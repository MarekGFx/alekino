package pl.gajdek.alekino.domain.shoppingCart;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gajdek.alekino.domain.ticket.Ticket;
import pl.gajdek.alekino.domain.user.dto.UserDto;

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


    @PatchMapping
    @Operation(summary = "Summary your shopping cart")
    public ResponseEntity<?> summaryShoppingCart(@RequestBody @Valid UserDto userDto){
        return shoppingCartService.summaryOrder(userDto,session);
    }
}
