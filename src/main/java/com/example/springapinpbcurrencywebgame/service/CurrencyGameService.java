package com.example.springapinpbcurrencywebgame.service;

import com.example.springapinpbcurrencywebgame.api.AvailableCurrency;
import com.example.springapinpbcurrencywebgame.api.Rate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CurrencyGameService {

 AvailableCurrency[] availableCurrency;


    public CurrencyGameService() {
        RestTemplate restTemplate = new RestTemplate();

        this.availableCurrency = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/tables/a", AvailableCurrency[].class);

    }


    public List<Rate> getAllRates()
    {
      return availableCurrency[0].getRates();


    }

    public Rate getEurRate()
    {
        return getAllRates().get(7);


    }

    public double hintLow(double rate) {

        BigDecimal low = new BigDecimal("0.9");
        BigDecimal rateValue = BigDecimal.valueOf(rate);
        BigDecimal lowHint = rateValue.multiply(low);


        return lowHint.doubleValue();
    }


    public double hintHigh(double rate) {

        BigDecimal high = new BigDecimal("1.1");
        BigDecimal rateValue = BigDecimal.valueOf(rate);
        BigDecimal highHint = rateValue.multiply(high);

        return highHint.doubleValue();
    }





}
