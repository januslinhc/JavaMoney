package org.janus.money;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.janus.money.dto.RateRecordDTO;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeRateAPIRateTable implements IRateTable {
    final OkHttpClient client = new OkHttpClient();
    final ObjectMapper mapper = new ObjectMapper();

    @NotNull
    @Override
    public BigDecimal getRate(@NotNull final CurrencyEnum from, @NotNull final CurrencyEnum to) throws IOException {
        Request request = new Request.Builder()
                .url(String.format("https://api.exchangeratesapi.io/latest?symbols=%s,%s", from.getValue(), to.getValue()))
                .build();
        Response responses = client.newCall(request).execute();
        RateRecordDTO rate = mapper.readValue(responses.body().string(), RateRecordDTO.class);
        return BigDecimal.ONE.divide(rate.getRate(from), 8, RoundingMode.HALF_UP).multiply(rate.getRate(to));
    }
}
