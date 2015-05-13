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
        Message message = new Message();
        message.setId_user_sender(from.getId_());
        message.setId_(messages.size() + 1);
        message.setText(text);
        message.setId_user_receiver(to.getId_());

        //TODO Send Message
    }

    public void SendMessageToGroup(String text,int groupID)
    {
        User from = ControllerFactory.getCurrentUser();
        Message message = new Message();
        message.setId_user_sender(from.getId_());
        message.setId_(messages.size() + 1);
        message.setText(text);
        message.setIdgroup_(groupID);
        //TODO Send Message

    }

    public Message GetMessage(int ID)
    {
        User user = ControllerFactory.getCurrentUser();
        List<Message> response = GetMessages();
        for (Message message : response) {
            if (message.getId_() == ID &&
                    user.getId_() == message.getId_user_receiver());
                return message;
        }
        return null;
    }

    public List<Message> GetMessagesFromUser(User from)
    {
        User user = ControllerFactory.getCurrentUser();
        List<Message> response = GetMessages();
        List<Message> result = new ArrayList<Message>();
        for (Message message : response) {
            if (message.getId_user_receiver() == user.getId_() &&
                    message.getId_user_sender() == from.getId_());
                    result.add(message);
        }
        return result;
    }

    public List<Message> GetMessages()
    {
        if(messages.size() == 0)
            messages.add(new Message());
        return messages;
    }
}
