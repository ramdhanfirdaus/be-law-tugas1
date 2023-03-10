package law.tugas1.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import law.tugas1.model.FavoriteTag;
import law.tugas1.repository.FavoriteTagRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private FavoriteTagRepository favoriteTagRepository;

    @Override
    public JSONObject requestQuestionByTitle(String title) {
        Map<String, String> mapRequest = new HashMap<>();
        mapRequest.put("title", title);

        return requestQuestion(mapRequest);
    }

    @Override
    public JSONObject requestQuestionByTags(String id) {
        List<FavoriteTag> list = favoriteTagRepository.findFavoriteTagByUser_Id(id);
        JSONObject jsonRes = new JSONObject();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        long todayInSeconds = calendar.getTimeInMillis() / 1000;
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        long tomorrowInSeconds = calendar.getTimeInMillis() / 1000;

        for (FavoriteTag fav : list) {
            Map<String, String> mapRequest = new HashMap<>();
            mapRequest.put("tagged", fav.getTag());
            mapRequest.put("fromdate", String.valueOf(todayInSeconds));
            mapRequest.put("todate", String.valueOf(tomorrowInSeconds));

            jsonRes.accumulate("data", requestQuestion(mapRequest));
        }

        jsonRes.accumulate("data", new JSONObject());
        return jsonRes;
    }

    public JSONObject convertHttpResponseToMap(HttpResponse<String> response, String title) {
        JSONObject jsonRes = new JSONObject(response.getBody());

        JSONArray jsonArray = jsonRes.getJSONArray("items");
        JSONObject jsonResp = new JSONObject();

        JSONObject json;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObj = new JSONObject();
            json = new JSONObject(jsonArray.get(i).toString());

            if (title != null) {
                jsonResp.put("tag", title);
            }

            jsonObj.put("question_id", json.get("question_id"));
            jsonObj.put("link", json.get("link"));
            jsonObj.put("title", json.get("title"));
            jsonObj.put("tags", json.get("tags"));

            jsonResp.accumulate("data", jsonObj);

        }
        jsonResp.accumulate("data", new JSONObject());

        return jsonResp;
    }

    public JSONObject requestQuestion(Map<String, String> mapRequest) {
        Unirest.setTimeouts(0, 0);
        try {
            StringBuilder req = new StringBuilder();
            for (String key : mapRequest.keySet()) {
                req.append('&').append(key).append('=').append(mapRequest.get(key));
            }
            HttpResponse<String> response = Unirest.get("https://api.stackexchange.com/2.3/search/advanced?site=stackoverflow" + req.toString().replace(" ", "%20"))
                    .header("Cookie", "prov=bac0606d-5451-405b-a58e-12397e93196f")
                    .asString();

            JSONObject jsonResp = convertHttpResponseToMap(response, mapRequest.get("tagged"));

            return jsonResp;
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

}
