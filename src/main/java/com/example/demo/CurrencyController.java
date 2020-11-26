package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class CurrencyController {


    public CurrencyController() {
        getCurrency();
    }

    public void getCurrency(){
        var restTemplate = new RestTemplate();
        Currency currency = restTemplate
                .getForObject("https://v6.exchangerate-api.com/v6/ab33143b55d3dc0b1f57ddd3/latest/eur", Currency.class);
        System.out.println(currency.getConversionRates().getPLN());
    }

}
