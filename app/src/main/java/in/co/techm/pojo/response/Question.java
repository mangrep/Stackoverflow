package in.co.techm.pojo.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by turing on 17/4/16.
 */
public class Question implements Parcelable {
    private String [] tags;
    private Person owner;
    private boolean is_answered;
    private int view_count;
    private int answer_count;
    private int score;
    private long last_activity_date;
    private long creation_date;
    private String question_id;
    private String link;
    private String title;

    protected Question(Parcel in) {
        tags = in.createStringArray();
        owner = in.readParcelable(Person.class.getClassLoader());
        is_answered = in.readByte() != 0;
        view_count = in.readInt();
        answer_count = in.readInt();
        score = in.readInt();
        question_id = in.readString();
        link = in.readString();
        title = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

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

    public long getLast_activity_date() {
        return last_activity_date;
    }

    public void setLast_activity_date(long last_activity_date) {
        this.last_activity_date = last_activity_date;
    }

    public long getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(long creation_date) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(tags);
        dest.writeParcelable(owner, flags);
        dest.writeByte((byte) (is_answered ? 1 : 0));
        dest.writeInt(view_count);
        dest.writeInt(answer_count);
        dest.writeInt(score);
        dest.writeString(question_id);
        dest.writeString(link);
        dest.writeString(title);
    }
}
