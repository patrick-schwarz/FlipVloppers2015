package at.tugraz.flipvloppers.flipvloppers2015;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.LoginController;
import at.tugraz.flipvloppers.flipvloppers2015.controller.MessageController;
import at.tugraz.flipvloppers.flipvloppers2015.controller.UserController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * Created by Admin on 03.06.2015.
 */
public class MessageActivityTest extends ActivityInstrumentationTestCase2<MessageActivity> {

    private Solo mySolo;
    User user;
    LoginController loginCtrl;
    UserController userCtrl;
    MessageController messageCtrl;
    boolean logged_in = false;
    User from = null;

    public MessageActivityTest() {
        super(MessageActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();


        loginCtrl = ControllerFactory.GetLoginControllerInstance();
        boolean logged_in = loginCtrl.Login("kurt", "123");
        user = ControllerFactory.getCurrentUser();
        userCtrl = ControllerFactory.GetUserControllerInstance();
        messageCtrl = ControllerFactory.GetMessageControllerInstance();
        from = userCtrl.getUser("sexybunny");

        Intent i = new Intent();
        i.setClassName("at.tugraz.flipvloppers.flipvloppers2015",
                "at.tugraz.flipvloppers.flipvloppers2015.MessageActivity");
        i.putExtra("from", from);
        setActivityIntent(i);

        mySolo = new Solo(getInstrumentation(), getActivity());
        assertTrue(logged_in);
    }

    public void tearDown() throws Exception {
        mySolo.finishOpenedActivities();
    }

    public void testSendMessage()
    {
        List<Message> messages = messageCtrl.GetMessagesFromUser(from.getUsername_());

        ListView listView =(ListView) mySolo.getView(R.id.listPosts);

        assertNotNull(listView);

        mySolo.scrollListToTop(listView);

        Message message = new Message(0,user.getId_(),from.getId_(),
                "Das ist Testnachricht " + messages.size());

        Button btnSend = (Button) mySolo.getView(R.id.buttonSend);
        EditText etMessage = (EditText) mySolo.getView(R.id.editTextMessage);

        mySolo.enterText(etMessage,message.getMessage());
        mySolo.clickOnView(btnSend);

        TextView topText = mySolo.getText(message.getMessage().toString(), true);

        assertNotNull(topText);
    }
    public void testScrollUpAndDown()
    {
        List<Message> messages = messageCtrl.GetMessagesFromUser(from.getUsername_());
        ListView listView =(ListView) mySolo.getView(R.id.listPosts);

        assertNotNull(listView);

        mySolo.scrollListToTop(listView);

        assertTrue((messages.size() > 0));

        TextView topText = mySolo.getText(messages.get(0).getMessage().toString(), true);

        assertNotNull(topText);

        mySolo.scrollListToBottom(listView);


        TextView bottomText = mySolo.getText(messages.get((messages.size() - 1)).getMessage().toString(), true);

        assertNotNull(bottomText);
    }
}