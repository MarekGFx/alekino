package pl.gajdek.alekino.domain.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.order.Orders;
import pl.gajdek.alekino.domain.showing.Showing;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Showing showing;
    private double TicketPrice;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;
}
