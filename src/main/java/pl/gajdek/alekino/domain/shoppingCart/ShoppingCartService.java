package pl.gajdek.alekino.domain.shoppingCart;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.ticket.Ticket;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private HttpSession session;

    public long createNewShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        long shoppingCartId = createShoppingCart(shoppingCart);
        session.setAttribute("shoppingCart", shoppingCart);
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
}
