package main;

import main.calc.CalcExchangeRates;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Exchange rates");
        Set<String> set = new HashSet<>();
        set.add("USD");
        set.add("HUF");
        set.add("CZK");
        set.add("BTC");
        CalcExchangeRates calcExchangeRates = new CalcExchangeRates();
        calcExchangeRates.calculate(90);
    }
}
