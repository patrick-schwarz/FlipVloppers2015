package at.tugraz.flipvloppers.flipvloppers2015;

import at.tugraz.flipvloppers.flipvloppers2015.utils.Utils;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.robotium.solo.Solo;
/**
 * Created by Admin on 29.04.2015.
 */

public class NewsFeedTest extends ActivityInstrumentationTestCase2<NewsFeedActivity> {
    private Solo mySolo;

    public NewsFeedTest() {
        super(NewsFeedActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
        Utils.Login(mySolo);
    }

    public void tearDown() throws Exception {
        mySolo.finishOpenedActivities();
        Utils.Logout(mySolo);
    }

    public void testPressAllButtons()
    {
        mySolo.clickOnButton(0);
        mySolo.clickOnButton(1);
    }

    public void testScrollUpAndDown()
    {
        ScrollView scrollView =(ScrollView) mySolo.getView(R.id.editTextUsername);

        assertNotNull(scrollView);

        mySolo.scrollToTop();

        TextView top = mySolo.getText(scrollView.getTop());
        TextView topText = mySolo.getText(top.getText().toString(), true);

        assertNotNull(topText);

        mySolo.scrollToBottom();

        TextView bottom = mySolo.getText(scrollView.getBottom());
        TextView bottomText = mySolo.getText(bottom.getText().toString(), true);

        assertNotNull(bottomText);
    }
}
