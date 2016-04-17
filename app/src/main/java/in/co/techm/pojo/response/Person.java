package in.co.techm.pojo.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by turing on 17/4/16.
 */
public class Person implements Parcelable {
    private String reputation;
    private String user_id;
    private String user_type;
    private String accept_rate;
    private String profile_image;
    private String display_name;
    private String link;

    protected Person(Parcel in) {
        reputation = in.readString();
        user_id = in.readString();
        user_type = in.readString();
        accept_rate = in.readString();
        profile_image = in.readString();
        display_name = in.readString();
        link = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getAccept_rate() {
        return accept_rate;
    }

    public void setAccept_rate(String accept_rate) {
        this.accept_rate = accept_rate;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Person{" +
                "reputation='" + reputation + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_type='" + user_type + '\'' +
                ", accept_rate='" + accept_rate + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", display_name='" + display_name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reputation);
        dest.writeString(user_id);
        dest.writeString(user_type);
        dest.writeString(accept_rate);
        dest.writeString(profile_image);
        dest.writeString(display_name);
        dest.writeString(link);
    }
}
