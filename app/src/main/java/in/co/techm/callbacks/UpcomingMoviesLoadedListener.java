package in.co.techm.callbacks;

import java.util.ArrayList;

import in.co.techm.pojo.Movie;

public interface UpcomingMoviesLoadedListener {
    public void onUpcomingMoviesLoaded(ArrayList<Movie> listMovies);
}
