package pl.gajdek.alekino.domain.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCart;
import pl.gajdek.alekino.domain.ticket.Ticket;
import pl.gajdek.alekino.domain.user.User;
import pl.gajdek.alekino.enums.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "order")
    private List<Ticket> ticketList;
    private double summaryPrice;
    private LocalDate orderDate;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToOne
    private ShoppingCart shoppingCart;


    public double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(List<Ticket> ticketList) {
        double summaryPrice = 0;
        for (Ticket ticket : ticketList) {
            summaryPrice+=ticket.getTicketPrice();
        }
        this.summaryPrice = summaryPrice;
    }

    public void setTicketList(List<Ticket> tickets) {
        this.ticketList = new ArrayList<>(tickets);
    }
}
