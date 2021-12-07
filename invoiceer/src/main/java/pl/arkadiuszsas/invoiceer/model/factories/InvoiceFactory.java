package pl.arkadiuszsas.invoiceer.model.factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import pl.arkadiuszsas.invoiceer.model.Invoice;
import pl.arkadiuszsas.invoiceer.model.products.properties.MethodOfPayment;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InvoiceFactory {
    public static Invoice produceValidInvoice() {
        final var invoicingDate = LocalDate.now();
        final var invoicingCity = "Portland";
        final var deliveryDate = LocalDate.now();
        final var invoiceNumber = "1/1/2022";
        final var methodOfPayment = MethodOfPayment.BANK_TRANSFER;
        final var paymentDate = LocalDate.now();
        final var vendor = CompanyFactory.produceValidCompany();
        final var customer = ContractorFactory.produceValidContractor();
        final var products = ProductFactory.produceValidListOfProducts();
        final var amountPaid = BigDecimal.ZERO;

        return Invoice.builder()
                .invoicingDate(invoicingDate)
                .invoicingCity(invoicingCity)
                .deliveryDate(deliveryDate)
                .invoiceNumber(invoiceNumber)
                .methodOfPayment(methodOfPayment)
                .paymentDate(paymentDate)
                .vendor(vendor)
                .customer(customer)
                .products(products)
                .amountPaid(amountPaid)
                .build();
    }
}
