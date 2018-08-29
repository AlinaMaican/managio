package ro.esolutions.eipl.types;

public enum MabecCode {
    CODE_01("01"),
    CODE_02("02"),
    CODE_03("03"),
    CODE_04("04"),
    CODE_05("05"),
    CODE_06("06"),
    CODE_07("07");

    private final String code;

    MabecCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
