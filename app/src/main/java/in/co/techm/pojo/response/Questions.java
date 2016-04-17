package in.co.techm.pojo.response;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by turing on 17/4/16.
 */
public class Questions {
    private String [] tags;
    private Person owner;
    private boolean is_answered;
    private int view_count;
    private int answer_count;
    private int score;
    private Date last_activity_date;
    private Date creation_date;
    private String question_id;
    private String link;
    private String title;

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public boolean is_answered() {
        return is_answered;
    }

    public void setIs_answered(boolean is_answered) {
        this.is_answered = is_answered;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(int answer_count) {
        this.answer_count = answer_count;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getLast_activity_date() {
        return last_activity_date;
    }

    public void setLast_activity_date(Date last_activity_date) {
        this.last_activity_date = last_activity_date;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "tags=" + Arrays.toString(tags) +
                ", owner=" + owner +
                ", is_answered=" + is_answered +
                ", view_count=" + view_count +
                ", answer_count=" + answer_count +
                ", score=" + score +
                ", last_activity_date=" + last_activity_date +
                ", creation_date=" + creation_date +
                ", question_id='" + question_id + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
