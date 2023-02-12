package pl.gajdek.alekino.domain.shoppingCart.map;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCart;
import pl.gajdek.alekino.domain.shoppingCart.dto.ShoppingCartDto;
import pl.gajdek.alekino.domain.ticket.Ticket;
import pl.gajdek.alekino.domain.ticket.map.TicketDtoMapper;

@Component
@AllArgsConstructor
public class ShoppingDtoMapper {

    private TicketDtoMapper ticketDtoMapper;

    public ShoppingCartDto mapToShoppingCartDto(ShoppingCart shoppingCart){

        double summaryPrice = 0;
        for (Ticket ticket : shoppingCart.getTicket()) {
            summaryPrice += ticket.getTicketPrice();
        }

        return new ShoppingCartDto(
                shoppingCart.getTicket().stream().map(ticketDtoMapper::mapToTicketDto).toList(),
                summaryPrice
        );
    }
}
