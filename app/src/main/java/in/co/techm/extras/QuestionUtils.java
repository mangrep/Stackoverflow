package in.co.techm.extras;

import android.util.Log;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

import in.co.techm.json.Endpoints;
import in.co.techm.json.Parser;
import in.co.techm.json.Requestor;
import in.co.techm.pojo.response.ListQuestion;

public class QuestionUtils {
    public static ListQuestion loadStackoverflowQuestions(RequestQueue requestQueue) {
        JSONObject response = Requestor.requestQuestionsJSON(requestQueue, Endpoints.getRequestUrlListQuestions(30));
        ListQuestion listQuestion = Parser.parseJSON(response);
//        MyApplication.getWritableDatabase().insertQuestions(DBQuestions.TABLE_QUESTIONS, questions, true);
        return listQuestion;
    }

    public static ListQuestion searchQuestion(RequestQueue requestQueue, String tag) {
//        JSONObject response = Requestor.requestQuestionsJSON(requestQueue, Endpoints.searchQuestions(tag));
        Log.d("Search URL", Endpoints.searchQuestions(tag));
        JSONObject response = Requestor.requestQuestionsJSON(requestQueue, Endpoints.getRequestUrlListQuestions(30));
        ListQuestion listQuestion = Parser.parseJSON(response);
//        MyApplication.getWritableDatabase().insertQuestions(DBQuestions.UPCOMING, questions, true);
        return listQuestion;
    }
}
