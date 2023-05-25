
package com.zerobase.weather.service;

import com.zerobase.weather.domain.Diary;
import com.zerobase.weather.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DiaryService {

    @Value("${openweathermap.key}")
    private String apiKey;

    private final DiaryRepository diaryRepository;

    public void createDiary(LocalDate date, String text) {
        // open weather map 날씨 데이터 가져오기
        String weatherData = getWeatherString();

        // 받아온 날씨 json 파싱
        Map<String, Object> parseWeather = parseWeather(weatherData);

        // 파싱된 데이터 + 일기 값 DB Insert
//        Diary nowDiary = new Diary();
//        nowDiary.setWeather(parseWeather.get("main").toString());
//        nowDiary.setIcon(parseWeather.get("icon").toString());
//        nowDiary.setTemperature((Double) parseWeather.get("temp"));
//        nowDiary.setText(text);
//        nowDiary.setDate(date);

        // 빌더 타입으로 변경
        Diary nowDiary = Diary.builder()
                .weather(parseWeather.get("main").toString())
                .icon(parseWeather.get("icon").toString())
                .temperature((Double) parseWeather.get("temp"))
                .text(text)
                .date(date)
                .build();

        diaryRepository.save(nowDiary);

    }

    private String getWeatherString() {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();

            BufferedReader br;

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
            }

            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            return response.toString();

        } catch (Exception e) {
//            e.printStackTrace();
            return "failed to get response";
        }
    }

    private Map<String, Object> parseWeather(String jsonString) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        HashMap<String, Object> resultMap = new HashMap<>();

        JSONObject mainData = (JSONObject) jsonObject.get("main");
        resultMap.put("temp", mainData.get("temp"));
        JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
        JSONObject weatherData = (JSONObject) weatherArray.get(0);
        resultMap.put("main", weatherData.get("main"));
        resultMap.put("icon", weatherData.get("icon"));

        return resultMap;

    }

    public List<Diary> readDiary(LocalDate date) {
        return diaryRepository.findAllByDate(date);
    }

    public List<Diary> readDiaries(LocalDate startDate, LocalDate endDate) {
        return diaryRepository.findAllByDateBetween(startDate, endDate);
    }

    public void updateDiary(LocalDate date, String text) {
        Diary nowDiary = diaryRepository.getFirstByDate(date);
        nowDiary.setText(text);
        diaryRepository.save(nowDiary);
    }

    public void deleteDiary(LocalDate date) {
        diaryRepository.deleteAllByDate(date);
    }
}
