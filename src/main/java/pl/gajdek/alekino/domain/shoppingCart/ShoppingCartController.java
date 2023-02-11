package pl.gajdek.alekino.domain.shoppingCart;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gajdek.alekino.domain.ticket.Ticket;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("shoppingCarts")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    private HttpSession session;



    @GetMapping
    @Operation(summary = "Get your shopping cart")
    public ResponseEntity<?> showShoppingCart(){
     return shoppingCartService.getShoppingCart(session);
    }

}
