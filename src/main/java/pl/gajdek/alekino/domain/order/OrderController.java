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
@RequestMapping("orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    private HttpSession session;


    @PatchMapping("/blik")
    @Operation(summary = "Pay for Order with BLIK")
    public ResponseEntity<?> payForOrder(@RequestParam(name = "BILK NUMBER") int blikNumber){
        return orderService.payForTheOrder(blikNumber,session);
    }

    @GetMapping
    @Operation(summary = "Get unpaid orders list by user")
    public ResponseEntity<?> findUnpaidOrders(@RequestParam(name = "email")String email) {
        return orderService.findUnpaidOrders(email);
    }
}
