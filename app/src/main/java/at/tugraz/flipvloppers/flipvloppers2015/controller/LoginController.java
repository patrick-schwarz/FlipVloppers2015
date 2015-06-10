package at.tugraz.flipvloppers.flipvloppers2015.controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;


/**
 * @author kwinter
 * @author tfeiertag
 */
public class LoginController {

    protected void onCreate(Bundle savedInstanceState) {
    }

    public boolean Login(String username, String password) {
        User user = ControllerFactory.GetWebserviceControllerInstance().CheckLogin(username, password);
        ControllerFactory.setCurrentUser(user);

        if (user != null)
            ControllerFactory.GetUserControllerInstance().resetUser();

        return (user != null);
    }


}
