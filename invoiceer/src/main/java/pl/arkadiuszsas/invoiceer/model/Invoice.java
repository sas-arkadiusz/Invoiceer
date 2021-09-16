package pl.arkadiuszsas.invoiceer.model;

import lombok.*;
import pl.arkadiuszsas.invoiceer.model.contractors.Contractor;
import pl.arkadiuszsas.invoiceer.model.products.Product;
import pl.arkadiuszsas.invoiceer.model.products.properties.MethodOfPayment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@ToString
public class Invoice {
    @NonNull private LocalDate invoicingDate;
    @NonNull private String invoicingCity;
    @NonNull private LocalDate deliveryDate;
    @NonNull private String invoiceNumber;
    @NonNull private MethodOfPayment methodOfPayment;
    @NonNull private LocalDate paymentDate;
    @NonNull private Contractor vendor;
    @NonNull private Contractor customer;
    @NonNull private List<Product> products;

    private BigDecimal amountPaid;
}
