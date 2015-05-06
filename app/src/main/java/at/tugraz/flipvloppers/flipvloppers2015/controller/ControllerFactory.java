package at.tugraz.flipvloppers.flipvloppers2015.controller;


import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import at.tugraz.flipvloppers.flipvloppers2015.configuration.Configuration;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * Created by Admin on 22.04.2015.
 */
public class ControllerFactory {

    private static Configuration config;

    static public Configuration GetConfiguration()
    {
        if(config == null)
            config = new Configuration();
        return config;
    }


    private static User currentUser = null;
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    static LoginController lCtrl = null;

    static public LoginController GetLoginControllerInstance()
    {
        if(lCtrl == null)
            lCtrl = new LoginController();
        return lCtrl;
    }

    static NewsFeedController nfCtrl = null;

    static public NewsFeedController GetNewsFeedControllerInstance()
    {
        if(nfCtrl == null)
            nfCtrl = new NewsFeedController();
        return nfCtrl;
    }

    static WebserviceController wsCtrl = null;

    static public WebserviceController GetWebserviceControllerInstance()
    {
        if(wsCtrl == null)
            wsCtrl = new WebserviceController();
        return wsCtrl;
    }
}
