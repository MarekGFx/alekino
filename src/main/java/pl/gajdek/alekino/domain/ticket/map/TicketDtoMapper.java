package pl.gajdek.alekino.domain.ticket.map;

import org.springframework.stereotype.Component;
import pl.gajdek.alekino.domain.ticket.Ticket;
import pl.gajdek.alekino.domain.ticket.dto.TicketToShoppingCartDto;

@Component
public class TicketDtoMapper {

    public TicketToShoppingCartDto mapToTicketDto(Ticket ticket) {
        return new TicketToShoppingCartDto(ticket.getShowing().getMovie().getTitle(),
                    ticket.getTicketPrice(),
                    ticket.getShowingSeat().getRowNumber(),
                    ticket.getShowingSeat().getSeatNumber()
        );
    }
}
