package at.tugraz.flipvloppers.flipvloppers2015.model.items;

import java.util.List;

/**
 * Created by Admin on 22.04.2015.
 */
public class Group {
    List<User> users_;
    String name_;
    Boolean open_;

    public Group()
    {

    }

    public List<User> getUsers_() {
        return users_;
    }

    public void setUsers_(List<User> users_) {
        this.users_ = users_;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public Boolean isOpen_() {
        return open_;
    }

    public void setOpen_(Boolean open_) {
        this.open_ = open_;
    }
}
