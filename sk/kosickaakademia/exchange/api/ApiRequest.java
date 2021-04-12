package sk.kosickaakademia.exchange.api;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ApiRequest {
    private static final String key = "0d2198cb88b6d1af6c8d02f3c6940c64";

    public Map getExchangeRates(Set<String> rates){
        if(rates==null || rates.size() ==0)
            return null;
        return parseData(rates);
    }
    private String getRatesFromAPIServer(){
        try {
            URL url = new URL("http://api.exchangeratesapi.io/v1/latest?access_key="+key+"&format=1");
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
}
