package org.janus.money;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public interface IMoney {
    @NotNull
    BigDecimal getValue();

    @NotNull
    BigDecimal getValue(@NotNull CurrencyEnum rateEnum) throws IOException;

    @NotNull
    BigDecimal add(@NotNull BigDecimal amount);

    @NotNull
    BigDecimal multiply(@NotNull BigDecimal amount);

    @NotNull
    BigDecimal divide(@NotNull BigDecimal amount, int scale, RoundingMode roundingMode);

    @NotNull
    BigDecimal add(@NotNull IMoney amount) throws IOException;

    @NotNull
    BigDecimal multiply(@NotNull IMoney amount) throws IOException;

    @NotNull
    BigDecimal divide(@NotNull IMoney amount, int scale, RoundingMode roundingMode) throws IOException;
}
