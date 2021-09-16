package pl.arkadiuszsas.invoiceer.reader;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import pl.arkadiuszsas.invoiceer.configuration.Constants;
import pl.arkadiuszsas.invoiceer.model.Invoice;
import pl.arkadiuszsas.invoiceer.model.contractors.Contractor;
import pl.arkadiuszsas.invoiceer.model.contractors.properties.Address;
import pl.arkadiuszsas.invoiceer.model.products.Product;
import pl.arkadiuszsas.invoiceer.model.products.properties.MethodOfPayment;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class ExcelInvoiceDataReader {
    private final String excelFilePath;

    public ExcelInvoiceDataReader(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    public List<Invoice> readInvoicesFromExcelDataFile() throws IOException {
        final List<Invoice> invoices = new ArrayList<>();

        final XSSFSheet invoiceDataSheet = getInvoiceExcelDataSheet();
         int numberOfInvoices = getLastNotEmptyRowNumber(invoiceDataSheet);

        // Method #getLastRowNum() returns number of rows with the data. So to generate
        // all the invoices it is necessary to iterate through rows: 1...numberOfInvoices + 1,
        // because #range method has exclusive end of the range.
        IntStream.range(1, numberOfInvoices + 1).forEach(rowNumber ->
                invoices.add(getInvoiceFromRowExcelDataSheet(invoiceDataSheet, rowNumber)));

        return invoices;
    }

    private XSSFSheet getInvoiceExcelDataSheet() throws IOException {
        final FileInputStream excelInvoiceDataFile = new FileInputStream(excelFilePath);
        final XSSFWorkbook invoiceDataWorkbook = new XSSFWorkbook(excelInvoiceDataFile);
        return invoiceDataWorkbook.getSheetAt(0);
    }

    private Invoice getInvoiceFromRowExcelDataSheet(XSSFSheet invoiceDataSheet, int rowNumber) {
        final XSSFRow row = invoiceDataSheet.getRow(rowNumber);

        // Invoice creation related information
        final String invoicingCity = row.getCell(RowContent.INVOICING_CITY.getRowNumber()).getStringCellValue();
        final LocalDate invoicingDate = DateUtil.getLocalDateTime(row.getCell(RowContent.INVOICING_DATE.getRowNumber()).getNumericCellValue()).toLocalDate();

        // Invoice header information
        final String invoiceNumber = row.getCell(RowContent.INVOICE_NUMBER.getRowNumber()).getStringCellValue();
        final LocalDate deliveryDate = DateUtil.getLocalDateTime(row.getCell(RowContent.DELIVERY_DATE.getRowNumber()).getNumericCellValue()).toLocalDate();
        final MethodOfPayment methodOfPayment = MethodOfPayment.valueOf(row.getCell(RowContent.METHOD_OF_PAYMENT.getRowNumber()).getStringCellValue());
        final LocalDate paymentDate = DateUtil.getLocalDateTime(row.getCell(RowContent.PAYMENT_DATE.getRowNumber()).getNumericCellValue()).toLocalDate();

        // Invoice vendor related information
        final Contractor company = Constants.defaultVendor;

        // Invoice customer related information
        final Contractor customer = Contractor.builder()
                .fullName(row.getCell(RowContent.CUSTOMER_NAME.getRowNumber()).getStringCellValue())
                .address(new Address(
                        row.getCell(RowContent.CUSTOMER_STREET_ADDRESS.getRowNumber()).getStringCellValue(),
                        row.getCell(RowContent.CUSTOMER_POSTAL_CODE.getRowNumber()).getStringCellValue(),
                        row.getCell(RowContent.CUSTOMER_CITY.getRowNumber()).getStringCellValue()
                )).build();

        final List<Product> products = new ArrayList<>();

        return Invoice.builder()
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
    }

    private int getLastNotEmptyRowNumber(XSSFSheet invoiceDataSheet) {
        int lastNotEmptyRowNumber = invoiceDataSheet.getLastRowNum();

        for (int rowNum = lastNotEmptyRowNumber; rowNum >= 0; rowNum--) {
            final Row row = invoiceDataSheet.getRow(rowNum);
            if (row != null && row.getCell(0) != null) {
                lastNotEmptyRowNumber = rowNum;
                break;
            }
        }

        return lastNotEmptyRowNumber;
    }
}
