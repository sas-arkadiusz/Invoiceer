package pl.arkadiuszsas.invoiceer.model.contractors;

import org.junit.jupiter.api.Test;
import pl.arkadiuszsas.invoiceer.model.contractors.properties.Address;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    final String validFullName = "Full Name";
    final Address validAddress = new Address("Street 1", "50-505", "City");

    @Test
    void nipIsValidatedCorrectly() {
        final List<String> invalidNips = Arrays.asList("", "555", "5842751978", "584-275-19-79");
        final String validNip = "5842751979";

        invalidNips.forEach(invalidNip ->
                assertThrows(IllegalArgumentException.class, () ->
                        new Company(validFullName, validAddress, invalidNip)));

        assertEquals(validNip, new Company(validFullName, validAddress, validNip).getNip());
    }
}
