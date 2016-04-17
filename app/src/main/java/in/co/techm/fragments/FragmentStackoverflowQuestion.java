package in.co.techm.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import in.co.techm.adapters.AdapterQuestions;
import in.co.techm.callbacks.QuestionsLoadedListener;
import in.co.techm.extras.QuestionSorter;
import in.co.techm.extras.SortListener;
import in.co.techm.logging.L;
import in.co.techm.pharmeasy.R;
import in.co.techm.pojo.response.ListQuestion;
import in.co.techm.task.TaskLoadQuestions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentStackoverflowQuestion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentStackoverflowQuestion extends Fragment implements SortListener, QuestionsLoadedListener, SwipeRefreshLayout.OnRefreshListener {

    //The key used to store arraylist of movie objects to and from parcelable
    private static final String STATE_QUESTIONS = "state_movies";
    private ListQuestion mListQuestions;
    //the adapter responsible for displaying our movies within a RecyclerView
    private AdapterQuestions mAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    //the recyclerview containing showing all our movies
    private RecyclerView mRecyclerMovies;
    //the TextView containing error messages generated by Volley
    private TextView mTextError;
    //the sorter responsible for sorting our movie results based on choice made by the user in the FAB
    private QuestionSorter mSorter = new QuestionSorter();

    public FragmentStackoverflowQuestion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBoxOffice.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentStackoverflowQuestion newInstance(String param1, String param2) {
        FragmentStackoverflowQuestion fragment = new FragmentStackoverflowQuestion();
        Bundle args = new Bundle();
        //put any extra arguments that you may want to supply to this fragment
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_box_office, container, false);
        mTextError = (TextView) layout.findViewById(R.id.textVolleyError);
        mSwipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swipeMovieHits);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerMovies = (RecyclerView) layout.findViewById(R.id.listMovieHits);
        //set the layout manager before trying to display data
        mRecyclerMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new AdapterQuestions(getActivity());
        mRecyclerMovies.setAdapter(mAdapter);

        if (savedInstanceState != null) {
            //if this fragment starts after a rotation or configuration change, load the existing movies from a parcelable
            mListQuestions = savedInstanceState.getParcelable(STATE_QUESTIONS);
        } else {
            //if this fragment starts for the first time, load the list of movies from a database
//            mListMovies = MyApplication.getWritableDatabase().readMovies(DBQuestions.TABLE_QUESTIONS);
            //if the database is empty, trigger an AsycnTask to download movie list from the web
            if (mListQuestions == null) {
                L.m("FragmentBoxOffice: executing task from fragment");
                new TaskLoadQuestions(this).execute();
            }
        }
        //update your Adapter to containg the retrieved movies
        mAdapter.setQuestions(mListQuestions);
        return layout;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save the movie list to a parcelable prior to rotation or configuration change
        outState.putParcelable(STATE_QUESTIONS, mListQuestions);
    }


    private void handleVolleyError(VolleyError error) {
        //if any error occurs in the network operations, show the TextView that contains the error message
        mTextError.setVisibility(View.VISIBLE);
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            mTextError.setText(R.string.error_timeout);

        } else if (error instanceof AuthFailureError) {
            mTextError.setText(R.string.error_auth_failure);
            //TODO
        } else if (error instanceof ServerError) {
            mTextError.setText(R.string.error_auth_failure);
            //TODO
        } else if (error instanceof NetworkError) {
            mTextError.setText(R.string.error_network);
            //TODO
        } else if (error instanceof ParseError) {
            mTextError.setText(R.string.error_parser);
            //TODO
        }
    }

    /**
     * Called when the user chooses to sort results by view count through the menu displayed inside FAB
     */
    @Override
    public void sortByViewCount() {
        if (mListQuestions != null) {
            mSorter.sortQuestionBYViewCount(mListQuestions);
            mAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext().getApplicationContext(), "No data available to sort", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Called when the user chooses to sort results by creation date through the menu displayed inside FAB
     */
    @Override
    public void onSortByCreationDate() {
        if (mListQuestions != null) {
            mSorter.sortQuestionsByCreationDate(mListQuestions);
            mAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext().getApplicationContext(), "No data available to sort", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Called when the user chooses to sort results by rating through the menu displayed inside FAB
     */
    @Override
    public void onSortByVotes() {
        if (mListQuestions != null) {
            mSorter.sortQuestionByVotes(mListQuestions);
            mAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext().getApplicationContext(), "No data available to sort", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Called when the AsyncTask finishes load the list of questions from the web
     */
    @Override
    public void onQuestionsLoaded(ListQuestion listQuestion) {
        L.m("FragmentBoxOffice: onQuestionsLoaded Fragment");
        //update the Adapter to contain the questions downloaded from the web
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mListQuestions = listQuestion;
        mAdapter.setQuestions(listQuestion);
    }

    @Override
    public void onRefresh() {
        L.t(getActivity(), "onRefresh");
        //load the whole feed again on refresh, dont try this at home :)
        new TaskLoadQuestions(this).execute();

    }
}