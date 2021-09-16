package pl.arkadiuszsas.invoiceer.reader;

public enum RowContent {
    INVOICING_DATE(0),
    INVOICING_CITY(1),
    DELIVERY_DATE(2),
    METHOD_OF_PAYMENT(3),
    PAYMENT_DATE(4),
    INVOICE_NUMBER(5),
    CUSTOMER_NAME(6),
    CUSTOMER_STREET_ADDRESS(7),
    CUSTOMER_POSTAL_CODE(8),
    CUSTOMER_CITY(9);

    private final int rowNumber;

    RowContent(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }
}
