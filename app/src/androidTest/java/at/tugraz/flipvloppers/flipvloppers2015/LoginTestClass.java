package at.tugraz.flipvloppers.flipvloppers2015;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;

import java.util.Random;
import java.util.UUID;

import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;

public class LoginTestClass extends ActivityInstrumentationTestCase2<LoginActivity> {
    private Solo mySolo;

    public LoginTestClass() {
        super(LoginActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        mySolo.finishOpenedActivities();
    }

    /*
    public void testSomething()
    {
        assertTrue("Error.", mySolo.waitForText(mySolo.getString(R.string.hello_world)));
    }*/

    private void login()
    {
        mySolo.clickOnButton("Login");
        mySolo.waitForActivity(NewsfeedActivity.class, 1000);
        assertTrue("Newsfeed", mySolo.waitForText("Newsfeed"));

    }

    public void testNewsfeedOpens()
    {
        login();

        mySolo.clickOnButton("+");
        LinearLayout messageSection = (LinearLayout) mySolo.getView(R.id.llMessageSection);

        assertEquals(View.VISIBLE, messageSection.getVisibility());

        mySolo.clickOnButton("-");

        LinearLayout messageSection2 = (LinearLayout) mySolo.getView(R.id.llMessageSection);
        assertEquals(View.GONE, messageSection2.getVisibility());
    }

    public void testAddMessage()
    {
        String rnd = UUID.randomUUID().toString();
        login();
        mySolo.clickOnButton("+");
        EditText message = (EditText) mySolo.getView(R.id.editTextMessage);
        mySolo.enterText(message, rnd);
        mySolo.clickOnButton("Send");

        LinearLayout messageSection = (LinearLayout) mySolo.getView(R.id.llMessageSection);
        assertEquals(View.GONE, messageSection.getVisibility());

        assertTrue("Error sending msg", mySolo.waitForText(rnd));
    }

    public void testScroll()
    {
        login();
        ListView listView =(ListView) mySolo.getView(R.id.listPosts);

        assertNotNull(listView);

        mySolo.scrollListToTop(listView);

        NewsFeed messagefirst = (NewsFeed)listView.getItemAtPosition(0);
        TextView topText = mySolo.getText(messagefirst.getMessage(), true);

        assertNotNull(topText);

        mySolo.scrollListToBottom(listView);


        NewsFeed messagelast = (NewsFeed)listView.getItemAtPosition(listView.getCount() - 1);
        TextView bottomText = mySolo.getText(messagelast.getMessage(), true);

        assertNotNull(bottomText);

        mySolo.scrollListToTop(listView);

        topText = mySolo.getText(messagefirst.getMessage(), true);

        assertNotNull(topText);
    }



    public void testFillInLoginData()
    {
        EditText username = (EditText) mySolo.getView(R.id.editTextUsername);
        mySolo.clearEditText(username);
        mySolo.enterText(username, "kurtWinter");
        assertEquals("kurtWinter", username.getText().toString());

        EditText password = (EditText) mySolo.getView(R.id.editTextPassword);
        mySolo.clearEditText(password);
        mySolo.enterText(password, "test123");
        assertEquals("test123", password.getText().toString());
    }

    public void testClickCheckbox()
    {
        assertTrue("Error. Testbox should not be checked", !mySolo.isCheckBoxChecked(0));
        mySolo.clickOnCheckBox(0);
        assertTrue("Error. Testbox should be checked", mySolo.isCheckBoxChecked(0));
    }
}