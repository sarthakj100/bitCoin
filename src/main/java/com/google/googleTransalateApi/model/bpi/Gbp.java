package com.google.googleTransalateApi.model.bpi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gbp {
    String code;
    String symbol;
    String rate;
    String description;
    String rate_float;
}
