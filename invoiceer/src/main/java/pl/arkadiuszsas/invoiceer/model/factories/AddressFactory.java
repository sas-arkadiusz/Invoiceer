package pl.arkadiuszsas.invoiceer.model.factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.arkadiuszsas.invoiceer.model.contractors.properties.Address;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AddressFactory {
    public static Address produceValidAddress() {
        final var streetAddress = "Traders Alley 4347";
        final var postalCode = "64-106";
        final var city = "Portland";

        return new Address(streetAddress, postalCode, city);
    }
}
