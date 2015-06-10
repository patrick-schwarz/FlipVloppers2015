package at.tugraz.flipvloppers.flipvloppers2015;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.LoginController;
import at.tugraz.flipvloppers.flipvloppers2015.controller.UserController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * Created by PS/KW.
 */
public class ContactsActivityTest extends ActivityInstrumentationTestCase2 {
    private Solo mySolo;

    public ContactsActivityTest() {
        super(ContactsActivity.class);
    }

    User user;
    LoginController loginCtrl;
    UserController userCtrl;
    boolean logged_in = false;

    public void setUp() throws Exception {

        super.setUp();
        loginCtrl = ControllerFactory.GetLoginControllerInstance();
        boolean logged_in = loginCtrl.Login("kurt", "123");
        userCtrl = ControllerFactory.GetUserControllerInstance();

        mySolo = new Solo(getInstrumentation(), getActivity());
        assertTrue(logged_in);
    }

    public void tearDown() throws Exception {

        mySolo.finishOpenedActivities();
    }

    public void testScrollUpAndDown() {
        ListView listView = (ListView) mySolo.getView(R.id.listContacts);

        assertNotNull(listView);

        mySolo.scrollListToTop(listView);

        List<User> users = userCtrl.getUsers();

        assertTrue((users.size() > 0));
        User user = users.get(0);
        TextView topText = mySolo.getText(user.getLastName().toString(), true);

        assertNotNull(topText);

        mySolo.scrollListToBottom(listView);


        User userlast = users.get(users.size() - 1);
        TextView bottomText = mySolo.getText(userlast.getLastName().toString(), true);

        assertNotNull(bottomText);
    }

    public void testFindTestUser() {
        ListView listView = (ListView) mySolo.getView(R.id.listContacts);

        assertNotNull(listView);

        mySolo.scrollListToTop(listView);


        List<User> users = userCtrl.getUsers();

        assertTrue((users.size() > 0));
        User user = users.get(0);
        TextView topText = mySolo.getText(user.getLastName().toString(), false);

        assertNotNull(topText);
    }

    public void testSearchForUser() {
        List<User> users = userCtrl.getUsers();
        SearchView viewSearch = (SearchView) mySolo.getView(R.id.searchViewContacts);
        mySolo.clickOnView(viewSearch);

        assertTrue(mySolo.waitForView(R.id.searchViewContacts));

        for (int c = 0; c < users.size(); c += 2) {
            mySolo.clickOnView(viewSearch);
            mySolo.clearEditText(0);
            mySolo.enterText(0, users.get(c).getLastName().toString());
            mySolo.waitForText(users.get(c).getName());
            mySolo.getText(users.get(c).getName(), true);
        }
    }
}