package com.codepath.apps.restclienttemplate.models;

import android.icu.text.RelativeDateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
@Parcel
public class Tweet {
    public String body;
    public String timestamp;
    public User user;

    public Tweet(){} //empty constructor rq'd by Parceler model

    public static String getFormattedTimeStamp(JSONObject jsonObject) throws JSONException {
        return TimeFormatter.getTimeDifference(jsonObject.getString("created_at"));
    }

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.timestamp = getFormattedTimeStamp(jsonObject);
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length();i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
}
