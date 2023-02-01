package pl.gajdek.alekino.domain.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.ConstantCinemaData.ConstantDataForCinema;
import pl.gajdek.alekino.domain.cinemaRoom.Seat;
import pl.gajdek.alekino.domain.cinemaRoom.SeatStatus;
import pl.gajdek.alekino.domain.order.Orders;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCart;
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
    private double ticketPrice;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @OneToOne
    private Seat seat;

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
