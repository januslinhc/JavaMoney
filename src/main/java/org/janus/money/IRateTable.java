package org.janus.money;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigDecimal;

public interface IRateTable {
    @NotNull
    BigDecimal getRate(@NotNull CurrencyEnum from, @NotNull CurrencyEnum to) throws IOException;
}
