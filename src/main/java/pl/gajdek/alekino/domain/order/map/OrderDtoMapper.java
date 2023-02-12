package pl.gajdek.alekino.domain.order.map;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.gajdek.alekino.domain.order.Order;
import pl.gajdek.alekino.domain.order.dto.OrderDto;
import pl.gajdek.alekino.domain.ticket.map.TicketDtoMapper;
import pl.gajdek.alekino.domain.user.map.UserDtoMapper;
import pl.gajdek.alekino.interfaceMapper.EntityMapper;

@Component
@AllArgsConstructor
public class OrderDtoMapper implements EntityMapper<Order, OrderDto> {

    private TicketDtoMapper ticketDtoMapper;
    private UserDtoMapper userDtoMapper;

    @Override
    public Order toEntity(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto toDto(Order order) {
        return new OrderDto(
                order.getTicketList().stream().map(ticketDtoMapper::mapToTicketDto).toList(),
                order.getSummaryPrice(),
                order.getOrderDate(),
                userDtoMapper.mapToUserDto(order.getUser())
        );
    }
}
