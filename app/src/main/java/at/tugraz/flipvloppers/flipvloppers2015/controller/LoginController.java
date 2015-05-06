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

import at.tugraz.flipvloppers.flipvloppers2015.configuration.Configuration;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Activity;
import android.util.Log;
import android.os.AsyncTask;

/**
 * Created by Admin on 22.04.2015.
 */
public class LoginController {
    public boolean Login(String username,String password)
    {
        User user = ControllerFactory.GetWebserviceControllerInstance().CheckLogin(username, password);
        Log.e("Login","Logged in " + user.getUsername_());
        ControllerFactory.setCurrentUser(user);
        return (user != null);
    }

    public void Logout()
    {
        ControllerFactory.setCurrentUser(null);
    }
}
