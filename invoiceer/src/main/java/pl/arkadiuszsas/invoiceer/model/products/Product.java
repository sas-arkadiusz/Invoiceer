package pl.arkadiuszsas.invoiceer.model.products;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.arkadiuszsas.invoiceer.model.products.properties.Gtu;
import pl.arkadiuszsas.invoiceer.model.products.properties.Unit;
import pl.arkadiuszsas.invoiceer.model.products.properties.VatRate;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Product {
    private String name;
    private int amount;
    private Unit unit;
    private Gtu gtu;
    private double grossUnitPrice;
    private VatRate vatRate;

    public double getGrossValue() {
        return grossUnitPrice * amount;
    }

    public double getNetValue() {
        return getGrossValue() * (vatRate.getVatRate() / 100);
    }
}
