package pl.gajdek.alekino.domain.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketToShoppingCartDto {

    private String movieTitle;
    private double ticketPrice;
    private int row;
    private int seatNumber;

}
