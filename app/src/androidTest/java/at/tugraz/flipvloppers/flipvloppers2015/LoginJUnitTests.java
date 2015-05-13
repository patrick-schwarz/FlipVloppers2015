package at.tugraz.flipvloppers.flipvloppers2015;

import junit.framework.TestCase;

import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.LoginController;

public class LoginJUnitTests extends TestCase {

    public void testLogin() {
        LoginController loginCtrl = ControllerFactory.GetLoginControllerInstance();

        boolean logged_in = loginCtrl.Login("kurt", "123");
        assertTrue(logged_in);

        logged_in = loginCtrl.Login("kurt", "1234");
        assertFalse(logged_in);
    }
}
