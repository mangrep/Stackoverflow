package in.co.techm.json;

import static in.co.techm.extras.UrlEndpoints.URL_CHAR_AMEPERSAND;
import static in.co.techm.extras.UrlEndpoints.URL_CHAR_QUESTION;
import static in.co.techm.extras.UrlEndpoints.URL_LIST_QUESTIONS;
import static in.co.techm.extras.UrlEndpoints.URL_PARAM_ACCEPTED;
import static in.co.techm.extras.UrlEndpoints.URL_PARAM_ANSWERS;
import static in.co.techm.extras.UrlEndpoints.URL_PARAM_ORDER;
import static in.co.techm.extras.UrlEndpoints.URL_PARAM_SITE;
import static in.co.techm.extras.UrlEndpoints.URL_PARAM_SORT;
import static in.co.techm.extras.UrlEndpoints.URL_PARAM_TAGGED;
import static in.co.techm.extras.UrlEndpoints.URL_SEARCH;

/**
 * Created by Windows on 02-03-2015.
 */
public class Endpoints {
    public static String getRequestUrlListQuestions(int limit) {

        return URL_LIST_QUESTIONS;
//                + URL_CHAR_QUESTION
//                + URL_PARAM_API_KEY + MyApplication.API_KEY_ROTTEN_TOMATOES
//                + URL_CHAR_AMEPERSAND
//                + URL_PARAM_LIMIT + limit;
    }

    public static String searchQuestions(String tag) {
        return URL_SEARCH
                + URL_CHAR_QUESTION
                + URL_PARAM_ORDER + "desc"
                + URL_CHAR_AMEPERSAND
                + URL_PARAM_SORT + "activity"
                + URL_CHAR_AMEPERSAND
                + URL_PARAM_ACCEPTED + "False"
                + URL_CHAR_AMEPERSAND
                + URL_PARAM_ANSWERS + "0"
                + URL_CHAR_AMEPERSAND
                + URL_PARAM_TAGGED + tag
                + URL_CHAR_AMEPERSAND
                + URL_PARAM_SITE + "stackoverflow";
//
    }
}
