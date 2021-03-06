package org.janus.money;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money implements IMoney {
    private final CurrencyEnum base;
    private BigDecimal amount;
    private final IRateTable rateTable;

    Money(@NotNull final CurrencyEnum base, @NotNull final IRateTable rateTable) {
        this.base = base;
        this.amount = BigDecimal.ZERO;
        this.rateTable = rateTable;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Money)) {
            return false;
        }
        final Money target = (Money) obj;
        if (this.amount.equals(BigDecimal.ZERO) && this.amount.equals(target.amount)) {
            return true;
        }
        return this.amount.equals(target.amount) && this.base.equals(target.base);
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, amount);
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
    public CurrencyEnum getBase() {
        return this.base;
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
