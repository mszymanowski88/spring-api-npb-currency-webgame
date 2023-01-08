package com.example.springapinpbcurrencywebgame.service;

import com.example.springapinpbcurrencywebgame.api.AvailableCurrency;
import com.example.springapinpbcurrencywebgame.api.Rate;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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


    public int getRandom(int array) {
        Random rnd = new Random();

        return rnd.nextInt(array);
    }


    public Rate getRandomRate() {
        List<Rate> rates = getAllRates();
        int randomRate = getRandom(rates.size());
        return rates.get(randomRate);

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

    @EventListener(ApplicationReadyEvent.class)
    public void test() throws ParseException {

//        Scanner sc = new Scanner(System.in);
//        double inputt = sc.nextDouble();
//        System.out.println("Test");
//
//        double num = 10025000;
        String input = "10,00";
        if(input.contains(","))

        {
            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
            Number number = format.parse(input);
            double d = number.doubleValue();
//            DecimalFormat df = new DecimalFormat();
//            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
//            symbols.setDecimalSeparator(',');
//            symbols.setGroupingSeparator('2');
//            df.setDecimalFormatSymbols(symbols);
//            df.parse(input);
//            System.out.println(input);
            System.out.println(d);

        }


//        public void inputHint(Rate rate) {
//
//            int counter = 0;
//
//            if (bindingResult.hasErrors()) {
//                return "start";
//            }
//
//            double numberos ;
//
//            String userInput = rate.getCode();
//            if(userInput.contains(","))
//
//            {
//                System.out.println("user napisa "+userInput);
//                NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
//                Number number = format.parse(userInput);
//                numberos = number.doubleValue()/100;
//
//                System.out.println("from if" + numberos);
//
//            }
//            else
//            {
//                numberos=  Double.parseDouble(userInput);
//                System.out.println("else " + numberos);
//            }
//
////        System.out.println("outside " + numberos);
////
//            BigDecimal input =
//
//                    BigDecimal.valueOf(numberos).setScale(1, RoundingMode.HALF_UP);
//
////
////        if (input >=  rateToGuess.multiply(rateToGuess)) {
////
////            System.out.println("numeros > 0 " +numberos);
////            result = "too much, try one more time";
////            counter++;
////
////
////        }
////        if (input.equals(rateToGuess)) {
////            System.out.println("numeros == 0 " +numberos);
////            result = "Congratulations! You won!";
////        }
////
////        if (input.compareTo(rateToGuess) < 0) {
////            System.out.println("numeros < 0 " +numberos);
////            result = "too less, try one more time";
////            counter++;
////        }
//
//
//
//
//            if (input.compareTo(rateToGuess) > 0) {
//
//                System.out.println("numeros > 0 " +numberos);
//                result = "too much, try one more time";
//                counter++;
//
//
//            }
//            if (input.equals(rateToGuess)) {
//                System.out.println("numeros == 0 " +numberos);
//                result = "Congratulations! You won!";
//            }
//
//            if (input.compareTo(rateToGuess) < 0) {
//                System.out.println("numeros < 0 " +numberos);
//                result = "too less, try one more time";
//                counter++;
//            }
//
//
//        }
//
////        System.out.println(inputt);
////        System.out.println(df.format(num));
////        System.out.println(getEurRate(7));
//
//
////        System.out.println(getAllRates());
    }

}
