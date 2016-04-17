package in.co.techm.extras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import in.co.techm.pojo.Movie;
import in.co.techm.pojo.response.ListQuestion;
import in.co.techm.pojo.response.Question;


public class QuestionSorter {
    public void sortQuestionBYViewCount(ListQuestion questions) {
        Collections.sort(Arrays.asList(questions.getItems()), new Comparator<Question>() {
            @Override
            public int compare(Question lhs, Question rhs) {
                if (lhs.getView_count() > rhs.getView_count()) {
                    return -1;
                } else if (lhs.getView_count() < rhs.getView_count()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    public void sortQuestionsByCreationDate(ListQuestion questions) {

        Collections.sort(Arrays.asList(questions.getItems()), new Comparator<Question>() {
            @Override
            public int compare(Question lhs, Question rhs) {
                Date lhsDate = new Date(lhs.getCreation_date());
                Date rhsDate = new Date(rhs.getCreation_date());
                if (lhsDate != null && rhsDate != null) {
                    return rhsDate.compareTo(lhsDate);
                } else {
                    return 0;
                }
            }
        });
    }

    public void sortMoviesByRating(ArrayList<Movie> movies) {
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {
                int ratingLhs = lhs.getAudienceScore();
                int ratingRhs = rhs.getAudienceScore();
                if (ratingLhs < ratingRhs) {
                    return 1;
                } else if (ratingLhs > ratingRhs) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }
}
