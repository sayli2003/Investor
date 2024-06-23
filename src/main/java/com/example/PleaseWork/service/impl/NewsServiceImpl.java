package com.example.PleaseWork.service.impl;

import com.example.PleaseWork.modal.News;
import com.example.PleaseWork.modal.StockData;
import com.example.PleaseWork.repository.NewsRepository;
import com.example.PleaseWork.service.NewsService;
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
import java.util.*;

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
            URL url = new URL("https://www.alphavantage.co/query?function=NEWS_SENTIMENT&tickers="+symbol+"&apikey=demo");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
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
//                System.out.println(dataObject.get("feed"));
                JSONArray dataArray = (JSONArray) dataObject.get("feed");
//                System.out.println(dataArray.get(0).get);

                JSONObject data = (JSONObject) dataArray.get(0);
                System.out.println(data.keySet());
                for (int i = 0; i < dataArray.size(); i++) {
                    data = (JSONObject) dataArray.get(i);
                    newsRepository.save(new News(data.get("title").toString(),data.get("summary").toString(),data.get("overall_sentiment_label").toString()));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return null;
    }
}
