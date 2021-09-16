package pl.arkadiuszsas.invoiceer.model.products.properties;

public enum MethodOfPayment {
    CASH("Gotówka"),
    BANK_TRANSFER("Przelew");

    private final String methodOfPayment;

    MethodOfPayment(String methodOfPayment) {
        this.methodOfPayment = methodOfPayment;
    }

    public String getMethodOfPayment() {
        return methodOfPayment;
    }
}
