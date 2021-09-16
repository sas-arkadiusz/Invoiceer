package pl.arkadiuszsas.invoiceer.model.products.properties;

public enum Gtu {
    NO_GTU(""),
    GTU_01("GTU_01"),
    GTU_02("GTU_02"),
    GTU_03("GTU_03"),
    GTU_04("GTU_04"),
    GTU_05("GTU_05"),
    GTU_06("GTU_06"),
    GTU_07("GTU_07"),
    GTU_08("GTU_08"),
    GTU_09("GTU_09"),
    GTU_10("GTU_10"),
    GTU_11("GTU_11"),
    GTU_12("GTU_12"),
    GTU_13("GTU_13");

    private final String gtu;

    private Gtu(String gtu) {
        this.gtu = gtu;
    }

    public String getGtu() {
        return gtu;
    }
}
