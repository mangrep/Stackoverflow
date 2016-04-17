package in.co.techm.pojo.response;


public class ListQuestion {
    private Questions questions;
    private boolean has_more;
    private int quota_max;
    private int quota_remaining;

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
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
                "items=" + questions +
                ", has_more=" + has_more +
                ", quota_max=" + quota_max +
                ", quota_remaining=" + quota_remaining +
                '}';
    }
}
