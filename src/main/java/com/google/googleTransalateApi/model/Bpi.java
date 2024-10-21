package com.google.googleTransalateApi.model;

import com.google.googleTransalateApi.model.bpi.Eur;
import com.google.googleTransalateApi.model.bpi.Gbp;
import com.google.googleTransalateApi.model.bpi.Usd;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bpi {
    Usd USD;
    Gbp GBP;
    Eur EUR;

}
