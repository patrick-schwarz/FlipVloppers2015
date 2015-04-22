package at.tugraz.flipvloppers.flipvloppers2015;

import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;

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
    }

    public void testSomething()
    {
        assertTrue("Error.", mySolo.waitForText(mySolo.getString(R.string.hello_world)));
    }
}