package in.co.techm.services;

import java.util.ArrayList;

import in.co.techm.callbacks.QuestionsLoadedListener;
import in.co.techm.logging.L;
import in.co.techm.pojo.Movie;
import in.co.techm.task.TaskLoadQuestions;
import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobService;

/**
 * Created by Windows on 23-02-2015.
 */
public class ServiceMoviesBoxOffice extends JobService implements QuestionsLoadedListener {
    private JobParameters jobParameters;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        L.t(this, "onStartJob");
        this.jobParameters = jobParameters;
        new TaskLoadQuestions(this).execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        L.t(this, "onStopJob");
        return false;
    }


    @Override
    public void onQuestionsLoaded(ArrayList<Movie> listMovies) {
        L.t(this, "onQuestionsLoaded");
        jobFinished(jobParameters, false);
    }
}

