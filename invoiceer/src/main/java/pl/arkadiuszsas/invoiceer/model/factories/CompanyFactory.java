package pl.arkadiuszsas.invoiceer.model.factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.arkadiuszsas.invoiceer.model.contractors.Company;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CompanyFactory {
    public static Company produceValidCompany() {
        final var name = "company-name";
        final var address = AddressFactory.produceValidAddress();
        final var nip = "1258567245";

        return new Company(name, address, nip);
    }
}
