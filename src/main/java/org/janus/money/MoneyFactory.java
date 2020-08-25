package org.janus.money;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class MoneyFactory implements IMoneyFactory {
    private final IRateTable rateTable;
    private final CurrencyEnum base;

    MoneyFactory(@NotNull final IRateTable rateTable, @NotNull final CurrencyEnum base) {
        this.rateTable = rateTable;
        this.base = base;
    }

    @Override
    public IMoney create() {
        return new Money(base, rateTable);
    }

    @Override
    public IMoney create(@NotNull BigDecimal amount) {
        return new Money(base, rateTable, amount);
    }
}
