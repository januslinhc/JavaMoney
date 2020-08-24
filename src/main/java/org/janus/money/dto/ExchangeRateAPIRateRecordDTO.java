package org.janus.money.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.janus.money.CurrencyEnum;
import org.janus.money.IRateTable;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
public class ExchangeRateAPIRateRecordDTO implements IRateRecordDTO {
    private Map<String, Double> rates;
    private String base;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Override
    public BigDecimal getRate(@NotNull final CurrencyEnum key) {
        return BigDecimal.valueOf(rates.get(key.getValue()));
    }
}
