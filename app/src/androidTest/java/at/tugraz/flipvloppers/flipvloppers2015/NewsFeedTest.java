package at.tugraz.flipvloppers.flipvloppers2015;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;

import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;

/**
 * Created by Admin on 29.04.2015.
 */

public class NewsFeedTest extends ActivityInstrumentationTestCase2<NewsfeedActivity> {
    private Solo mySolo;

    public NewsFeedTest() {
        super(NewsfeedActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
        //Utils.Login(mySolo);
    }

    public void tearDown() throws Exception {
        mySolo.finishOpenedActivities();
        //Utils.Logout(mySolo);
    }

    public void testPressAllButtons()
    {
        //mySolo.clickOnButton(0);
        //mySolo.clickOnButton(1);
    }

    public void testScrollUpAndDown()
    {
        ListView listView =(ListView) mySolo.getView(R.id.listPosts);

        assertNotNull(listView);

        mySolo.scrollListToTop(listView);

        Message messagefirst = (Message)listView.getItemAtPosition(0);
        TextView topText = mySolo.getText(messagefirst.getText(), true);

        assertNotNull(topText);

        mySolo.scrollListToBottom(listView);


        Message messagelast = (Message)listView.getItemAtPosition(0);
        TextView bottomText = mySolo.getText(messagelast.getText(), true);

        assertNotNull(bottomText);

        mySolo.scrollListToTop(listView);

        bottomText = mySolo.getText(messagelast.getText().toString(), true);

        assertNull(bottomText);
    }
}
