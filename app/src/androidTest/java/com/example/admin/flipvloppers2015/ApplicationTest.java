package com.example.admin.flipvloppers2015;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import com.robotium.solo.Solo;

import com.example.admin.flipvloppers2015.MyActivity;
import com.example.admin.flipvloppers2015.R;
/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MyActivity> {

    private Solo mySolo;
    public ApplicationTest() {
        super(MyActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws  Exception{

    }

    public void testButtons(){

        for (int i = 0 ; i <= 9 ; i++){
            mySolo.clickOnButton(Integer.toString(i));
        }
        mySolo.clickOnButton("+");
        mySolo.clickOnButton("-");
        mySolo.clickOnButton("/");
        mySolo.clickOnButton("*");

        mySolo.clickOnButton("=");
        mySolo.clickOnButton("C");
    }
}

