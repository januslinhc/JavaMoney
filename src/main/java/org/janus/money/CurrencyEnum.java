package org.janus.money;

public enum CurrencyEnum {
    CAD("CAD"),
    HKD("HKD"),
    USD("USD"),
    JPY("JPY"),
    EUR("EUR"),
    GBP("GBP"),
    CHF("CHF"),
    AUD("AUD"),
    CNY("CNY"),
    INR("INR"),
    MYR("MYR"),
    NZD("NZD");

    private final String value;

    CurrencyEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
