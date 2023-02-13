package pl.gajdek.alekino.domain.shoppingCart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.ticket.Ticket;
import pl.gajdek.alekino.domain.ticket.dto.TicketToShoppingCartDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto {

    private List<TicketToShoppingCartDto> tickets;
    private double summaryPrice = getSummaryPrice();

}
