package at.tugraz.flipvloppers.flipvloppers2015.controller;

import junit.framework.TestCase;

import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * Created by Admin on 20.05.2015.
 */
public class MessageControllerTest extends TestCase {

    LoginController loginCtrl;
    UserController userCtrl;
    MessageController messageCtrl;
    User current;
    public void setUp() throws Exception {
        super.setUp();

        loginCtrl = ControllerFactory.GetLoginControllerInstance();
        boolean logged_in = loginCtrl.Login("kurt", "123");
        userCtrl = ControllerFactory.GetUserControllerInstance();
        current = ControllerFactory.getCurrentUser();
        messageCtrl = ControllerFactory.GetMessageControllerInstance();
    }

    public void tearDown() throws Exception {

    }

    public void testSendMessageToUser()
    {
        messageCtrl.SendMessageToUser("Das ist ein 1. Testtext von kurt!",new User("sexybunny"));

        messageCtrl.SendMessageToUser("Das ist ein 2. Testtext von kurt!",new User("sexybunny"));

        messageCtrl.SendMessageToUser("Das ist ein 3. Testtext von kurt!",new User("sexybunny"));


        boolean logged_in = loginCtrl.Login("sexybunny", "Asdf1234");


        messageCtrl.SendMessageToUser("Das ist ein 1. Testtext von sexybunny!",new User("kurt"));

        messageCtrl.SendMessageToUser("Das ist ein 2. Testtext von sexybunny!",new User("kurt"));

        messageCtrl.SendMessageToUser("Das ist ein 3. Testtext von sexybunny!",new User("kurt"));

        List<Message> messages = messageCtrl.GetMessagesFromUser(new User("kurt"));

        assertEquals(messages.get((messages.size() - 1)).getMessage() , "Das ist ein 3. Testtext von sexybunny!");
        assertEquals(messages.get((messages.size() - 2)).getMessage() , "Das ist ein 2. Testtext von sexybunny!");
        assertEquals(messages.get((messages.size() - 3)).getMessage() , "Das ist ein 1. Testtext von sexybunny!");
    }
}