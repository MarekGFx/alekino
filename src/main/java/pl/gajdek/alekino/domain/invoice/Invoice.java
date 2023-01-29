package pl.gajdek.alekino.domain.invoice;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.order.Orders;

import java.time.YearMonth;
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
    private Orders orders;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber() {
        YearMonth yearMonth = YearMonth.now();
        this.invoiceNumber = yearMonth + "/" + id;
    }
}
