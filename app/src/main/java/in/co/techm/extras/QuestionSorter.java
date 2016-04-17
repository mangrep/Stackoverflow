package in.co.techm.extras;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

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

    public void sortQuestionByVotes(ListQuestion questions) {
        Collections.sort(Arrays.asList(questions.getItems()), new Comparator<Question>() {
            @Override
            public int compare(Question lhs, Question rhs) {
                int soreLhs = lhs.getScore();
                int soreRhs = rhs.getScore();
                if (soreLhs < soreRhs) {
                    return 1;
                } else if (soreLhs > soreRhs) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }
}
