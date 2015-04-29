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
public class NewsFeedController extends AsyncTask<User, Void, ArrayList<NewsFeed>> {
    private static final String TAG = "NewsFeedReader";
    public static final String SERVER_URL = "http://134.0.27.180/NewsfeedReader.php";

    @Override
    protected ArrayList<NewsFeed> doInBackground(User... params) {

        try {
            //Create an HTTP client
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(SERVER_URL);

            // set POST parameters
            ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("user", params[0].getUsername_()));
            postParameters.add(new BasicNameValuePair("password", params[0].getPassword_()));
            post.setEntity(new UrlEncodedFormEntity(postParameters));

            //Perform the request and check the status code
            HttpResponse response = client.execute(post);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();

                try {
                    //Read the server response and attempt to parse it as JSON
                    Reader reader = new InputStreamReader(content);

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("yyyy-mm-dd hh:mm:ss");
                    Gson gson = gsonBuilder.create();
                    List<NewsFeed> posts = new ArrayList<NewsFeed>();
                    posts = Arrays.asList(gson.fromJson(reader, NewsFeed[].class));
                    content.close();
                    Log.e(TAG, "packet recieved");
                    return new ArrayList<NewsFeed>(posts);
                } catch (Exception ex) {
                    Log.e(TAG, "Failed to parse JSON due to: " + ex);
                }
            } else {
                Log.e(TAG, "Server responded with status code: " + statusLine.getStatusCode());
            }
        } catch(Exception ex) {
            Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
        }
        return new ArrayList<NewsFeed>();
    }
}
