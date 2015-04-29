package at.tugraz.flipvloppers.flipvloppers2015.utils;

import android.widget.Button;
import android.widget.EditText;
import at.tugraz.flipvloppers.flipvloppers2015.R;

import com.robotium.solo.Solo;
/**
 * Created by Admin on 29.04.2015.
 */
public class Utils {

    public static void Login(Solo mySolo)
    {
        EditText username = (EditText) mySolo.getView(R.id.editTextUsername);
        mySolo.clearEditText(username);
        mySolo.enterText(username, "kurtWinter");

        EditText password = (EditText) mySolo.getView(R.id.editTextPassword);
        mySolo.clearEditText(password);
        mySolo.enterText(password, "test123");

        mySolo.clickOnButton(R.id.buttonLogin);
    }
    public static void Logout(Solo mySolo)
    {

    }
}
