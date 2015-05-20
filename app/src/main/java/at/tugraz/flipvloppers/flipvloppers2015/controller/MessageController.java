package at.tugraz.flipvloppers.flipvloppers2015.controller;

import java.util.ArrayList;
import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * Created by Admin on 06.05.2015.
 */
public class MessageController {

    List<Message> messages;
    public MessageController()
    {
        messages = new ArrayList<Message>();
    }
    public void SendMessageToUser(String text,User to)
    {
        User from = ControllerFactory.getCurrentUser();
        Message message = new Message(0,from.getId_(),to.getId_(),text);
        ControllerFactory.GetWebserviceControllerInstance().SendMessageToUser(message);
    }

   /* public void SendMessageToGroup(String text,int groupID)
    {
        User from = ControllerFactory.getCurrentUser();
        Message message = new Message();
        message.setId_user_sender(from.getId_());
        message.setId_(messages.size() + 1);
        message.setText(text);
        message.setIdgroup_(groupID);
        //TODO Send Message

    }*/

    public List<Message> GetMessagesFromUser(User from)
    {
        User user = ControllerFactory.getCurrentUser();
        return ControllerFactory.GetWebserviceControllerInstance().GetMessagesFrom(from);
    }
}
