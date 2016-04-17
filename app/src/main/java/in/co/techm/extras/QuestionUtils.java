package in.co.techm.extras;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

import in.co.techm.database.DBQuestions;
import in.co.techm.json.Endpoints;
import in.co.techm.json.Parser;
import in.co.techm.json.Requestor;
import in.co.techm.pharmeasy.MyApplication;
import in.co.techm.pojo.response.Questions;

/**
 * Created by Windows on 02-03-2015.
 */
public class QuestionUtils {
    public static Questions loadStackoverflowQuestions(RequestQueue requestQueue) {
        JSONObject response = Requestor.requestQuestionsJSON(requestQueue, Endpoints.getRequestUrlListQuestions(30));
        Questions questions = Parser.parseJSON(response);
//        MyApplication.getWritableDatabase().insertQuestions(DBQuestions.TABLE_QUESTIONS, questions, true);
        return questions;
    }

    public static Questions loadUpcomingMovies(RequestQueue requestQueue) {
        JSONObject response = Requestor.requestQuestionsJSON(requestQueue, Endpoints.getRequestUrlUpcomingMovies(30));
        Questions questions = Parser.parseJSON(response);
//        MyApplication.getWritableDatabase().insertQuestions(DBQuestions.UPCOMING, questions, true);
        return questions;
    }
}
