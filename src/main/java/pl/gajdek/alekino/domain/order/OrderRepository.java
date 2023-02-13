package pl.gajdek.alekino.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCart;
import pl.gajdek.alekino.domain.ticket.Ticket;
import pl.gajdek.alekino.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByShoppingCart(ShoppingCart shoppingCart);

    List<Order> findByUser(User user);

//    Optional<Order> findByTicket(List<Ticket> ticketList);
}
