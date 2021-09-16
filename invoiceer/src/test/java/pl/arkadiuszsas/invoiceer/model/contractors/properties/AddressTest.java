package pl.arkadiuszsas.invoiceer.model.contractors.properties;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    final String validStreetAddress = "Street 1";
    final String validPostalCode = "50-505";
    final String validCity = "Węgrzce Wielkie";

    @Test
    void streetAddressIsValidatedCorrectly() {
        final List<String> invalidStreetAddresses = Arrays.asList("", "50505", validPostalCode, "Street");
        final List<String> validStreetAddresses = Arrays.asList(validStreetAddress, "Street 1A", "Street 1a", "Street 1/1");

        invalidStreetAddresses.forEach(invalidStreetAddress ->
                assertThrows(IllegalArgumentException.class, () ->
                        new Address(invalidStreetAddress, validPostalCode, validCity)));

        validStreetAddresses.forEach(validStreetAddress ->
                assertEquals(validStreetAddress, new Address(validStreetAddress, validPostalCode, validCity).getStreetAddress()));
    }

    @Test
    void postalCodeIsValidatedCorrectly() {
        final List<String> invalidPostalCodes = Arrays.asList("", "Invalid", "50505", "505-505", "50-5050");

        invalidPostalCodes.forEach(invalidPostalCode ->
                assertThrows(IllegalArgumentException.class, () ->
                        new Address(validStreetAddress, invalidPostalCode, validCity)));

        assertEquals(validPostalCode, new Address(validStreetAddress, validPostalCode, validCity).getPostalCode());
    }

    @Test
    void cityNameIsValidatedCorrectly() {
        final List<String> invalidCityNames = Arrays.asList("", "50505", validPostalCode, validStreetAddress);
        final List<String> validCityNames = Arrays.asList(validCity, "City Name", "City Name Name", "City-Name", "City-Name-Name");

        invalidCityNames.forEach(invalidCityName ->
                assertThrows(IllegalArgumentException.class, () ->
                        new Address(validStreetAddress, validPostalCode, invalidCityName), invalidCityName));

        validCityNames.forEach(validCityName ->
                assertEquals(validCityName, new Address(validStreetAddress, validPostalCode, validCityName).getCity(), validCityName));
    }
}
