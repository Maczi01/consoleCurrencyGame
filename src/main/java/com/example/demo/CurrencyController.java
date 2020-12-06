package com.example.demo;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@Controller
public class CurrencyController {

    private Scanner scanner = new Scanner(System.in);
    private final String LINK_TO_API = "https://v6.exchangerate-api.com/v6/ab33143b55d3dc0b1f57ddd3/latest/eur";

    public CurrencyController() {
        game();
    }

    public void game(){
        System.out.println("Sprobuj odgadnac dzisiejszy kurs EUR w przeliczeniu na PLN");
        getCurrencyAndValueFromUser();
    }

    public double getCurrency() {
        var restTemplate = new RestTemplate();
        Currency currency = restTemplate
                .getForObject(LINK_TO_API, Currency.class);
        String pln = currency.getConversionRates().getPLN().toString().substring(0,4);
        System.out.println(pln);
        return Double.parseDouble(pln);
    }

    public double getValueFromUser() {
        String valueToCheck;
        do {
            System.out.println("Podaj wartosc");
            valueToCheck = scanner.nextLine();
        } while (!isConvertibleToDouble(valueToCheck));
        return Double.parseDouble(valueToCheck);
    }

    public void getCurrencyAndValueFromUser() {
        double currency = getCurrency();
        double valueFromUser = getValueFromUser();
        while (!compareValues(currency, valueFromUser)) {
            valueFromUser = getValueFromUser();
        }
        System.out.println("Wygrales!");
        scanner.close();
    }

    private boolean compareValues(double currency, double valueFromUser) {
        if (currency == valueFromUser) {
            return true;
        } else if (currency > valueFromUser) {
            notEnoughMessage();
            return false;
        } else
            tooMuchMessage();
        return false;
    }


    private boolean isConvertibleToDouble(String valueToCheck) {
        boolean parsable = NumberUtils.isParsable(valueToCheck);
        if (!parsable) {
            System.out.println("Nieprawidłowy format, sprobuj ponownie");
        }
        return parsable;
    }

    public void tooMuchMessage() {
        System.out.println("Za duzo!");
    }

    public void notEnoughMessage() {
        System.out.println("Za malo!");
    }


}
