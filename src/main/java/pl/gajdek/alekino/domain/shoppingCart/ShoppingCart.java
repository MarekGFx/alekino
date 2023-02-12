package pl.gajdek.alekino.domain.shoppingCart;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import pl.gajdek.alekino.domain.order.Order;
import pl.gajdek.alekino.domain.ticket.Ticket;
import pl.gajdek.alekino.domain.user.User;
import pl.gajdek.alekino.enums.CartStatus;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingCart implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "shoppingCart")
    private List<Ticket> ticket;
    @Enumerated(EnumType.STRING)
    private CartStatus status;
    @OneToOne
    private Order order;
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}
