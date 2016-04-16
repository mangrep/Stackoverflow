package in.co.techm.callbacks;

import java.util.ArrayList;

import in.co.techm.pojo.Movie;

/**
 * Created by Windows on 02-03-2015.
 */
public interface BoxOfficeMoviesLoadedListener {
    public void onBoxOfficeMoviesLoaded(ArrayList<Movie> listMovies);
}
