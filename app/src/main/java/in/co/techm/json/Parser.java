package in.co.techm.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import in.co.techm.extras.Constants;
import in.co.techm.pojo.Movie;
import in.co.techm.pojo.response.Questions;

import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_AUDIENCE_SCORE;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_CAST;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_ID;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_LINKS;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_MOVIES;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_POSTERS;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_RATINGS;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_RELEASE_DATES;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_REVIEWS;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_SELF;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_SIMILAR;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_SYNOPSIS;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_THEATER;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_THUMBNAIL;
import static in.co.techm.extras.Keys.EndpointBoxOffice.KEY_TITLE;


public class Parser {
    public static Questions parseJSON(JSONObject response) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
        return gson.fromJson(response.toString(), Questions.class);
    }


}
