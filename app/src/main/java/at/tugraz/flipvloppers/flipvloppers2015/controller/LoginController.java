package at.tugraz.flipvloppers.flipvloppers2015.controller;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.util.Log;
import android.os.AsyncTask;

/**
 * Created by Admin on 22.04.2015.
 */
public class LoginController extends AsyncTask<String, Void, User> {
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
            if(statusLine.getStatusCode() == 200) {
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
                    return  usr;
                } catch (Exception ex) {
                    Log.e(TAG, "Failed to parse JSON due to: " + ex);
                }
            } else {
                Log.e(TAG, "Server responded with status code: " + statusLine.getStatusCode());
            }
        } catch(Exception ex) {
            Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
        }
        return null;
    }
}
