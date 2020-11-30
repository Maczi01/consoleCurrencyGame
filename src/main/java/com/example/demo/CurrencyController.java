package com.example.demo;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@Controller
public class CurrencyController {


    public CurrencyController() {
//        System.out.println(getCurrency());
//        getValueFromUser();
    }

    public double getCurrency() {
        var restTemplate = new RestTemplate();
        Currency currency = restTemplate
                .getForObject("https://v6.exchangerate-api.com/v6/ab33143b55d3dc0b1f57ddd3/latest/eur", Currency.class);
        return currency.getConversionRates().getPLN();
    }

//    public void game() {
//        double currency = getCurrency();
//        String valueFromUser = getValueFromUser();
//        if (currency > Double.valueOf(valueFromUser)) {
//            tooMuchMessage();
//        } else {
//            notEnoughMessage();
//        }
//    }

    public void showMenu() {
        System.out.println("Ile PLN musisz przeznaczyc na zakup 1 euro?");
    }

    public void tooMuchMessage() {
        System.out.println("Za duzo!");
    }

    public void notEnoughMessage() {
        System.out.println("Za malo!");
    }


}
