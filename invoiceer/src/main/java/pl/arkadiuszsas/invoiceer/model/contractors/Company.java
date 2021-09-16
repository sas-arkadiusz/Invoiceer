package pl.arkadiuszsas.invoiceer.model.contractors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.arkadiuszsas.invoiceer.model.contractors.properties.Address;

import java.util.stream.IntStream;

@Getter
@Setter
@EqualsAndHashCode
public class Company extends Contractor {
    private String nip;

    public Company(String fullName, Address address, String nip) {
        super(fullName, address);
        this.nip = nip;

        if (nipIsInvalid()) throw new IllegalArgumentException("NIP is not valid!");
    }

    /**
     * Method checks if the NIP number is incorrect.
     * <p>
     * The algorithm does the following steps:
     * <ol>
     * <li>Check the length of the NIP. Length of 10 is expected.
     * <li>Calculate the Check Sum by multiplying 1st...9th digit by its weight defined in the array and sum those values.
     * <li>Calculate the Check Sum modulo 11.
     * <li>If the calculated modulo is equal to the 10th digit value: NIP is correct, in other case NIP is not correct.
     * </ol>
     *
     * @return true if the NIP is incorrect, true in other case
     */
    private boolean nipIsInvalid() {
        final int CORRECT_NIP_LENGTH = 10;
        if (nip.length() != CORRECT_NIP_LENGTH) return true;

        final int[] weights = {6, 5, 7, 2, 3, 4, 5, 6, 7};
        int controlSum = IntStream.range(0, weights.length)
                .map(i -> Integer.parseInt(String.valueOf(nip.charAt(i))) * weights[i])
                .sum();

        return controlSum % 11 != Integer.parseInt(String.valueOf(nip.charAt(9)));
    }
}
