package in.co.techm.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import in.co.techm.callbacks.QuestionsLoadedListener;
import in.co.techm.extras.QuestionUtils;
import in.co.techm.network.VolleySingleton;
import in.co.techm.pojo.response.ListQuestion;


public class TaskLoadQuestions extends AsyncTask<Void, Void, ListQuestion> {
    private QuestionsLoadedListener myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;


    public TaskLoadQuestions(QuestionsLoadedListener myComponent) {

        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }


    @Override
    protected ListQuestion doInBackground(Void... params) {

        ListQuestion listQuestion = QuestionUtils.loadStackoverflowQuestions(requestQueue);
        return listQuestion;
    }

    @Override
    protected void onPostExecute(ListQuestion listQuestion) {
        if (myComponent != null) {
            myComponent.onQuestionsLoaded(listQuestion);
        }
    }


}
