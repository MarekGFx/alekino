package pl.gajdek.alekino.domain.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gajdek.alekino.domain.showing.Showing;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findByShoppingCartId(long id);
}
