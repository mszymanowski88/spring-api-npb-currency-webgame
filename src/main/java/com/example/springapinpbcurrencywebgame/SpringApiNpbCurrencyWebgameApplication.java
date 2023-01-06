package com.example.springapinpbcurrencywebgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class SpringApiNpbCurrencyWebgameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringApiNpbCurrencyWebgameApplication.class, args);
        Locale.setDefault(new Locale("en", "US"));
    }

}
