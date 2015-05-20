package at.tugraz.flipvloppers.flipvloppers2015.model.items;

import java.util.Date;

/**
 * Created by Admin on 22.04.2015.
 */
public class Message {

    public int id;
    public int from_id;
    public int to_id;
    public Date date;
    public String message;

    public Message(int id, int from, int to, String message)
    {
        this.id = id;
        this.from_id = from_id;
        this.to_id = to_id;
        this.date = date;
        this.message = message;
    }


    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public int getFrom_id() {
        return from_id;
    }

    public int getTo_id() {
        return to_id;
    }
}
