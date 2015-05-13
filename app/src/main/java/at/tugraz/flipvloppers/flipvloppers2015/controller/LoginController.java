package at.tugraz.flipvloppers.flipvloppers2015.controller;

import android.util.Log;

import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * @author kwinter
 * @author tfeiertag
 */
public class LoginController {
    public boolean Login(String username,String password)
    {
        User user = ControllerFactory.GetWebserviceControllerInstance().CheckLogin(username, password);
        ControllerFactory.setCurrentUser(user);
        return (user != null);
    }

    public void Logout()
    {
        ControllerFactory.setCurrentUser(null);
    }
}
