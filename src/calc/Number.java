package calc;

public class Number {
    public enum NumType {
        ARABIC,
        ROMAN
    }

    private final int value;
    private final NumType type;

    Number(int value, NumType type) {
        this.value = value;
        this.type = type;
    }

    int getValue() {
        return value;
    }

    NumType getType() {
        return type;
    }

}
