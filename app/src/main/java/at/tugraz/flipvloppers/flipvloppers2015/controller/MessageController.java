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
    public void SendMessageToUser(String text,Integer to_id)
    {
        ControllerFactory.GetWebserviceControllerInstance().SendMessageToUser(to_id,text);
    }
    public void SendMessageToUser(Message m)
    {
        ControllerFactory.GetWebserviceControllerInstance().SendMessageToUser(m.getTo_id(),m.getMessage());
    }

    public List<Message> GetMessagesFromUser(String username_from)
    {
        return ControllerFactory.GetWebserviceControllerInstance().GetMessagesFrom(username_from);
    }
}
