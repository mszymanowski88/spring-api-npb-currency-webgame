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
    MathContext m = new MathContext(3);

    @Autowired
    public CurrencyGameController(CurrencyGameService currencyGameservice) {
        this.currencyGameService = currencyGameservice;
        rate = currencyGameService.getEurRate();
        rateToGuess = BigDecimal.valueOf(rate.getMid()).round(m);
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





    @PostMapping("/start")
    public String userGuess(@Valid @ModelAttribute ("userInput") Rate rate, BindingResult bindingResult) throws ParseException {

        double numberos ;

        if (bindingResult.hasErrors()) {
            return "start";
        }

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

        BigDecimal input =

                BigDecimal.valueOf(numberos).round(m);

        System.out.println("rate: "+ rateToGuess);
        System.out.println("input "+ input);
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

    @GetMapping("/hint")
    public String hint(Model model) {

        model.addAttribute("low", currencyGameService.hintLow(rate.getMid()));
        model.addAttribute("high", currencyGameService.hintHigh(rate.getMid()));
        return "hint";
    }


}
