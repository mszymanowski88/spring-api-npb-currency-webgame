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

@Controller
public class CurrencyGameController {


    private final CurrencyGameService currencyGameService;
    final private MathContext m = new MathContext(3);
    final private Rate rate;
    final private BigDecimal rateToGuess;
    private BigDecimal input;

    @Autowired
    public CurrencyGameController(CurrencyGameService currencyGameservice) {
        this.currencyGameService = currencyGameservice;
        rate = currencyGameService.getEurRate();
        rateToGuess = BigDecimal.valueOf(rate.getMid()).round(m);

    }


    @GetMapping("/start")
    public String getCurrency(Model model) {


        model.addAttribute("rate", rate.getCode());
        model.addAttribute("rateCode", rate.getMid());
        model.addAttribute("userInput", new Rate());
        model.addAttribute("userGuess", input);
        model.addAttribute("result", currencyGameService.getResult());
        model.addAttribute("counter", currencyGameService.getCounter());


        return "start";
    }


    @PostMapping("/start")
    public String userGuess(@Valid @ModelAttribute("userInput") Rate rate, BindingResult bindingResult)  {


        if (bindingResult.hasErrors()) {
            return "start";
        }

        input = BigDecimal.valueOf(rate.getMid()).round(m);


        currencyGameService.hint(input, rateToGuess);

        return "redirect:/start";

    }

    @GetMapping("/hint")
    public String hint(Model model) {

        model.addAttribute("low", currencyGameService.hintLow(rate.getMid()));
        model.addAttribute("high", currencyGameService.hintHigh(rate.getMid()));
        return "hint";
    }


}
