package pl.arkadiuszsas.invoiceer.reader;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ExcelInvoiceDataReaderTest {

    @Test
    void x() throws IOException {
        ExcelInvoiceDataReader x = new ExcelInvoiceDataReader("/Users/arkadiusz/Work/Development/Invoiceer/invoiceer/src/test/resources/Test.xlsx");
        x.readInvoicesFromExcelDataFile();
    }
}