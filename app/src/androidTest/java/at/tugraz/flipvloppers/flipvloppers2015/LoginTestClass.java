package at.tugraz.flipvloppers.flipvloppers2015;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

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
        mySolo.finishOpenedActivities();
    }

    /*
    public void testSomething()
    {
        assertTrue("Error.", mySolo.waitForText(mySolo.getString(R.string.hello_world)));
    }*/

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