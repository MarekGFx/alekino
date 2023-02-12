package pl.gajdek.alekino.domain.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.constantCinemaData.ConstantDataForCinema;
import pl.gajdek.alekino.domain.order.Order;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCart;
import pl.gajdek.alekino.domain.showing.Showing;
import pl.gajdek.alekino.enums.SeatStatus;
import pl.gajdek.alekino.domain.showingSeat.ShowingSeat;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Showing showing;
    private double ticketPrice;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    @OneToOne
    private ShowingSeat showingSeat;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(SeatStatus seatStatus) {
        if (seatStatus.equals(SeatStatus.VIP)){
            ticketPrice = ConstantDataForCinema.SEAT_VIP_PRICE;
        }
        else
        ticketPrice = ConstantDataForCinema.SEAT_NORMAL_PRICE;
    }
}
