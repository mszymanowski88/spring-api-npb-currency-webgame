package com.example.springapinpbcurrencywebgame.controller;

import com.example.springapinpbcurrencywebgame.api.Rate;
import com.example.springapinpbcurrencywebgame.service.CurrencyGameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Controller
public class CurrencyGameController {


private final CurrencyGameService currencyGameService;

    private Rate rate;
    private  BigDecimal rateToGuess;
    private String result;
    private int counter = 0;
    MathContext m = new MathContext(2);

    @Autowired
    public CurrencyGameController(CurrencyGameService currencyGameservice) {
        this.currencyGameService = currencyGameservice;
        rate = currencyGameService.getEurRate();
        rateToGuess = BigDecimal.valueOf(rate.getMid()).round(m);
//        rateToGuess = BigDecimal.valueOf(rate.getMid()).setScale(2, RoundingMode.HALF_UP).round(m);
//        rateToGuess = BigDecimal.valueOf(2.5);
    }


    @GetMapping("/start")
            public String getCurrency(Model model)
    {

        model.addAttribute("rate", rate.getCode());
        model.addAttribute("rateCode", rate.getMid());
        model.addAttribute("userInput", new Rate());
        model.addAttribute("result", result);
        model.addAttribute("counter", counter);



        return "start";
    }

  public String inputofuser(BigDecimal bigDecimal)
  {
      return String.format(Locale.ROOT, "%.2f", bigDecimal.doubleValue());


  }
//
//    @PostMapping("/start")
//    public String inputValidation(@Valid Rate rate, BindingResult bindingResult )
//    {
//        {
//
//            if (bindingResult.hasErrors()) {
//                return "start";
//            }
//
//
//        return "redirect:/start";
//    }


    @PostMapping("/start")
    public String userGuess(@Valid @ModelAttribute ("userInput") Rate rate,BindingResult bindingResult  ) throws ParseException {

        if (bindingResult.hasErrors()) {
            return "start";
        }

        double numberos ;

        String userInput = rate.getCode();
        if(userInput.contains(","))

        {
            System.out.println("user napisa "+userInput);
            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
            Number number = format.parse(userInput);
            numberos = number.doubleValue()/100;

            System.out.println("from if" + numberos);

        }
        else
        {
            numberos=  Double.parseDouble(userInput);
            System.out.println("else " + numberos);
        }

//        System.out.println("outside " + numberos);
//
        BigDecimal input =

                BigDecimal.valueOf(numberos).setScale(1, RoundingMode.HALF_UP);


        if (input.compareTo(rateToGuess) < 0) {

            System.out.println("numeros > 0 " +numberos);
            result = "too much, try one more time";
            counter++;


        }
        if (input.equals(rateToGuess)) {
            System.out.println("numeros == 0 " +numberos);
            result = "Congratulations! You won!";
        }

        if (input.compareTo(rateToGuess) < 0) {
            System.out.println("numeros < 0 " +numberos);
            result = "too less, try one more time";
            counter++;
        }




        if (input.compareTo(rateToGuess) > 0) {

            System.out.println("numeros > 0 " +numberos);
            result = "too much, try one more time";
            counter++;


        }
        if (input.equals(rateToGuess)) {
            System.out.println("numeros == 0 " +numberos);
            result = "Congratulations! You won!";
        }

        if (input.compareTo(rateToGuess) < 0) {
            System.out.println("numeros < 0 " +numberos);
            result = "too less, try one more time";
            counter++;
        }

        return "redirect:/start";

    }






//    @PostMapping("/start")
//    public String userGuess(@ModelAttribute Rate rate) throws ParseException {
//
//        double numberos ;
//
//        String userInput = rate.getCode();
//        if(userInput.contains(","))
//
//        {
//            System.out.println("user napisa "+userInput);
//            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
//            Number number = format.parse(userInput);
//          numberos = number.doubleValue()/100;
//
//            System.out.println("from if" + numberos);
//
//        }
//        else
//        {
//       numberos=  Double.parseDouble(userInput);
//            System.out.println("else " + numberos);
//        }
//
////        System.out.println("outside " + numberos);
////
//        BigDecimal input =
//
//                BigDecimal.valueOf(numberos).setScale(1, RoundingMode.HALF_UP);
//
//
//        if (input.compareTo(rateToGuess) > 0) {
//
//            System.out.println("numeros > 0 " +numberos);
//            result = "too much, try one more time";
//            counter++;
//
//
//        }
//        if (input.equals(rateToGuess)) {
//            System.out.println("numeros == 0 " +numberos);
//            result = "Congratulations! You won!";
//        }
//
//        if (input.compareTo(rateToGuess) < 0) {
//            System.out.println("numeros < 0 " +numberos);
//            result = "too less, try one more time";
//            counter++;
//        }
//
//        return "redirect:/start";
//
//    }

//    @PostMapping("/start")
//    public String userGuess(@ModelAttribute Rate rate) {
//
////        BigDecimal inputToParse = new BigDecimal(0.00);
////        String  bigDecimalToparse = inputToParse.toString();
////        String decimalParsed = String.format(Locale.ROOT, "%.2f",bigDecimalToparse);
////        BigDecimal input = new BigDecimal(decimalParsed);
//     BigDecimal input = BigDecimal.valueOf(rate.getMid()).setScale(2, RoundingMode.HALF_UP);
//
//
//
////        BigDecimal input = BigDecimal.valueOf(rate.getMid()).setScale(4, RoundingMode.HALF_UP);
//
//
//        if (input.compareTo(rateToGuess) > 0) {
//
//            result = "too much, try one more time";
//            counter++;
//
//
//        }
//        if (input.equals(rateToGuess)) {
//
//            result = "Congratulations! You won!";
//        }
//
//        if (input.compareTo(rateToGuess) < 0) {
//
//            result = "too less, try one more time";
//            counter++;
//        }
//
//        return "redirect:/start";
//
//    }

    @GetMapping("/error")
    public String incorrectUserInput(Model model) {

        return "error";
    }

    @GetMapping("/hint")
    public String hint(Model model) {

        model.addAttribute("low", currencyGameService.hintLow(rate.getMid()));
        model.addAttribute("high", currencyGameService.hintHigh(rate.getMid()));

        return "hint";
    }


}
