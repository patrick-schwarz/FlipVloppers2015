package at.tugraz.flipvloppers.flipvloppers2015.controller;


import android.content.res.Configuration;

import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * @pschwarz
 */
public class ControllerFactory {

    private static Configuration config;

    static public Configuration GetConfiguration()
    {
        if(config == null)
            config = new Configuration();
        return config;
    }


    private static User currentUser = null;
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    static LoginController lCtrl = null;

    static public LoginController GetLoginControllerInstance()
    {
        if(lCtrl == null)
            lCtrl = new LoginController();
        return lCtrl;
    }

    static NewsFeedController nfCtrl = null;

    static public NewsFeedController GetNewsFeedControllerInstance()
    {
        if(nfCtrl == null)
            nfCtrl = new NewsFeedController();
        return nfCtrl;
    }


    static WebserviceController wsCtrl = null;

    static public WebserviceController GetWebserviceControllerInstance()
    {
        if(wsCtrl == null)
            wsCtrl = new WebserviceController();
        return wsCtrl;
    }

    static UserController usrCtrl = null;

    static public UserController GetUserControllerInstance()
    {
        if(usrCtrl == null)
            usrCtrl = new UserController();
        return usrCtrl;
    }

    static MessageController messageCtrl = null;

    static public MessageController GetMessageControllerInstance()
    {
        if(messageCtrl == null)
            messageCtrl = new MessageController();
        return messageCtrl;
    }

    static EmojiController emojiCtrl = null;

    static public EmojiController GetEmojiControllerInstance()
    {
        if(emojiCtrl == null)
            emojiCtrl = new EmojiController();
        return emojiCtrl;
    }
}
