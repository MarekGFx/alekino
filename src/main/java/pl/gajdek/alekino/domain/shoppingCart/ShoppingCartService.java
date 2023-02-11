package pl.gajdek.alekino.domain.shoppingCart;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.shoppingCart.dto.ShoppingCartDto;
import pl.gajdek.alekino.domain.shoppingCart.map.ShoppingDtoMapper;
import pl.gajdek.alekino.domain.ticket.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingDtoMapper shoppingDtoMapper;
    @Autowired
    private HttpSession session;

    public long createNewShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
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
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);
        if (!shoppingCart.isPresent()) {
            System.out.println(shoppingCartId);
            return ResponseEntity.status(404).body("Nie ma");
        }
        else {
            ShoppingCartDto shoppingCartDto = shoppingDtoMapper.mapToShoppingCartDto(shoppingCart.get());
            return ResponseEntity.ok(shoppingCartDto);
        }
    }


    public ShoppingCart getShoppingCartByIds(long id){
        return shoppingCartRepository.findById(id).get();
    }
}
