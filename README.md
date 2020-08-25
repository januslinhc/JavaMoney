# Java Money
[![MIT License](https://img.shields.io/apm/l/atomic-design-ui.svg?)](https://github.com/januslinhc/JavaMoney)

## Demo
```java
class Demo {
    public static void main(String[] args) throws IOException {
      IRateTable rateTable = new ExchangeRateAPIRateTable();
      IMoneyFactory hkdFactory = new MoneyFactory(rateTable, CurrencyEnum.HKD);
      IMoneyFactory usdFactory = new MoneyFactory(rateTable, CurrencyEnum.USD);
      IMoney zeroHKD = hkdFactory.create();
      IMoney tenUSD = usdFactory.create(BigDecimal.TEN);
      zeroHKD.add(tenUSD);
      zeroHKD.getValue(); //77.5025576
      zeroHKD.getValue(CurrencyEnum.USD); //10
    }
}
```
