package at.tugraz.flipvloppers.flipvloppers2015;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.AbsListView;
import android.widget.TextView;

import com.robotium.solo.Solo;
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
        mySolo.clickOnButton(0);
        mySolo.clickOnButton(1);
    }

    public void testScrollUpAndDown()
    {
        AbsListView scrollView =(AbsListView) mySolo.getView(R.id.listPosts);

        assertNotNull(scrollView);

        mySolo.scrollListToTop(scrollView);

        TextView top = (TextView)scrollView.getChildAt(0).findViewById(R.id.feed_message);
        top.setText("First");
        TextView topText = mySolo.getText(top.getText().toString(), true);

        assertNotNull(topText);

        mySolo.scrollListToBottom(scrollView);

        TextView bottom = (TextView)scrollView.getChildAt(scrollView.getChildCount()-1).findViewById(R.id.feed_message);
        bottom.setText("last");
        TextView bottomText = mySolo.getText(bottom.getText().toString(), true);

        assertNotNull(bottomText);

        mySolo.scrollListToTop(scrollView);

        bottom.setText("last");
        bottomText = mySolo.getText(bottom.getText().toString(), true);

        assertNull(bottomText);
    }
}
