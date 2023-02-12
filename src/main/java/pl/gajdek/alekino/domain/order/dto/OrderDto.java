package pl.gajdek.alekino.domain.order.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.ticket.dto.TicketToOrderDto;
import pl.gajdek.alekino.domain.ticket.dto.TicketToShoppingCartDto;
import pl.gajdek.alekino.domain.user.dto.UserDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private List<TicketToShoppingCartDto> tickets;
    private double summaryPrice;
    private LocalDate orderDate;
    private UserDto userDto;

}
