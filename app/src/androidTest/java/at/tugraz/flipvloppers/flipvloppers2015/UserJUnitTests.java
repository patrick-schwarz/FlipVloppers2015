package at.tugraz.flipvloppers.flipvloppers2015;

import junit.framework.TestCase;

import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.LoginController;
import at.tugraz.flipvloppers.flipvloppers2015.controller.UserController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * @author tfeiertag
 * @author meder
 */
public class UserJUnitTests extends TestCase{

    public void testGettingUsers()
    {
        LoginController loginCtrl = ControllerFactory.GetLoginControllerInstance();

        boolean logged_in = loginCtrl.Login("user", "password");
        assertTrue(logged_in);

        User user = ControllerFactory.getCurrentUser();

        UserController nfCtrl = ControllerFactory.GetUserControllerInstance();

        List<User> users = nfCtrl.getUsers();

        assertFalse(users.isEmpty());

        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getId_() == user.getId_())
            {
                assertTrue(false);
            }
        }

    }

}
