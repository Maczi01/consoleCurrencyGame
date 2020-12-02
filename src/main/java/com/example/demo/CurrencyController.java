package com.example.demo;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@Controller
public class CurrencyController {

    private Scanner scanner = new Scanner(System.in);
    private double value;

    public CurrencyController() {
//        System.out.println(getCurrency());
        value = getCurrency();
        getCurrencyAndValueFromUser();
    }

    public double getCurrency() {
        var restTemplate = new RestTemplate();
        Currency currency = restTemplate
                .getForObject("https://v6.exchangerate-api.com/v6/ab33143b55d3dc0b1f57ddd3/latest/eur", Currency.class);
        Double pln = currency.getConversionRates().getPLN();
        System.out.println(pln);
        return pln;
    }

    public double getValueFromUser() {
        String valueToCheck;
        do {
            System.out.println("Podaj wartosc");
            valueToCheck = scanner.nextLine();
        } while (!isConvertibleToDouble(valueToCheck));
//        scanner.close();
        return Double.parseDouble(valueToCheck);
    }

//    @EventListener(ApplicationReadyEvent.class)
    public void getCurrencyAndValueFromUser() {
        double currency = getCurrency();
        double valueFromUser = value;
        while (!compareValues(currency, valueFromUser)) {
            valueFromUser = getValueFromUser();
        }
        System.out.println("Wygrales!");

    }

    private boolean compareValues(double currency, double valueFromUser) {
        if (currency == valueFromUser) {
            return true;
        } else if (currency > valueFromUser) {
            System.out.println("Za duzo!");
            return false;
        } else
            System.out.println("za malo!");
        return false;
    }


    private boolean isConvertibleToDouble(String valueToCheck) {
        boolean parsable = NumberUtils.isParsable(valueToCheck);
        if (parsable) {
            System.out.println("ok, dobra wartosc");
        } else {
            System.out.println("jeszcze raz");
        }
        return parsable;
    }

//    public void showMenu() {
//        System.out.println("Ile PLN musisz przeznaczyc na zakup 1 euro?");
//    }

    public void tooMuchMessage() {
        System.out.println("Za duzo!");
    }

    public void notEnoughMessage() {
        System.out.println("Za malo!");
    }


}
