package pl.gajdek.alekino.domain.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.showing.dto.ShowingDto;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketToOrderDto {

    private ShowingDto showingDto;
    private int ticketPrice;
}
