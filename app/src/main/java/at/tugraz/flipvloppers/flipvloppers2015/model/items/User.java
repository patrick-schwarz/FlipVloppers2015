package at.tugraz.flipvloppers.flipvloppers2015.model.items;

import java.io.Serializable;

/**
 * Created by Admin on 22.04.2015.
 */
public class User implements Serializable {
    int id_;
    String username_;
    String first_name_;
    String last_name_;
    String password_;

    public User()
    {
        username_="";
        first_name_="";
        last_name_="";
        password_="";
    }

    public User(String username)
    {
        this.username_ = username;
    }
    public User(LoginResponseUser response, String username, String password)
    {
        id_ = response.id;
        username_ = username;
        password_ = password;
        first_name_ = response.first_name;
        last_name_ = response.last_name;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public String getUsername_() {
        return username_;
    }

    public void setUsername_(String username_) {
        this.username_ = username_;
    }

    public String getFirstName() {
        return first_name_;
    }

    public void setFirstName(String first_name_) {
        this.first_name_ = first_name_;
    }

    public String getLastName() {
        return last_name_;
    }
    public String getName()
    {
        return last_name_ + " " + first_name_;
    }
    public String getNameUsername()
    {
        return getName() + " (" + username_ + ")";
    }

    public void setLastName(String last_name_) {
        this.last_name_ = last_name_;
    }

    public String getPassword_() {
        return password_;
    }

    public void setPassword_(String password_) {
        this.password_ = password_;
    }

}
