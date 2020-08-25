# Java Money
[![Contributors][contributors-shield]][contributors-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

## Demo
```java
class Demo {
    public static void main(String[] args){
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