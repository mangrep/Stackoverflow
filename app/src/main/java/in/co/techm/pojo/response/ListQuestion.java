package in.co.techm.pojo.response;


import android.os.Parcel;
import android.os.Parcelable;

public class ListQuestion implements Parcelable{
    private Question[] items;
    private boolean has_more;
    private int quota_max;
    private int quota_remaining;

    protected ListQuestion(Parcel in) {
        has_more = in.readByte() != 0;
        quota_max = in.readInt();
        quota_remaining = in.readInt();
    }

    public static final Creator<ListQuestion> CREATOR = new Creator<ListQuestion>() {
        @Override
        public ListQuestion createFromParcel(Parcel in) {
            return new ListQuestion(in);
        }

        @Override
        public ListQuestion[] newArray(int size) {
            return new ListQuestion[size];
        }
    };

    public Question[] getItems() {
        return items;
    }

    public void setItems(Question[] items) {
        this.items = items;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public int getQuota_max() {
        return quota_max;
    }

    public void setQuota_max(int quota_max) {
        this.quota_max = quota_max;
    }

    public int getQuota_remaining() {
        return quota_remaining;
    }

    public void setQuota_remaining(int quota_remaining) {
        this.quota_remaining = quota_remaining;
    }

    @Override
    public String toString() {
        return "ListQuestion{" +
                "items=" + items +
                ", has_more=" + has_more +
                ", quota_max=" + quota_max +
                ", quota_remaining=" + quota_remaining +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (has_more ? 1 : 0));
        dest.writeInt(quota_max);
        dest.writeInt(quota_remaining);
    }
}
