package com.example.PleaseWork.service.impl;

import com.example.PleaseWork.modal.StockData;
import com.example.PleaseWork.repository.StockRepository;
import com.example.PleaseWork.service.StockService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository stockRepository;

    public Map<String, String> convertJSONToHashMap(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        try {
            // Convert JSON string to HashMap
            map = objectMapper.readValue(jsonString, new TypeReference<Map<String, String>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public List<StockData> getClosingPrice(String symbol) {
        List<StockData> data=stockRepository.findAll();
        return null;
    }
    public List<StockData> GetStockData(String symbol) {
        try {

            URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+symbol+"&interval=5min&apikey=demo");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                stockRepository.deleteAll();
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

//                System.out.println(informationString);


                //JSON simple library Setup with Maven is used to convert strings to JSON
                JSONParser parse = new JSONParser();
                JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));

                //Get the first JSON object in the JSON array


                JSONObject countryData = (JSONObject)dataObject.get("Time Series (5min)");

//                System.out.println(countryData.keySet());

                Object[] Keys=countryData.keySet().toArray();
                ArrayList<StockData> data = new ArrayList<>();

                for(int i=0;i<=10;i++) {
                    String S = Keys[i].toString();
                    Object obj = countryData.get(S);
                    Map<String, String> map = convertJSONToHashMap(obj.toString());
                    stockRepository.save(new StockData(S, map.get("1. open"), map.get("2. high"), map.get("3. low"), map.get("4. close")));
//                    data.add(item);
                }
                System.out.println("Data saved");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
