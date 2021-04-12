package sk.kosickaakademia.exchange.calc;

import sk.kosickaakademia.exchange.api.ApiRequest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CalcExchangeRates {
    private static final String[] rates= new String[]{"USD","CZK","HUF","BTC","PLN"};

    public void calculate(double eur){
        if(eur<0){
            System.out.println("Can not be negative value!");
            return;
        }
        Set<String> set = new HashSet<>();
        Collections.addAll(set, rates);
        ApiRequest apiRequest=new ApiRequest();
        Map map = apiRequest.getExchangeRates(set);

        for(String temp:rates){
            if(map.containsKey(temp)){
                double value = (double)map.get(temp);
                double result = eur*value;
                print("EUR", temp, eur, result, value);
            }
        }
    }

    private void print(String from, String to, double eur, double result, double rate){
        System.out.println(eur +" "+from+" = "+result+" "+to+" (exchange rate: "+rate+")");
    }
}
