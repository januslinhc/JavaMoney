package org.janus.money;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

class MoneyTests {
    IRateTable rateTable = (from, to) -> BigDecimal.TEN;

    @Test
    void moneyEqual() {
        boolean expected = true;
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ZERO);
        IMoney money2 = new Money(CurrencyEnum.USD, rateTable, BigDecimal.ZERO);
        boolean actual = money.equals(money2);
        Assert.assertEquals(expected, actual);
        long moneyHash = money.hashCode();
        long money2Hash = money2.hashCode();
        Assert.assertNotEquals(moneyHash, money2Hash);
    }

    @Test
    void moneyEqual2() {
        boolean expected = false;
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        IMoney money2 = new Money(CurrencyEnum.USD, rateTable, BigDecimal.ONE);
        boolean actual = money.equals(money2);
        Assert.assertEquals(expected, actual);
        long moneyHash = money.hashCode();
        long money2Hash = money2.hashCode();
        Assert.assertNotEquals(moneyHash, money2Hash);
    }

    @Test
    void moneyEqual3() {
        boolean expected = true;
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        IMoney money2 = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        boolean actual = money.equals(money2);
        Assert.assertEquals(expected, actual);
        long moneyHash = money.hashCode();
        long money2Hash = money2.hashCode();
        Assert.assertEquals(moneyHash, money2Hash);
    }

    @Test
    void shouldAbleToGetBase() {
        CurrencyEnum expected = CurrencyEnum.HKD;
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        CurrencyEnum actual = money.getBase();
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToGetValueOne() {
        BigDecimal expected = BigDecimal.ONE;
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.getValue();
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToGetValueZero() {
        BigDecimal expected = BigDecimal.ZERO;
        IMoney money = new Money(CurrencyEnum.HKD, rateTable);
        BigDecimal actual = money.getValue();
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToGetValueHKD() throws IOException {
        BigDecimal expected = BigDecimal.ONE;
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.getValue(CurrencyEnum.HKD);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToGetValueUSD() throws IOException {
        BigDecimal expected = BigDecimal.TEN;
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.getValue(CurrencyEnum.USD);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToAddBigDecimal() {
        BigDecimal expected = BigDecimal.valueOf(2);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.add(BigDecimal.ONE);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToAddMoney() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(2);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        IMoney money2 = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.add(money2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToAddMoneyWithOtherCurrency() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(11);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        IMoney money2 = new Money(CurrencyEnum.USD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.add(money2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToMultiplyBigDecimal() {
        BigDecimal expected = BigDecimal.valueOf(2);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.multiply(BigDecimal.valueOf(2));
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToMultiplyMoney() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(4);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.valueOf(2));
        IMoney money2 = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.valueOf(2));
        BigDecimal actual = money.multiply(money2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToMultiplyMoneyWithOtherCurrency() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(20);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.valueOf(2));
        IMoney money2 = new Money(CurrencyEnum.USD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.multiply(money2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToDivideBigDecimal() {
        BigDecimal expected = BigDecimal.valueOf(5.0000);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.TEN);
        BigDecimal actual = money.divide(BigDecimal.valueOf(2), 4, RoundingMode.HALF_UP);
        Assert.assertEquals(0, expected.compareTo(actual));
    }

    @Test
    void shouldAbleToDivideMoney() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(5.0000);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.TEN);
        IMoney money2 = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.valueOf(2));
        BigDecimal actual = money.divide(money2, 4, RoundingMode.HALF_UP);
        Assert.assertEquals(0, expected.compareTo(actual));
    }

    @Test
    void shouldAbleToDivideMoneyWithOtherCurrency() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(2.0000);
        IMoney money = new Money(CurrencyEnum.HKD, rateTable, BigDecimal.valueOf(20));
        IMoney money2 = new Money(CurrencyEnum.USD, rateTable, BigDecimal.ONE);
        BigDecimal actual = money.divide(money2, 4, RoundingMode.HALF_UP);
        Assert.assertEquals(0, expected.compareTo(actual));
    }
}
