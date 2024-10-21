package com.google.googleTransalateApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.googleTransalateApi.model.CurrencyXchange;
import com.google.googleTransalateApi.model.Time;
//import com.google.googleTransalateApi.response.Response;
import com.google.googleTransalateApi.model.bpi.Eur;
import com.google.googleTransalateApi.model.bpi.Gbp;
import com.google.googleTransalateApi.model.bpi.Usd;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class TransalateService {

    public String bitcoinPrice() {

        String imp="";
        RestTemplate restTemplate = new RestTemplate();
        String s = restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);
        JSONObject jsonObject = new JSONObject(s);
        JSONObject time = jsonObject.getJSONObject("time");
        String disclaimer = jsonObject.getString("disclaimer");
        String chartName = jsonObject.getString("chartName");
        JSONObject bpi = jsonObject.getJSONObject("bpi");
        JSONObject usd = bpi.getJSONObject("USD");
        JSONObject gbp = bpi.getJSONObject("GBP");
        JSONObject eur = bpi.getJSONObject("EUR");

        System.out.println(time);
        ObjectMapper objectMapper = new ObjectMapper();
        Time time1 = null;
        Usd usd1 = null;
        Gbp gbp1 = null;
        Eur eur1 = null;
        try {
            time1 = objectMapper.readValue(time.toString(), Time.class);
            System.out.println(disclaimer);
            System.out.println(chartName);
            usd1 = objectMapper.readValue(usd.toString(), Usd.class);
            String curr = currencyExchange(usd1.getRate());
            CurrencyXchange currencyXchange = objectMapper.readValue(curr, CurrencyXchange.class);
            imp = currencyXchange.getConverted();
            gbp1 = objectMapper.readValue(gbp.toString(), Gbp.class);
            eur1 = objectMapper.readValue(eur.toString(), Eur.class);
            System.out.println(time1);
            System.out.println(gbp1);
            System.out.println(eur1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imp;
    }
    public String currencyExchange(String rate){
        RestTemplate restTemplate = new RestTemplate();
        String rate1 = rate.replace(",", "");
        String url = UriComponentsBuilder.fromHttpUrl("https://anyapi.io/api/v1/exchange/convert").
                queryParam("base","USD").
                queryParam("to","INR").
                queryParam("amount",rate1).
                queryParam("apiKey","erak7dosa3gt3bqda7ljigcoav3s1emug9a5v724jot8j8fhmr823q8").
                toUriString();

        String response = restTemplate.getForObject(url, String.class);
        return response;
    }

    public ResponseEntity<String> transalateMessage(String message) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://google-translate1.p.rapidapi.com/language/translate/v2/detect";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", "47e124d9c6msh5d4b7f59882d2f9p1b6dc2jsnb97b81c8b5ad");
        headers.set("x-rapidapi-host", "google-translate1.p.rapidapi.com");
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("q", message);
        body.add("target", "es");
        body.add("source", "en");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response;

        try{
            response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

        }
        catch (Exception exception){
            return new ResponseEntity<>("Bad request",HttpStatus.BAD_REQUEST);
        }
        return response;
    }
    }

