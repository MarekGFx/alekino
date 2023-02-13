package pl.gajdek.alekino.domain.shoppingCart;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.order.Order;
import pl.gajdek.alekino.domain.order.OrderRepository;
import pl.gajdek.alekino.domain.order.dto.OrderDto;
import pl.gajdek.alekino.domain.order.map.OrderDtoMapper;
import pl.gajdek.alekino.domain.shoppingCart.dto.ShoppingCartDto;
import pl.gajdek.alekino.domain.shoppingCart.map.ShoppingDtoMapper;
import pl.gajdek.alekino.domain.ticket.Ticket;
import pl.gajdek.alekino.domain.ticket.TicketRepository;
import pl.gajdek.alekino.domain.user.User;
import pl.gajdek.alekino.domain.user.UserRepository;
import pl.gajdek.alekino.domain.user.dto.UserDto;
import pl.gajdek.alekino.enums.CartStatus;
import pl.gajdek.alekino.enums.OrderStatus;
import pl.gajdek.alekino.enums.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private TicketRepository ticketRepository;
    private ShoppingDtoMapper shoppingDtoMapper;

    private OrderDtoMapper orderDtoMapper;
    @Autowired
    private HttpSession session;

    public long createNewShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setStatus(CartStatus.OPEN);
        long shoppingCartId = createShoppingCart(shoppingCart);
        session.setAttribute("shopping_cart_id", shoppingCartId);
        return shoppingCartId;
    }

    private long createShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart = shoppingCartRepository.save(shoppingCart);
        return shoppingCart.getId();
    }

    public void addTicketToShoppingCart(long shoppingCartId, Ticket ticket) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElse(null);
        if (shoppingCart != null) {
            ticket.setShoppingCart(shoppingCart);
            if (shoppingCart.getTicket() == null) {
                List<Ticket> newTickets = new ArrayList<>();
                newTickets.add(ticket);
                shoppingCart.setTicket(newTickets);
            } else {
                shoppingCart.getTicket().add(ticket);
            }
            shoppingCartRepository.save(shoppingCart);
        }
    }

    public ResponseEntity<?> getShoppingCart(HttpSession session){
        Long shoppingCartId = (Long) session.getAttribute("shopping_cart_id");
        if (shoppingCartId == null){
            return ResponseEntity.status(404).body("You haven't added any tickets to your cart yet");
        }
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);
        if (!shoppingCart.isPresent()) {
            return ResponseEntity.status(404).body("Shopping cart does not exist");
        } else if (shoppingCart.get().getStatus().equals(CartStatus.CLOSED)){
            return ResponseEntity.status(404).body("Shopping cart is empty");
        }
        else {
            ShoppingCartDto shoppingCartDto = shoppingDtoMapper.mapToShoppingCartDto(shoppingCart.get());
            return ResponseEntity.ok(shoppingCartDto);
        }
    }

    @Transactional
    public ResponseEntity<?> summaryOrder(UserDto userToOrder, HttpSession session){
        User user = new User();
        Order order = new Order();
        Long shoppingCartId = (Long) session.getAttribute("shopping_cart_id");
        Optional<User> optionalUser = userRepository.findByEmail(userToOrder.getEmail());
        Optional<ShoppingCart> shoppingCart = Optional.empty();
        if (shoppingCartId != null) {
            shoppingCart = shoppingCartRepository.findById(shoppingCartId);
        }
        if (!shoppingCart.isPresent()) {
            return ResponseEntity.status(404).body("Have not made any purchases there is nothing to summarize");
        } else if (shoppingCart.get().getStatus().equals(CartStatus.CLOSED)) {
            return ResponseEntity.status(400).body("The shopping cart has been summarized and it's empty");
        } else {
            if (optionalUser.isPresent() && !optionalUser.get().getFirstName().equals(userToOrder.getFirstName())
                    || optionalUser.isPresent() && !optionalUser.get().getLastName().equals(userToOrder.getLastName())){
                return ResponseEntity.status(400).body("First or last name not match the email address stored in the database");
            }
            if (optionalUser.isPresent()){
                user = optionalUser.get();
            } else {
                user.setFirstName(userToOrder.getFirstName());
                user.setLastName(userToOrder.getLastName());
                user.setEmail(userToOrder.getEmail());
                user.setRole(Role.USER);
            }
            order.setOrderDate(LocalDate.now());
            order.setTicketList(shoppingCart.get().getTicket());
            order.setSummaryPrice(order.getTicketList());
            List<Order> orders = user.getOrder();
            if (user.getOrder()==null){
                orders = new ArrayList<>();
                user.setOrder(orders);
            }
            orders.add(order);
            order.setUser(user);
            shoppingCart.get().setStatus(CartStatus.CLOSED);
            order.setStatus(OrderStatus.PENDING);
            List<Ticket> ticketList = ticketRepository.findByShoppingCartId(shoppingCart.get().getId());
            for (Ticket ticket : ticketList) {
                ticket.setOrder(order);
                ticketRepository.save(ticket);
            }
            order.setShoppingCart(shoppingCart.get());
            shoppingCart.get().setOrder(order);
            orderRepository.save(order);
            userRepository.save(user);
            OrderDto orderDto = orderDtoMapper.toDto(order);
            return ResponseEntity.ok(orderDto);
        }
    }
}
