package com.example.springapinpbcurrencywebgame.service;

import com.example.springapinpbcurrencywebgame.api.AvailableCurrency;
import com.example.springapinpbcurrencywebgame.api.Rate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CurrencyGameService {

    private AvailableCurrency[] availableCurrency;
    private int counter = 0;
    private String result;


    public CurrencyGameService() {
        RestTemplate restTemplate = new RestTemplate();

        this.availableCurrency = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/tables/a", AvailableCurrency[].class);
    }


    public void hint(BigDecimal input, BigDecimal rateToGuess) {
        if (input.doubleValue() > rateToGuess.doubleValue() * 1.1) {

            System.out.println("numeros > 0 " + input);
            result = "freezing, try one more time";
            counter++;


        }

        if (input.doubleValue() < rateToGuess.doubleValue() * 1.1 && input.doubleValue() > rateToGuess.doubleValue()) {

            System.out.println("numeros > 0 " + input);
            result = "cold, try one more time";
            counter++;


        }

        if (input.doubleValue() < rateToGuess.doubleValue() * 1.05 && input.doubleValue() > rateToGuess.doubleValue()) {

            System.out.println("numeros > 0 " + input);
            result = "hot, try one more time";
            counter++;


        }

        if (input.equals(rateToGuess)) {
            System.out.println("numeros == 0 " + input);
            result = "Congratulations! You won!";
        }

        if (input.compareTo(rateToGuess) < 0) {
            System.out.println("numeros < 0 " + input);
            result = "freezing, try one more time";
            counter++;
        }

        if (input.doubleValue() > rateToGuess.doubleValue() * 0.95 && input.doubleValue() < rateToGuess.doubleValue()) {

            System.out.println("numeros > 0 " + input);
            result = "hot, try one more time";
            counter++;


        }


        if (input.doubleValue() > rateToGuess.doubleValue() * 0.90 && input.doubleValue() < rateToGuess.doubleValue() * 0.95 && input.doubleValue() < rateToGuess.doubleValue()) {

            System.out.println("numeros > 0 " + input);
            result = "cold, try one more time";
            counter++;


        }


    }


    public List<Rate> getAllRates() {
        return availableCurrency[0].getRates();


    }

    public Rate getEurRate() {
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

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
