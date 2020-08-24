package org.janus.money.dto;

import org.janus.money.CurrencyEnum;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public interface IRateRecordDTO {
    BigDecimal getRate(@NotNull CurrencyEnum key);
}
