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
    public void setUp() throws Exception {
        super.setUp();

        loginCtrl = ControllerFactory.GetLoginControllerInstance();
        assertTrue(loginCtrl.Login("kurt", "123"));
        userCtrl = ControllerFactory.GetUserControllerInstance();
        messageCtrl = ControllerFactory.GetMessageControllerInstance();
    }

    public void tearDown() throws Exception {

    }

    public void testSendMessageToUser() throws InterruptedException {

        User to = userCtrl.getUser("sexybunny");
        messageCtrl.SendMessageToUser("Das ist ein 1. Testtext von kurt!",  to.getId_());

        Thread.sleep(1000);

        messageCtrl.SendMessageToUser("Das ist ein 2. Testtext von kurt!", to.getId_());

        Thread.sleep(1000);

        messageCtrl.SendMessageToUser("Das ist ein 3. Testtext von kurt!", to.getId_());

        Thread.sleep(1000);

        assertTrue(loginCtrl.Login("sexybunny", "Asdf1234"));


        messageCtrl.SendMessageToUser("Das ist ein 1. Testtext von sexybunny!", to.getId_());

        Thread.sleep(1000);

        messageCtrl.SendMessageToUser("Das ist ein 2. Testtext von sexybunny!",to.getId_());

        Thread.sleep(1000);

        messageCtrl.SendMessageToUser("Das ist ein 3. Testtext von sexybunny!",to.getId_());

        Thread.sleep(1000);

        List<Message> messages = messageCtrl.GetMessagesFromUser("kurt");

        assertEquals(messages.get((messages.size() - 1)).getMessage(), "Das ist ein 3. Testtext von sexybunny!");
        assertEquals(messages.get((messages.size() - 2)).getMessage(), "Das ist ein 2. Testtext von sexybunny!");
        assertEquals(messages.get((messages.size() - 3)).getMessage(), "Das ist ein 1. Testtext von sexybunny!");

    }
}