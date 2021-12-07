package pl.arkadiuszsas.invoiceer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.arkadiuszsas.invoiceer.generator.GenerateInvoice;
import pl.arkadiuszsas.invoiceer.model.Invoice;
import pl.arkadiuszsas.invoiceer.model.factories.InvoiceFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    private final List<Invoice> invoices = new ArrayList<>();

    /**
     * Method calls all the methods which are responsible for the generation of the Invoice.
     *
     * @param invoice with all its properties necessary to generate one
     * @return the status of the request
     */
    @PostMapping
    public ResponseEntity generateInvoice(@RequestBody Invoice invoice) {
        invoices.add(invoice);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/testInvoice")
    public ResponseEntity generateTestInvoice() {
        invoices.add(InvoiceFactory.produceValidInvoice());
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
