package org.janus.money;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

class MoneyTest {
    IRateTable rateTable = new IRateTable() {
        @NotNull
        @Override
        public BigDecimal getRate(@NotNull CurrencyEnum from, @NotNull CurrencyEnum to) throws IOException {
            return BigDecimal.TEN;
        }
    };

    @Test
    public void shouldAbleToGetValueOne() {
        BigDecimal expected = BigDecimal.ONE;
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.getValue();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldAbleToGetValueZero() {
        BigDecimal expected = BigDecimal.ZERO;
        IMoney money = new Money(CurrencyEnum.HKD, rateTable);
        BigDecimal actual = money.getValue();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldAbleToGetValueHKD() throws IOException {
        BigDecimal expected = BigDecimal.ONE;
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.getValue(CurrencyEnum.HKD);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldAbleToGetValueUSD() throws IOException {
        BigDecimal expected = BigDecimal.TEN;
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.getValue(CurrencyEnum.USD);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldAbleToAddBigDecimal() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(2);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.add(BigDecimal.ONE);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldAbleToAddMoney() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(2);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        IMoney money2 = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.add(money2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldAbleToAddMoneyWithOtherCurrency() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(11);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        IMoney money2 = new Money(CurrencyEnum.USD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.add(money2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldAbleToMultiplyBigDecimal() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(2);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.multiply(BigDecimal.valueOf(2));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldAbleToMultiplyMoney() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(4);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.valueOf(2));
        IMoney money2 = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.valueOf(2));
        BigDecimal actual = money.multiply(money2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldAbleToMultiplyMoneyWithOtherCurrency() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(20);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.valueOf(2));
        IMoney money2 = new Money(CurrencyEnum.USD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.multiply(money2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldAbleToDivideBigDecimal() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(5.0000);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.TEN);
        BigDecimal actual = money.divide(BigDecimal.valueOf(2), 4, RoundingMode.HALF_UP);
        Assert.assertEquals(0, expected.compareTo(actual));
    }

    @Test
    public void shouldAbleToDivideMoney() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(5.0000);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.TEN);
        IMoney money2 = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.valueOf(2));
        BigDecimal actual = money.divide(money2, 4, RoundingMode.HALF_UP);
        Assert.assertEquals(0, expected.compareTo(actual));
    }

    @Test
    public void shouldAbleToDivideMoneyWithOtherCurrency() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(2.0000);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.valueOf(20));
        IMoney money2 = new Money(CurrencyEnum.USD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.divide(money2, 4, RoundingMode.HALF_UP);
        Assert.assertEquals(0, expected.compareTo(actual));
    }
}
