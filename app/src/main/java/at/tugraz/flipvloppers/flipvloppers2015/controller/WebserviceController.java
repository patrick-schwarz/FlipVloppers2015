package at.tugraz.flipvloppers.flipvloppers2015.controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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
import java.util.concurrent.ExecutionException;

import at.tugraz.flipvloppers.flipvloppers2015.configuration.Configuration;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.LoginResponseUser;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * Created by Admin on 06.05.2015.
 */
public class WebserviceController {


    public User CheckLogin(String username,String password) {

        AsyncTask<String, Void, User> task = new AsyncTask<String, Void, User>() {

            private static final String TAG = "LoginChecker";
            public static final String SERVER_URL = "http://134.0.27.180/AuthenticationHandler.php";

            @Override
            protected User doInBackground(String... params) {
                Log.e(TAG, "going to check Login");

                try {
                    //Create an HTTP client
                    HttpClient client = new DefaultHttpClient();
                    HttpPost post = new HttpPost(SERVER_URL);

                    // set POST parameters
                    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                    postParameters.add(new BasicNameValuePair("email", params[0]));
                    postParameters.add(new BasicNameValuePair("password", params[1]));
                    post.setEntity(new UrlEncodedFormEntity(postParameters));

                    //Perform the request and check the status code
                    HttpResponse response = client.execute(post);
                    StatusLine statusLine = response.getStatusLine();
                    if (statusLine.getStatusCode() == 200) {
                        HttpEntity entity = response.getEntity();
                        InputStream content = entity.getContent();

                        try {
                            //Read the server response and attempt to parse it as JSON
                            Reader reader = new InputStreamReader(content);

                            GsonBuilder gsonBuilder = new GsonBuilder();
                            Gson gson = gsonBuilder.create();
                            List<LoginResponseUser> posts = new ArrayList<LoginResponseUser>();
                            posts = Arrays.asList(gson.fromJson(reader, LoginResponseUser.class));
                            content.close();
                            Log.e(TAG, "packet recieved");
                            if (posts.get(0).error == true)
                                return null;
                            User usr = new User(posts.get(0), params[0], params[1]);
                            return usr;
                        } catch (Exception ex) {
                            Log.e(TAG, "Failed to parse JSON due to: " + ex);
                        }
                    } else {
                        Log.e(TAG, "Server responded with status code: " + statusLine.getStatusCode());
                    }
                } catch (Exception ex) {
                    Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
                }
                return null;
            }
        };

        User user = null;
        try {
             user = task.execute(username, password).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return user;
    }


    public void SendNewsfeedPost(String message)
    {
        AsyncTask<String, Void, Void> task = new AsyncTask<String, Void, Void>() {

            private static final String TAG = "NewsFeedReader";
            public static final String SERVER_URL = "http://134.0.27.180/NewsfeedAdder.php";
            @Override
            protected Void doInBackground(String... params) {
                try {
                    //Create an HTTP client
                    HttpClient client = new DefaultHttpClient();
                    HttpPost post = new HttpPost(SERVER_URL);
                    Log.e(TAG, "sending data to database");

                    // set POST parameters
                    ArrayList<BasicNameValuePair> postParameters = new ArrayList<>();
                    postParameters.add(new BasicNameValuePair("user", params[0]));
                    postParameters.add(new BasicNameValuePair("password", params[1]));
                    postParameters.add(new BasicNameValuePair("message", params[2]));

                    post.setEntity(new UrlEncodedFormEntity(postParameters));

                    //Perform the request and check the status code
                    HttpResponse response = client.execute(post);
                    Log.e(TAG, "finished sending");


                } catch (Exception ex) {
                    Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
                }
                return null;
            }
        };
        User user = ControllerFactory.getCurrentUser();
        task.execute(user.getUsername_(), user.getPassword_(),message);
    }

    public List<NewsFeed> GetNewsFeedList() {
        AsyncTask<String, Void, List<NewsFeed>> task = new AsyncTask<String, Void, List<NewsFeed>>() {

            private static final String TAG = "NewsFeedReader";
            public static final String SERVER_URL = "http://134.0.27.180/NewsfeedReader.php";

            @Override
            protected List<NewsFeed> doInBackground (String...params){

                try {
                    //Create an HTTP client
                    HttpClient client = new DefaultHttpClient();
                    HttpPost post = new HttpPost(SERVER_URL);

                    // set POST parameters
                    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                    postParameters.add(new BasicNameValuePair("user", params[0]));
                    postParameters.add(new BasicNameValuePair("password", params[1]));
                    post.setEntity(new UrlEncodedFormEntity(postParameters));

                    //Perform the request and check the status code
                    HttpResponse response = client.execute(post);
                    StatusLine statusLine = response.getStatusLine();
                    if (statusLine.getStatusCode() == 200) {
                        HttpEntity entity = response.getEntity();
                        InputStream content = entity.getContent();

                        try {
                            //Read the server response and attempt to parse it as JSON
                            Reader reader = new InputStreamReader(content);

                            GsonBuilder gsonBuilder = new GsonBuilder();
                            gsonBuilder.setDateFormat("yyyy-mm-dd hh:mm:ss");
                            Gson gson = gsonBuilder.create();

                            List<NewsFeed> posts = new ArrayList<NewsFeed>();
                            JsonParser parser = new JsonParser();
                            JsonArray jArray = parser.parse(reader).getAsJsonArray();

                            for(JsonElement obj : jArray )
                            {
                                NewsFeed cse = gson.fromJson( obj , NewsFeed.class);
                                posts.add(cse);
                            }

                            content.close();
                            Log.e(TAG, "packet recieved");
                            return posts;
                        } catch (Exception ex) {
                            Log.e(TAG, "Failed to parse JSON due to: " + ex);
                        }
                    } else {
                        Log.e(TAG, "Server responded with status code: " + statusLine.getStatusCode());
                    }
                } catch (Exception ex) {
                    Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
                }
                return new ArrayList<NewsFeed>();
            }
        };
        List<NewsFeed> result = null;
        try {
            User user = ControllerFactory.getCurrentUser();
            Log.e("NewsReader",user.getUsername_() + user.getPassword_());
            result = task.execute(user.getUsername_(),user.getPassword_()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
