package at.tugraz.flipvloppers.flipvloppers2015.model.items;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Matthias on 29.04.2015.
 */
public class NewsFeed {
    public int id;
    public String user;
    public String first;
    public String last;
    public Date date;
    public String message;

    public NewsFeed(int id, String user, String first, String last, Date date, String message)
    {
        this.id = id;
        this.user = user;
        this.first = first;
        this.last = last;
        this.date = date;
        this.message = message;
    }


    public String getUser() {
        return user;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
