package at.tugraz.flipvloppers.flipvloppers2015;

import junit.framework.TestCase;

import java.util.List;
import java.util.Random;

import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.LoginController;
import at.tugraz.flipvloppers.flipvloppers2015.controller.NewsFeedController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;

/**
 * @author kwinter
 * @author tfeiertag
 */
public class NewfeedJUnitTests extends TestCase{

    public void testSend()
    {
        LoginController loginCtrl = ControllerFactory.GetLoginControllerInstance();

        boolean logged_in = loginCtrl.Login("kurt", "123");
        assertTrue(logged_in);

        NewsFeedController nfCtrl = ControllerFactory.GetNewsFeedControllerInstance();

        Random rand = new Random();
        int rnd = rand.nextInt();

        nfCtrl.SendNewsfeedPost(rnd+"");

        List<NewsFeed> messageList = nfCtrl.GetNewsFeedList();
        assertEquals(rnd + "", messageList.get(0).getMessage());

        nfCtrl.DeleteNewsfeedPost(rnd+"");

    }

}
