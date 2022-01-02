package pl.arkadiuszsas.invoiceer.model.factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.arkadiuszsas.invoiceer.model.contractors.Contractor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContractorFactory {
    public static Contractor produceValidContractor() {
        final var fullName = "Josh Newell";
        final var address = AddressFactory.produceValidAddress();

        return new Contractor(fullName, address);
    }
}
