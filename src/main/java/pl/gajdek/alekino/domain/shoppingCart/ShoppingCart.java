package pl.gajdek.alekino.domain.shoppingCart;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import pl.gajdek.alekino.domain.ticket.Ticket;
import pl.gajdek.alekino.domain.user.User;

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
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
