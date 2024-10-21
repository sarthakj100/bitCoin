package com.google.googleTransalateApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyXchange {
    String base;
    String to;
    String amount;
    String converted;
    String rate;
    String lastUpdate;

}
