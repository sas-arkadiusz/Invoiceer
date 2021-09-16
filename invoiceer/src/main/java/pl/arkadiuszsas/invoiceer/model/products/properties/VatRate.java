package pl.arkadiuszsas.invoiceer.model.products.properties;

public enum VatRate {
    VAT23(23.0),
    VAT8(8.0),
    VAT5(5.0),
    VAT0(0.0);

    private final double vatRate;

    private VatRate(double vatRate) {
        this.vatRate = vatRate;
    }

    public double getVatRate() {
        return vatRate;
    }
}
