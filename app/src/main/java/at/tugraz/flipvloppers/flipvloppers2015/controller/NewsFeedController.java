package at.tugraz.flipvloppers.flipvloppers2015.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;

/**
 * Created by Admin on 22.04.2015.
 */
public class NewsFeedController {

  public List<Message> getList(int offset,int size)
  {
      List<Message> messages = new ArrayList<Message>();
      messages.add(new Message());
      messages.add(new Message());
      messages.add(new Message());
      messages.add(new Message());
      return messages;
  }

    /*
    /* @return returns the id of the new/edited message
     */
    public int addOrUpdate(Message m)
    {
        if(m.getId_() == 0)
        {
            //Add
        }
        else
        {
            //Update
        }
        return m.getId_();
    }

    public boolean delete(Message m)
    {
        return true;
    }
}
