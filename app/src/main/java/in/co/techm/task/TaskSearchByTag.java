package in.co.techm.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import in.co.techm.callbacks.SearchLoadedListener;
import in.co.techm.extras.QuestionUtils;
import in.co.techm.network.VolleySingleton;
import in.co.techm.pojo.response.ListQuestion;

public class TaskSearchByTag extends AsyncTask<String, Void, ListQuestion> {
    private SearchLoadedListener myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;


    public TaskSearchByTag(SearchLoadedListener myComponent) {

        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }


    @Override
    protected ListQuestion doInBackground(String... params) {
        ListQuestion listQuestion = QuestionUtils.searchQuestion(requestQueue, params[0]);
        return listQuestion;
    }

    @Override
    protected void onPostExecute(ListQuestion listQuestion) {
        if (myComponent != null) {
            myComponent.onUpcomingMoviesLoaded(listQuestion);
        }
    }
}
