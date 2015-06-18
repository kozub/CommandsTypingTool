package pl.kozubg;

/**
 * Created by kozub on 6/18/15.
 */
public enum  SpecialTag {

    SCREENSHOT("[SCREENSHOT]");

    private String value;

    SpecialTag(String value) {
        this.value = value;
    }

    public static SpecialTag findByValue(String machingValue) {
        for (SpecialTag specialTag : values()) {
            if (specialTag.value.equals(machingValue)) {
                return specialTag;
            }
        }
        return null;
    }
}
