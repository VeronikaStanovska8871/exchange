package sk.kosickaakademia.exchange.calc;

import sk.kosickaakademia.exchange.api.ApiRequest;
import sk.kosickaakademia.exchange.mongo.Mongo;

import java.util.*;

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

    private Map<String, Double> calculate(double eur, String[] ratesGui){
        if (eur<0){
            System.out.println("Cant be negative value");
            return null;
        }
        new Mongo().writeData(eur,ratesGui);
        Set<String> set= new HashSet<>();
        Collections.addAll(set,ratesGui);
        return null;
    }
}
