package pl.gajdek.alekino.domain.invoice.map;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import pl.gajdek.alekino.domain.invoice.Invoice;
import pl.gajdek.alekino.domain.invoice.dto.InvoiceDto;
import pl.gajdek.alekino.domain.order.map.OrderDtoMapper;
import pl.gajdek.alekino.interfaceMapper.EntityMapper;

@Component
@AllArgsConstructor
public class InvoiceDtoMapper implements EntityMapper<Invoice, InvoiceDto> {

    private OrderDtoMapper orderDtoMapper;

    @Override
    public Invoice toEntity(InvoiceDto invoiceDto) {
        return null;
    }

    @Override
    public InvoiceDto toDto(Invoice invoice) {
        return new InvoiceDto(
                invoice.getInvoiceNumber(),
                orderDtoMapper.toDto(invoice.getOrder())
        );
    }
}
