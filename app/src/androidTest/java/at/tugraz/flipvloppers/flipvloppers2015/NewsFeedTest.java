package at.tugraz.flipvloppers.flipvloppers2015;

import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.robotium.solo.Solo;

import at.tugraz.flipvloppers.flipvloppers2015.model.items.LoginResponseUser;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * Created by Admin on 29.04.2015.
 */

public class NewsFeedTest extends ActivityInstrumentationTestCase2<NewsfeedActivity> {
    private Solo mySolo;
    private User user;
    public NewsFeedTest() {
        super(NewsfeedActivity.class);
    }

    public void setUp() throws Exception {
        Intent nextScreen = new Intent(getActivity().getApplicationContext(), NewsFeed.class);
        nextScreen.putExtra("user", new Gson().toJson(user));
        setActivityIntent(nextScreen);
        super.setUp();
        user = new User();
        user.setId_(1);
        user.setUsername_("user");
        user.setFirstName("user");
        user.setPassword_("password");
        user.setLastName("user");


        mySolo = new Solo(getInstrumentation(), getActivity());


        getActivity().user = user;
        //Utils.Login(mySolo);
    }

    public void tearDown() throws Exception {
        mySolo.finishOpenedActivities();
        //Utils.Logout(mySolo);
    }

    /*public void testPressAllButtons()
    {
        //mySolo.clickOnButton(0);
        //mySolo.clickOnButton(1);
    }
    public void testMessageSend(){

    }*/
    public void testScrollUpAndDown()
    {
        ListView listView =(ListView) mySolo.getView(R.id.listPosts);

        assertNotNull(listView);

        mySolo.scrollListToTop(listView);

        NewsFeed messagefirst = (NewsFeed)listView.getItemAtPosition(0);
        TextView topText = mySolo.getText(messagefirst.getMessage(), true);

        assertNotNull(topText);

        mySolo.scrollListToBottom(listView);


        NewsFeed messagelast = (NewsFeed)listView.getItemAtPosition(0);
        TextView bottomText = mySolo.getText(messagelast.getMessage(), true);

        assertNotNull(bottomText);

        mySolo.scrollListToTop(listView);

        bottomText = mySolo.getText(messagelast.getMessage().toString(), true);

        assertNull(bottomText);
    }
}
