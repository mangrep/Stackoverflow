package in.co.techm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.techm.anim.AnimationUtils;
import in.co.techm.extras.Constants;
import in.co.techm.network.VolleySingleton;
import in.co.techm.pharmeasy.R;
import in.co.techm.pojo.response.ListQuestion;
import in.co.techm.pojo.response.Question;

public class AdapterQuestions extends RecyclerView.Adapter<AdapterQuestions.ViewHolderQuestions> {

    private ListQuestion mListQuestions;
    private LayoutInflater mInflater;
    private VolleySingleton mVolleySingleton;
    private ImageLoader mImageLoader;
    //formatter for parsing the dates in the specified format below
    private DateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd");
    //keep track of the previous position for animations where scrolling down requires a different animation compared to scrolling up
    private int mPreviousPosition = 0;


    public AdapterQuestions(Context context) {
        mInflater = LayoutInflater.from(context);
        mVolleySingleton = VolleySingleton.getInstance();
        mImageLoader = mVolleySingleton.getImageLoader();
    }

    public void setQuestions(ListQuestion listQuestion) {
        this.mListQuestions = listQuestion;
        //update the adapter to reflect the new set of movies
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderQuestions onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_movie_box_office, parent, false);
        ViewHolderQuestions viewHolder = new ViewHolderQuestions(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderQuestions holder, int position) {
        Question question = mListQuestions.getItems()[position];
        //one or more fields of the Movie object may be null since they are fetched from the web
        holder.questionTxt.setText(question.getTitle());
        holder.name.setText(question.getOwner().getDisplay_name());
        holder.tags.setText(question.getTags().toString());


        //retrieved date may be null
        Date creationDate = new Date(question.getCreation_date());
        Date now = new Date();

        long diff = now.getTime() - creationDate.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        holder.time.setText(diffMinutes + " minutes back");

        if (position > mPreviousPosition) {
            AnimationUtils.animateSunblind(holder, true);
//            AnimationUtils.animateSunblind(holder, true);
//            AnimationUtils.animate1(holder, true);
//            AnimationUtils.animate(holder,true);
        } else {
            AnimationUtils.animateSunblind(holder, false);
//            AnimationUtils.animateSunblind(holder, false);
//            AnimationUtils.animate1(holder, false);
//            AnimationUtils.animate(holder, false);
        }
        mPreviousPosition = position;

        String urlThumnail = question.getOwner().getProfile_image();
        loadImages(urlThumnail, holder);

    }


    private void loadImages(String urlThumbnail, final ViewHolderQuestions holder) {
        if (!urlThumbnail.equals(Constants.NA)) {
            mImageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.profileThumbnail.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mListQuestions == null || mListQuestions.getItems() == null) {
            return 0;
        } else {
            return mListQuestions.getItems().length;
        }

    }

    static class ViewHolderQuestions extends RecyclerView.ViewHolder {

        ImageView profileThumbnail;
        TextView questionTxt;
        TextView tags;
        TextView name;
        TextView time;

        public ViewHolderQuestions(View itemView) {
            super(itemView);
            profileThumbnail = (ImageView) itemView.findViewById(R.id.profileThumbnail);
            questionTxt = (TextView) itemView.findViewById(R.id.questionTxt);
            tags = (TextView) itemView.findViewById(R.id.tags);
            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
