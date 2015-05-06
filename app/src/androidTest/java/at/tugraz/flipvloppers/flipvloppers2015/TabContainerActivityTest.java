package at.tugraz.flipvloppers.flipvloppers2015;

import android.app.TabActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.robotium.solo.Solo;


public class TabContainerActivityTest extends ActivityInstrumentationTestCase2<TabContainerActivity> {

    private Solo mySolo;


    public TabContainerActivityTest()
    {
        super(TabContainerActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        //mySolo = new Solo(getInstrumentation(), TabActivity.getLocalActivityManger().getCurrentActivity());
    }

    public void tearDown() throws Exception {
        mySolo.finishOpenedActivities();
    }

    public void testTabBar(){

        //ViewGroup tabs = (TabWidget) mySolo.getView(android.R.id.tabs);
//        View testoptions = tabs.getChildAt(tabs.getChildCount());
//        View testnewsfeed = tabs.getChildAt(tabs.getChildCount());
//        View testchat = tabs.getChildAt(tabs.getChildCount());
//        View testcontacts = tabs.getChildAt(tabs.getChildCount());
//
//        mySolo.clickOnView(testoptions);
//        mySolo.clickOnView(testnewsfeed);
//        mySolo.clickOnView(testchat);
//        mySolo.clickOnView(testcontacts);


        //System.out.println(tabs.getChildCount());
    }

}