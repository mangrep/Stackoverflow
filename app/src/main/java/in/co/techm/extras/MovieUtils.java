package in.co.techm.extras;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

import java.util.ArrayList;

import in.co.techm.database.DBMovies;
import in.co.techm.json.Endpoints;
import in.co.techm.json.Parser;
import in.co.techm.json.Requestor;
import in.co.techm.pharmeasy.MyApplication;
import in.co.techm.pojo.Movie;

/**
 * Created by Windows on 02-03-2015.
 */
public class MovieUtils {
    public static ArrayList<Movie> loadBoxOfficeMovies(RequestQueue requestQueue) {
        JSONObject response = Requestor.requestQuestionsJSON(requestQueue, Endpoints.getRequestUrlListQuestions(30));
        ArrayList<Movie> listMovies = Parser.parseMoviesJSON(response);
        MyApplication.getWritableDatabase().insertMovies(DBMovies.BOX_OFFICE, listMovies, true);
        return listMovies;
    }

    public static ArrayList<Movie> loadUpcomingMovies(RequestQueue requestQueue) {
        JSONObject response = Requestor.requestQuestionsJSON(requestQueue, Endpoints.getRequestUrlUpcomingMovies(30));
        ArrayList<Movie> listMovies = Parser.parseMoviesJSON(response);
        MyApplication.getWritableDatabase().insertMovies(DBMovies.UPCOMING, listMovies, true);
        return listMovies;
    }
}
