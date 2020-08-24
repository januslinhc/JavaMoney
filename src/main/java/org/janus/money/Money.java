package org.janus.money;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money implements IMoney {
    private final CurrencyEnum base;
    private BigDecimal amount;
    private final IRateTable rateTable;

    Money(@NotNull final CurrencyEnum base, @NotNull final IRateTable rateTable) {
        this.base = base;
        this.amount = BigDecimal.ZERO;
        this.rateTable = rateTable;
    }

    Money(@NotNull final CurrencyEnum base, @NotNull final IRateTable rateTable, @NotNull final BigDecimal amount) {
        this.base = base;
        this.amount = amount;
        this.rateTable = rateTable;
    }

    @NotNull
    @Override
    public BigDecimal getValue() {
        return this.amount;
    }

    @NotNull
    @Override
    public BigDecimal getValue(@NotNull final CurrencyEnum rateEnum) throws IOException {
        if (rateEnum.equals(this.base)) {
            return getValue();
        }
        return this.amount.multiply(this.rateTable.getRate(base, rateEnum));
    }

    @NotNull
    @Override
    public BigDecimal add(@NotNull final BigDecimal amount) {
        this.amount = this.amount.add(amount);
        return this.amount;
    }

    @NotNull
    @Override
    public BigDecimal multiply(@NotNull final BigDecimal amount) {
        this.amount = this.amount.multiply(amount);
        return this.amount;
    }

    @NotNull
    @Override
    public BigDecimal divide(@NotNull final BigDecimal amount, int scale, final RoundingMode roundingMode) {
        this.amount = this.amount.divide(amount, scale, roundingMode);
        return this.amount;
    }

    @NotNull
    @Override
    public BigDecimal add(@NotNull final IMoney amount) throws IOException {
        return add(amount.getValue(base));
    }

    @NotNull
    @Override
    public BigDecimal multiply(@NotNull final IMoney amount) throws IOException {
        return multiply(amount.getValue(base));
    }

    @NotNull
    @Override
    public BigDecimal divide(@NotNull final IMoney amount, int scale, final RoundingMode roundingMode) throws IOException {
        return divide(amount.getValue(base), scale, roundingMode);
    }
}
