package pl.arkadiuszsas.invoiceer.model.contractors.properties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.regex.Pattern;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Address {
    private String streetAddress;
    private String postalCode;
    private String city;

    public Address(String streetAddress, String postalCode, String city) {
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;

        if (streetAddressIsInvalid()) throw new IllegalArgumentException("Street address is not valid!");
        if (postalCodeIsInvalid()) throw new IllegalArgumentException("Postal code is not valid!");
        if (cityNameIsInvalid()) throw new IllegalArgumentException("City name is not valid!");
    }

    /**
     * Method checks if the Street Address is incorrect.
     * <p>
     * Expected value for the Street Address is to contain letters and digits obligatory.
     * Other optional characters are also accepted.
     *
     * @return true if the Street Address is incorrect, false in other case
     */
    private boolean streetAddressIsInvalid() {
        final Pattern streetAddressPattern = Pattern.compile("(.*[A-Za-z].*)(.*\\d.*)");
        return !streetAddressPattern.matcher(this.streetAddress).find();
    }

    /**
     * Method checks if the Postal Code is incorrect.
     * <p>
     * Expected value for the Postal Code is to match the following pattern: 00-000.
     *
     * @return true if the Postal Code is incorrect, false in other case
     */
    private boolean postalCodeIsInvalid() {
        final Pattern postalCodePattern = Pattern.compile("^\\d{2}-\\d{3}$");
        return !postalCodePattern.matcher(this.postalCode).find();
    }

    /**
     * Method checks if the City Name is incorrect.
     * <p>
     * Expected value for the City Name is to start with letters.
     * Other optional characters (white characters, dashes, letters after mentioned characters) are also accepted.
     *
     * @return true if the City Name is incorrect, false in other case
     */
    private boolean cityNameIsInvalid() {
        final Pattern cityPattern = Pattern.compile("^[a-zA-ZĄĆĘŁŃŚŻŹąćęłńóśżź]+([\\s-][a-zA-ZĄĆĘŁŃŚŻŹąćęłńóśżź]+)*$");
        return !cityPattern.matcher(this.city).find();
    }
}
