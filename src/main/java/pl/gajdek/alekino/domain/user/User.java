package pl.gajdek.alekino.domain.user;

import jakarta.persistence.*;
import lombok.*;
import pl.gajdek.alekino.domain.order.Order;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCart;
import pl.gajdek.alekino.enums.Role;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
//    @OneToOne
//    private ShoppingCart shoppingCart;

    @OneToMany
    private List<Order> order;
}
