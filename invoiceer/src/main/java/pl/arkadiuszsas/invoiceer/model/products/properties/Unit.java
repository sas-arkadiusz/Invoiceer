package pl.arkadiuszsas.invoiceer.model.products.properties;

public enum Unit {
    ITEM("szt.");

    private final String unit;

    private Unit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
