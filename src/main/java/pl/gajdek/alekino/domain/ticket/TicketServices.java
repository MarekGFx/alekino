package pl.gajdek.alekino.domain.ticket;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCartService;

@Service
@AllArgsConstructor
public class TicketServices {

    private ShoppingCartService shoppingCartService;
    @Autowired
    private HttpSession session;

    public void addTicket(Ticket ticket) {
        Long shoppingCartId = (Long) session.getAttribute("shopping_cart_id");
        if (shoppingCartId == null) {

            shoppingCartId = shoppingCartService.createNewShoppingCart();
            session.setAttribute("shopping_cart_id", shoppingCartId);
        }

        shoppingCartService.addTicketToShoppingCart(shoppingCartId, ticket);
    }

}
