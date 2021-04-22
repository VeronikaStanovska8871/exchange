package sk.kosickaakademia.exchange.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ApiRequest {
    public static final String ACCESS_KEY = "0d2198cb88b6d1af6c8d02f3c6940c64";
    public static final String BASE_URL = "https://apilayer.net/api/";
    public static final String ENDPOINT = "convert";

    public Map getExchangeRates(Set<String> rates){
        if(rates==null || rates.size() ==0)
            return null;
        return parseData(rates);
    }
    private String getRatesFromAPIServer(){

        try {
            URL url = new URL(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + "&from=GBP&to=INR&amount=2");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();
                return inline;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map parseData(Set<String> rates){
        String inline = getRatesFromAPIServer();
        if (inline==null)
            return null;
        try {
            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(inline);
            JSONObject obj = (JSONObject) data_obj.get("rates");
            Map<String,Double> maps = new HashMap<>();
            for (String temp:rates){
                if(obj.containsKey(temp)){
                    double value = (double)obj.get(temp);
                    maps.put(temp,value);
                }
            }
            return maps;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
