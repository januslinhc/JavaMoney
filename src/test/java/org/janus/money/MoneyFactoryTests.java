package org.janus.money;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

class MoneyFactoryTests {
    IMoneyFactory moneyFactory;
    IRateTable rateTable = new IRateTable() {
        @NotNull
        @Override
        public BigDecimal getRate(@NotNull CurrencyEnum from, @NotNull CurrencyEnum to) throws IOException {
            return BigDecimal.ONE;
        }
    };

    @Test
    void shouldAbleToCreateMoney1() {
        moneyFactory = new MoneyFactory(rateTable, CurrencyEnum.HKD);
        IMoney expected = new Money(CurrencyEnum.HKD, rateTable);
        IMoney actual = moneyFactory.create();
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToCreateMoney2() {
        moneyFactory = new MoneyFactory(rateTable, CurrencyEnum.USD);
        IMoney expected = new Money(CurrencyEnum.HKD, rateTable);
        IMoney actual = moneyFactory.create();
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToCreateMoney3() {
        moneyFactory = new MoneyFactory(rateTable, CurrencyEnum.HKD);
        IMoney expected = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        IMoney actual = moneyFactory.create(BigDecimal.ONE);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToCreateMoney4() {
        moneyFactory = new MoneyFactory(rateTable, CurrencyEnum.USD);
        IMoney expected = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        IMoney actual = moneyFactory.create(BigDecimal.ONE);
        Assert.assertNotEquals(expected, actual);
    }
}