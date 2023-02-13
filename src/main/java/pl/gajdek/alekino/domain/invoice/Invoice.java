package pl.gajdek.alekino.domain.invoice;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.order.Order;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String invoiceNumber;
    @OneToOne
    private Order order;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Order order) {
        Year year = Year.now();
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();
        this.invoiceNumber = year + "/" + month + "/" + day + "/" + order.getId();
    }
}
