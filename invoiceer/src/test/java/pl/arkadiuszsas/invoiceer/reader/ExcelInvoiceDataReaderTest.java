package pl.arkadiuszsas.invoiceer.reader;

import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import pl.arkadiuszsas.invoiceer.configuration.Constants;
import pl.arkadiuszsas.invoiceer.model.Invoice;
import pl.arkadiuszsas.invoiceer.model.contractors.Contractor;
import pl.arkadiuszsas.invoiceer.model.contractors.properties.Address;
import pl.arkadiuszsas.invoiceer.model.products.properties.MethodOfPayment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExcelInvoiceDataReaderTest {

    @Test
    void readInvoicesFromExcelDataFileFindsCorrectInvoiceDataFromFile() throws IOException {
        final ExcelInvoiceDataReader excelInvoiceDataReader = new ExcelInvoiceDataReader("src/test/resources/ProperInvoiceDataSheet.xlsx");
        final List<Invoice> invoices = excelInvoiceDataReader.readInvoicesFromExcelDataFile();

        final Invoice firstExpectedInvoice = Invoice.builder()
                .invoicingDate(LocalDate.of(2021, 9, 2))
                .invoicingCity("Bydgoszcz")
                .deliveryDate(LocalDate.of(2021, 9, 3))
                .invoiceNumber("136/2021")
                .methodOfPayment(MethodOfPayment.BANK_TRANSFER)
                .paymentDate(LocalDate.of(2021, 9, 4))
                .vendor(Constants.defaultVendor)
                .customer(Contractor.builder()
                    .fullName("Jan Kowalski")
                    .address(new Address("Długa 1", "85-028", "Poznań"))
                    .build())
                .products(new ArrayList<>())
                .amountPaid(BigDecimal.ZERO)
                .build();

        assertEquals(2, invoices.size());
        assertEquals(firstExpectedInvoice, invoices.get(0));
    }
}