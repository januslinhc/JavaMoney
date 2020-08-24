package org.janus.money;

public enum CurrencyEnum {
    CAD("CAD"), HKD("HKD"), USD("USD");

    private final String value;

    CurrencyEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
