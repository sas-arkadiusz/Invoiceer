package pl.arkadiuszsas.invoiceer.model.contractors;

import lombok.*;
import pl.arkadiuszsas.invoiceer.model.contractors.properties.Address;

@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Contractor {
    private String fullName;
    private Address address;
}
