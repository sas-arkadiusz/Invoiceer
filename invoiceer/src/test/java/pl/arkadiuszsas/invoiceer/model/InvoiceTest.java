package pl.arkadiuszsas.invoiceer.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.arkadiuszsas.invoiceer.model.contractors.Company;
import pl.arkadiuszsas.invoiceer.model.contractors.Contractor;
import pl.arkadiuszsas.invoiceer.model.contractors.properties.Address;
import pl.arkadiuszsas.invoiceer.model.products.Product;
import pl.arkadiuszsas.invoiceer.model.products.properties.Gtu;
import pl.arkadiuszsas.invoiceer.model.products.properties.MethodOfPayment;
import pl.arkadiuszsas.invoiceer.model.products.properties.Unit;
import pl.arkadiuszsas.invoiceer.model.products.properties.VatRate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {
    final LocalDate invoicingDate = LocalDate.of(2021, 1, 1);
    final String invoicingCity = "City";
    final LocalDate deliveryDate = LocalDate.of(2021, 1, 5);
    final String invoiceNumber = "1/2020";
    final MethodOfPayment methodOfPayment = MethodOfPayment.CASH;
    final LocalDate paymentDate = LocalDate.of(2021, 1, 15);
    final Address address = new Address("Street 1", "00-000", "City");
    final Contractor company = new Company("Company Name", address, "5842751979");
    final Contractor customer = new Contractor("Contractor Name", address);
    final List<Product> products = Arrays.asList(new Product("Product Name", 1, Unit.ITEM, Gtu.GTU_01, 50.0, VatRate.VAT23));

    @Test
    void invoiceIsBuiltCorrectlyWithCorrectProperties() {
        final Invoice invoice = Invoice.builder()
                .invoicingDate(invoicingDate)
                .invoicingCity(invoicingCity)
                .deliveryDate(deliveryDate)
                .invoiceNumber(invoiceNumber)
                .methodOfPayment(methodOfPayment)
                .paymentDate(paymentDate)
                .vendor(company)
                .customer(customer)
                .products(products)
                .amountPaid(BigDecimal.ZERO)
                .build();

        assertInvoiceProperties(invoice);
    }

    @Test
    void buildExceptionIsThrownWhenNotAllPropertiesWithoutDefaultValuesAreProvided() {
        assertThrows(NullPointerException.class, () ->
                Invoice.builder()
                    .build());

        assertDoesNotThrow(() ->
                Invoice.builder()
                    .invoicingDate(invoicingDate)
                    .invoicingCity(invoicingCity)
                    .deliveryDate(deliveryDate)
                    .invoiceNumber(invoiceNumber)
                    .methodOfPayment(methodOfPayment)
                    .paymentDate(paymentDate)
                    .vendor(company)
                    .customer(customer)
                    .products(products)
                    .build());
    }

    void assertInvoiceProperties(Invoice invoice) {
        // To make sure that custom classes objects are compered correctly
        // those local instances are used instead of the global ones.
        final MethodOfPayment localMethodOfPayment = MethodOfPayment.CASH;
        final Address localAddress = new Address("Street 1", "00-000", "City");
        final Contractor localCompany = new Company("Company Name", localAddress, "5842751979");
        final Contractor localCustomer = new Contractor("Contractor Name", localAddress);
        final List<Product> localProducts = Arrays.asList(new Product("Product Name", 1, Unit.ITEM, Gtu.GTU_01, 50.0, VatRate.VAT23));

        Assertions.assertTrue(invoicingDate.equals(invoice.getInvoicingDate()), "Invoicing dates aren't equal!");
        Assertions.assertTrue(invoicingCity.equals(invoice.getInvoicingCity()), "Invoicing cities aren't equal!");
        Assertions.assertTrue(deliveryDate.equals(invoice.getDeliveryDate()), "Delivery dates aren't equal!");
        Assertions.assertTrue(invoiceNumber.equals(invoice.getInvoiceNumber()), "Invoicing numbers aren't equal!");
        Assertions.assertTrue(localMethodOfPayment.equals(invoice.getMethodOfPayment()), "Method of payments aren't equal!");
        Assertions.assertTrue(localCompany.equals(invoice.getVendor()), "Companies aren't equal!");
        Assertions.assertTrue(localCustomer.equals(invoice.getCustomer()), "Customers aren't equal!");
        Assertions.assertTrue(localProducts.equals(invoice.getProducts()), "List of products aren't equal!");
    }
}