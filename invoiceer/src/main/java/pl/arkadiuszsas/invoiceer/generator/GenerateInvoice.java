package pl.arkadiuszsas.invoiceer.generator;

import org.springframework.stereotype.Service;
import pl.arkadiuszsas.invoiceer.model.Invoice;

@Service
public interface GenerateInvoice {

    /**
     * Method generates an invoice with an appropriate data.
     * Invoice's structure is based on pattern from the MS Word file.
     */
    void generateInvoice(String generatedFilePath,
                         String invoiceWordPatternFilePath,
                         Invoice invoice);
}
