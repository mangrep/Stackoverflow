package in.co.techm.callbacks;

import java.util.ArrayList;

import in.co.techm.pojo.Movie;
import in.co.techm.pojo.response.ListQuestion;

public interface SearchLoadedListener {
    public void onUpcomingMoviesLoaded(ListQuestion listQuestion);
}
