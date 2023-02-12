package pl.gajdek.alekino.domain.ticket;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCart;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCartRepository;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCartService;
import pl.gajdek.alekino.enums.CartStatus;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServices {

    private ShoppingCartService shoppingCartService;
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private HttpSession session;

    public void addTicket(Ticket ticket) {
        Long shoppingCartId = (Long) session.getAttribute("shopping_cart_id");
        Optional<ShoppingCart> shoppingCart = Optional.empty();
        if (shoppingCartId != null) {
            shoppingCart = shoppingCartRepository.findById(shoppingCartId);
        }
        if (!shoppingCart.isPresent() || shoppingCart.get().getStatus().equals(CartStatus.CLOSED)) {

            shoppingCartId = shoppingCartService.createNewShoppingCart();
            session.setAttribute("shopping_cart_id", shoppingCartId);
        }

        shoppingCartService.addTicketToShoppingCart(shoppingCartId, ticket);
    }

}
