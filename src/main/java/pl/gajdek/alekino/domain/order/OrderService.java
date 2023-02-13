package pl.gajdek.alekino.domain.order;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.blik.Blik;
import pl.gajdek.alekino.domain.blik.BlikRepository;
import pl.gajdek.alekino.domain.invoice.Invoice;
import pl.gajdek.alekino.domain.invoice.InvoiceRepository;
import pl.gajdek.alekino.domain.invoice.dto.InvoiceDto;
import pl.gajdek.alekino.domain.invoice.map.InvoiceDtoMapper;
import pl.gajdek.alekino.domain.order.map.OrderDtoMapper;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCart;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCartRepository;
import pl.gajdek.alekino.domain.user.User;
import pl.gajdek.alekino.domain.user.UserRepository;
import pl.gajdek.alekino.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private ShoppingCartRepository shoppingCartRepository;
    private OrderRepository orderRepository;
    private BlikRepository blikRepository;
    private InvoiceRepository invoiceRepository;
    private UserRepository userRepository;
    private InvoiceDtoMapper invoiceDtoMapper;
    private OrderDtoMapper orderDtoMapper;


    public ResponseEntity<?> payForTheOrder(int blikNumber, HttpSession session){
        Long shoppingCartId = (Long) session.getAttribute("shopping_cart_id");
        Optional<ShoppingCart> shoppingCart = Optional.empty();
        if (shoppingCartId != null) {
            shoppingCart = shoppingCartRepository.findById(shoppingCartId);
        }
        Optional<Order> order = orderRepository.findByShoppingCart(shoppingCart.get());
        if (!order.isPresent()) {
            return ResponseEntity.status(404).body("Order does not exist");
        } else if (order.get().getStatus().equals(OrderStatus.CLOSED)) {
            return ResponseEntity.status(404).body("Order is already payed");
        } else {
            Optional<Blik> blik = blikRepository.findByBlikNumber(blikNumber);
            if (blik.isPresent()) {
                order.get().setStatus(OrderStatus.CLOSED);
                Invoice invoice = new Invoice();
                invoice.setInvoiceNumber(order.get());
                invoice.setOrder(order.get());
                invoiceRepository.save(invoice);
                InvoiceDto invoiceDto = invoiceDtoMapper.toDto(invoice);
                blikRepository.delete(blik.get());
                return ResponseEntity.ok(invoiceDto);
            } else
                return ResponseEntity.status(404).body("Invalid blik code");
        }
    }

    public ResponseEntity<?> findUnpaidOrders(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()){
            return ResponseEntity.status(404).body("User with email " + email + " does not exist");
        } else {
            List<Order> orders = user.get().getOrder();
            List<Order> unpaid = new ArrayList<>();
            for (Order order : orders) {
                if (order.getStatus().equals(OrderStatus.PENDING)){
                    unpaid.add(order);
                }
            }
            return ResponseEntity.ok(unpaid.stream().map(orderDtoMapper::toDto));
        }
    }


}
