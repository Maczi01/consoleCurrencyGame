package com.example.demo;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;

public class LoadData {

    private Scanner scanner = new Scanner(System.in);


    public String getValueFromUser() {
        String valueToCheck;
        do {
            System.out.println("Podaj wartosc");
            valueToCheck = scanner.nextLine();
        } while (!isConvertibleToDouble(valueToCheck));
        System.out.println("great!");
//        isConvertibleToDouble(valueToCheck);

        scanner.close();
        return "";
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
}
