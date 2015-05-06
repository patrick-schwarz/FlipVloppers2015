package at.tugraz.flipvloppers.flipvloppers2015.controller;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * Created by Admin on 22.04.2015.
 */
public class NewsFeedController {
    public void SendNewsfeedPost(String message) {
        ControllerFactory.GetWebserviceControllerInstance().SendNewsfeedPost(message);
    }

    public List<NewsFeed> GetNewsFeedList()
    {
        return ControllerFactory.GetWebserviceControllerInstance().GetNewsFeedList();
    }

}
