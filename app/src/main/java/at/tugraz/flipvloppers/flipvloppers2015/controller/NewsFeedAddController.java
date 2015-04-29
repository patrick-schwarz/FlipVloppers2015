package at.tugraz.flipvloppers.flipvloppers2015.controller;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**
 * Created by Matthias on 29.04.2015.
 */
public class NewsFeedAddController extends AsyncTask<String, Void, Void> {
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


            } catch(Exception ex) {
                Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
            }
        return null;
    }
}
