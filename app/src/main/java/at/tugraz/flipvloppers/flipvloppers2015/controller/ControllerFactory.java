package at.tugraz.flipvloppers.flipvloppers2015.controller;

/**
 * Created by Admin on 22.04.2015.
 */
public class ControllerFactory {
    static LoginController lCtrl = null;
    static public LoginController GetLoginControllerInstance()
    {
        if(lCtrl == null)
            lCtrl = new LoginController();
        return lCtrl;
    }
}
