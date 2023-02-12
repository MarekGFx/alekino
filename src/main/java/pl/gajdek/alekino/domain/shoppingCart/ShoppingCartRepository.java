package pl.gajdek.alekino.domain.shoppingCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gajdek.alekino.domain.order.Order;
import pl.gajdek.alekino.domain.ticket.Ticket;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

//    Optional<ShoppingCart> findByTicket(List<Ticket> ticketList);
}
