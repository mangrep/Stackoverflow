package in.co.techm.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import in.co.techm.pojo.response.ListQuestion;


public class Parser {
    public static ListQuestion parseJSON(JSONObject response) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
        return gson.fromJson(response.toString(), ListQuestion.class);
    }


}
