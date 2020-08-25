package org.janus.money;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public interface IMoneyFactory {
    IMoney create();

    IMoney create(@NotNull BigDecimal amount);
}
