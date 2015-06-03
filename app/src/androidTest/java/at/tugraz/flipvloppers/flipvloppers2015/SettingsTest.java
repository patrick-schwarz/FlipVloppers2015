package at.tugraz.flipvloppers.flipvloppers2015;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.robotium.solo.Solo;

import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.LoginController;
import at.tugraz.flipvloppers.flipvloppers2015.controller.UserController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * Created by Chris on 03.06.2015.
 */
public class SettingsTest extends ActivityInstrumentationTestCase2 {


    private Solo mySolo;
    public SettingsTest() {
        super(SettingsAcitivity.class);
    }

    User user;
    LoginController loginCtrl;
    UserController userCtrl;
    boolean logged_in = false;
    public void setUp() throws Exception {

        super.setUp();
        loginCtrl = ControllerFactory.GetLoginControllerInstance();
        boolean logged_in = loginCtrl.Login("kurt", "123");
        userCtrl = ControllerFactory.GetUserControllerInstance();

        mySolo = new Solo(getInstrumentation(), getActivity());
        assertTrue(logged_in);
    }

    public void tearDown() throws Exception {

        mySolo.finishOpenedActivities();
    }


    public void testLogout()
    {
/*
        mySolo.clickOnButton(R.id.btnLogout);

        Activity current = mySolo.getCurrentActivity();

        assertEquals(LoginActivity.class, current);
*/

    }

}