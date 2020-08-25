package org.janus.money;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.janus.money.dto.ExchangeRateAPIRateRecordDTO;
import org.janus.money.dto.IRateRecordDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

class ExchangeRateAPIRateTableTests {
    IRateTable rateTable = new ExchangeRateAPIRateTable();
    OkHttpClient client = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldAbleToGetRate() throws IOException {
        Request request = new Request.Builder()
                .url(String.format("https://api.exchangeratesapi.io/latest?symbols=%s,%s", CurrencyEnum.HKD, CurrencyEnum.USD))
                .build();
        Response responses = client.newCall(request).execute();
        IRateRecordDTO rate = mapper.readValue(responses.body().string(), ExchangeRateAPIRateRecordDTO.class);

        BigDecimal expected = BigDecimal.ONE.divide(rate.getRate(CurrencyEnum.HKD), 8, RoundingMode.HALF_UP).multiply(rate.getRate(CurrencyEnum.USD));
        BigDecimal actual = rateTable.getRate(CurrencyEnum.HKD, CurrencyEnum.USD);

        Assert.assertEquals(expected, actual);
    }

    @Test
    void shouldAbleToGetRate2() throws IOException {
        BigDecimal expected = BigDecimal.ONE;
        BigDecimal actual = rateTable.getRate(CurrencyEnum.HKD, CurrencyEnum.HKD);

        Assert.assertEquals(expected, actual);
    }
}
