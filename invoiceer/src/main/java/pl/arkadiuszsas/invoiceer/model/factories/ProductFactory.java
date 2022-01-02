package pl.arkadiuszsas.invoiceer.model.factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.arkadiuszsas.invoiceer.model.products.Product;
import pl.arkadiuszsas.invoiceer.model.products.properties.Gtu;
import pl.arkadiuszsas.invoiceer.model.products.properties.Unit;
import pl.arkadiuszsas.invoiceer.model.products.properties.VatRate;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductFactory {
    public static List<Product> produceValidListOfProducts() {
        return List.of(produceValidProduct());
    }

    public static Product produceValidProduct() {
        final var name = "product-name";
        final var amount = 1;
        final var unit = Unit.ITEM;
        final var gtu = Gtu.GTU_12;
        final var grossUnitPrice = 49.99;
        final var vatRate = VatRate.VAT23;

        return new Product(name, amount, unit, gtu, grossUnitPrice, vatRate);
    }
}
