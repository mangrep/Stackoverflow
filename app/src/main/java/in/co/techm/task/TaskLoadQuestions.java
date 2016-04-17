package in.co.techm.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import in.co.techm.callbacks.QuestionsLoadedListener;
import in.co.techm.extras.QuestionUtils;
import in.co.techm.network.VolleySingleton;
import in.co.techm.pojo.response.Questions;


public class TaskLoadQuestions extends AsyncTask<Void, Void, Questions> {
    private QuestionsLoadedListener myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;


    public TaskLoadQuestions(QuestionsLoadedListener myComponent) {

        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }


    @Override
    protected Questions doInBackground(Void... params) {

        Questions questions = QuestionUtils.loadStackoverflowQuestions(requestQueue);
        return questions;
    }

    @Override
    protected void onPostExecute(Questions questions) {
        if (myComponent != null) {
            myComponent.onQuestionsLoaded(questions);
        }
    }


}
