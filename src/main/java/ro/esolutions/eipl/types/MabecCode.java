package ro.esolutions.eipl.types;

public enum MabecCode {
    MABEC_01("01"),
    MABEC_02("02"),
    MABEC_03("03"),
    MABEC_04("04"),
    MABEC_05("05"),
    MABEC_06("06"),
    MABEC_07("07");

    private final String code;

    MabecCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
