package pl.arkadiuszsas.invoiceer.configuration;


import pl.arkadiuszsas.invoiceer.model.contractors.Contractor;
import pl.arkadiuszsas.invoiceer.model.contractors.properties.Address;

import java.time.format.DateTimeFormatter;

public class Constants {
    // DateTime
    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-mm-yyyy");

    // Invoice default values
    public static final Contractor defaultVendor = Contractor.builder()
            .fullName("Arkadiusz Sas")
            .address(new Address("Homarowa 20", "85-435", "Bydgoszcz"))
            .build();
}
