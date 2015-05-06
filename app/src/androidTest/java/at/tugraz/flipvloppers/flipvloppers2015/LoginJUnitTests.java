package at.tugraz.flipvloppers.flipvloppers2015;

import junit.framework.TestCase;

import java.util.concurrent.ExecutionException;

import at.tugraz.flipvloppers.flipvloppers2015.controller.LoginController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

public class LoginJUnitTests extends TestCase{

    public void testLogin()
    {
        try {
            User correct_user = new LoginController().execute("kurt", "123").get();
            assertNotNull(correct_user);

            User false_user = new LoginController().execute("kurt", "1234").get();
            assertNull(false_user);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
