package pl.gajdek.alekino.domain.order;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gajdek.alekino.domain.user.dto.UserDto;

@RestController
@AllArgsConstructor
@RequestMapping("blik")
public class OrderController {

    private OrderService orderService;

    @Autowired
    private HttpSession session;


    @PatchMapping
    @Operation(summary = "Pay for Order with BLIK")
    public ResponseEntity<?> payForOrder(@RequestParam(name = "BILK NUMBER") int blikNumber){
        return orderService.payForTheOrder(blikNumber,session);
    }
}
