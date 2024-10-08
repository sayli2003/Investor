package com.example.demo.service.impl;


import com.example.demo.model.News;
import com.example.demo.model.StockData;
import com.example.demo.repository.NewsRepository;
import com.example.demo.service.NewsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

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
        return null;
    }

    public void getNews(String symbol) {
        try {
//            String apikey="ZV2RZQN44QV3CE1D";
            String apikey="demo";
            URL url = new URL("https://www.alphavantage.co/query?function=NEWS_SENTIMENT&tickers="+symbol+"&apikey="+apikey);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                newsRepository.deleteAll();
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
//                System.out.println(informationString);

                //Get the first JSON object in the JSON array
//                System.out.println(dataObject.get("feed"));
                JSONArray dataArray = (JSONArray) dataObject.get("feed");
//                System.out.println("DataArray "+dataArray);

                if(dataArray!=null){
                    JSONObject data = (JSONObject) dataArray.get(0);
                    System.out.println(data.keySet());
                    for (int i = 0; i < dataArray.size(); i++) {
                        data = (JSONObject) dataArray.get(i);
                        newsRepository.save(new News(data.get("title").toString(),data.get("summary").toString(),data.get("overall_sentiment_label").toString()));
                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return null;
    }
}
