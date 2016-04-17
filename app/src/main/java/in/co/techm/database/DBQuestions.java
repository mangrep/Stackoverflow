package in.co.techm.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.Date;

import in.co.techm.logging.L;
import in.co.techm.pojo.Movie;
import in.co.techm.pojo.response.Question;

/**
 * Created by Windows on 25-02-2015.
 */
public class DBQuestions {
    public static final int TABLE_QUESTIONS = 0;
    public static final int UPCOMING = 1;
    private QuestionsHelper mHelper;
    private SQLiteDatabase mDatabase;

    public DBQuestions(Context context) {
        mHelper = new QuestionsHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    public void insertQuestions(int table, Question question, boolean clearPrevious) {
        if (clearPrevious) {
            deleteQuestions(table);
        }


        //create a sql prepared statement
        String sql = "INSERT INTO " + (table == TABLE_QUESTIONS ? QuestionsHelper.TABLE_QUESTIONS : QuestionsHelper.TABLE_UPCOMING) + " VALUES (?,?,?,?,?,?,?,?,?,?);";
        //compile the statement and start a transaction
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();
//        for (int i = 0; i < questions.size(); i++) {
//            Movie currentMovie = questions.get(i);
//            statement.clearBindings();
//            //for a given column index, simply bind the data to be put inside that index
//            statement.bindString(2, currentMovie.getTitle());
//            statement.bindLong(3, currentMovie.getReleaseDateTheater() == null ? -1 : currentMovie.getReleaseDateTheater().getTime());
//            statement.bindLong(4, currentMovie.getAudienceScore());
//            statement.bindString(5, currentMovie.getSynopsis());
//            statement.bindString(6, currentMovie.getUrlThumbnail());
//            statement.bindString(7, currentMovie.getUrlSelf());
//            statement.bindString(8, currentMovie.getUrlCast());
//            statement.bindString(9, currentMovie.getUrlReviews());
//            statement.bindString(10, currentMovie.getUrlSimilar());
//
//            statement.execute();
//        }
        //set the transaction as successful and end the transaction
//        L.m("inserting entries " + questions.size() + new Date(System.currentTimeMillis()));
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }

    public ArrayList<Movie> readMovies(int table) {
        ArrayList<Movie> listMovies = new ArrayList<>();

        //get a list of columns to be retrieved, we need all of them
        String[] columns = {QuestionsHelper.COLUMN_UID,
                QuestionsHelper.COLUMN_TITLE,
                QuestionsHelper.COLUMN_RELEASE_DATE,
                QuestionsHelper.COLUMN_AUDIENCE_SCORE,
                QuestionsHelper.COLUMN_SYNOPSIS,
                QuestionsHelper.COLUMN_URL_THUMBNAIL,
                QuestionsHelper.COLUMN_URL_SELF,
                QuestionsHelper.COLUMN_URL_CAST,
                QuestionsHelper.COLUMN_URL_REVIEWS,
                QuestionsHelper.COLUMN_URL_SIMILAR
        };
        Cursor cursor = mDatabase.query((table == TABLE_QUESTIONS ? QuestionsHelper.TABLE_QUESTIONS : QuestionsHelper.TABLE_UPCOMING), columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            L.m("loading entries " + cursor.getCount() + new Date(System.currentTimeMillis()));
            do {

                //create a new movie object and retrieve the data from the cursor to be stored in this movie object
                Movie movie = new Movie();
                //each step is a 2 part process, find the index of the column first, find the data of that column using
                //that index and finally set our blank movie object to contain our data
                movie.setTitle(cursor.getString(cursor.getColumnIndex(QuestionsHelper.COLUMN_TITLE)));
                long releaseDateMilliseconds = cursor.getLong(cursor.getColumnIndex(QuestionsHelper.COLUMN_RELEASE_DATE));
                movie.setReleaseDateTheater(releaseDateMilliseconds != -1 ? new Date(releaseDateMilliseconds) : null);
                movie.setAudienceScore(cursor.getInt(cursor.getColumnIndex(QuestionsHelper.COLUMN_AUDIENCE_SCORE)));
                movie.setSynopsis(cursor.getString(cursor.getColumnIndex(QuestionsHelper.COLUMN_SYNOPSIS)));
                movie.setUrlThumbnail(cursor.getString(cursor.getColumnIndex(QuestionsHelper.COLUMN_URL_THUMBNAIL)));
                movie.setUrlSelf(cursor.getString(cursor.getColumnIndex(QuestionsHelper.COLUMN_URL_SELF)));
                movie.setUrlCast(cursor.getString(cursor.getColumnIndex(QuestionsHelper.COLUMN_URL_CAST)));
                movie.setUrlReviews(cursor.getString(cursor.getColumnIndex(QuestionsHelper.COLUMN_URL_REVIEWS)));
                movie.setUrlSimilar(cursor.getString(cursor.getColumnIndex(QuestionsHelper.COLUMN_URL_SIMILAR)));
                //add the movie to the list of movie objects which we plan to return
                listMovies.add(movie);
            }
            while (cursor.moveToNext());
        }
        return listMovies;
    }

    public void deleteQuestions(int table) {
        mDatabase.delete((table == TABLE_QUESTIONS ? QuestionsHelper.TABLE_QUESTIONS : QuestionsHelper.TABLE_UPCOMING), null, null);
    }

    private static class QuestionsHelper extends SQLiteOpenHelper {
        public static final String TABLE_UPCOMING = " movies_upcoming";
        public static final String TABLE_QUESTIONS = "stackoverflow_questions";
        public static final String COLUMN_UID = "_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_AUDIENCE_SCORE = "audience_score";
        public static final String COLUMN_SYNOPSIS = "synopsis";
        public static final String COLUMN_URL_THUMBNAIL = "url_thumbnail";
        public static final String COLUMN_URL_SELF = "url_self";
        public static final String COLUMN_URL_CAST = "url_cast";
        public static final String COLUMN_URL_REVIEWS = "url_reviews";
        public static final String COLUMN_URL_SIMILAR = "url_similar";
        private static final String CREATE_TABLE_BOX_OFFICE = "CREATE TABLE " + TABLE_QUESTIONS + " (" +
                COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_RELEASE_DATE + " INTEGER," +
                COLUMN_AUDIENCE_SCORE + " INTEGER," +
                COLUMN_SYNOPSIS + " TEXT," +
                COLUMN_URL_THUMBNAIL + " TEXT," +
                COLUMN_URL_SELF + " TEXT," +
                COLUMN_URL_CAST + " TEXT," +
                COLUMN_URL_REVIEWS + " TEXT," +
                COLUMN_URL_SIMILAR + " TEXT" +
                ");";
        private static final String CREATE_TABLE_UPCOMING = "CREATE TABLE " + TABLE_UPCOMING + " (" +
                COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_RELEASE_DATE + " INTEGER," +
                COLUMN_AUDIENCE_SCORE + " INTEGER," +
                COLUMN_SYNOPSIS + " TEXT," +
                COLUMN_URL_THUMBNAIL + " TEXT," +
                COLUMN_URL_SELF + " TEXT," +
                COLUMN_URL_CAST + " TEXT," +
                COLUMN_URL_REVIEWS + " TEXT," +
                COLUMN_URL_SIMILAR + " TEXT" +
                ");";
        private static final String DB_NAME = "question_db";
        private static final int DB_VERSION = 1;
        private Context mContext;

        public QuestionsHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_BOX_OFFICE);
                db.execSQL(CREATE_TABLE_UPCOMING);
                L.m("create table box office executed");
            } catch (SQLiteException exception) {
                L.t(mContext, exception + "");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                L.m("upgrade table box office executed");
                db.execSQL(" DROP TABLE " + TABLE_QUESTIONS + " IF EXISTS;");
                db.execSQL(" DROP TABLE " + TABLE_UPCOMING + " IF EXISTS;");
                onCreate(db);
            } catch (SQLiteException exception) {
                L.t(mContext, exception + "");
            }
        }
    }
}

