package at.tugraz.flipvloppers.flipvloppers2015.model.items;

/**
 * Created by Admin on 22.04.2015.
 */
public class User {
    int id_;
    String username_;
    String first_name_;
    String password_;
    String salt_;

    public User()
    {

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

    public String getFirst_name_() {
        return first_name_;
    }

    public void setFirst_name_(String first_name_) {
        this.first_name_ = first_name_;
    }

    public String getPassword_() {
        return password_;
    }

    public void setPassword_(String password_) {
        this.password_ = password_;
    }

    public String getSalt_() {
        return salt_;
    }

    public void setSalt_(String salt_) {
        this.salt_ = salt_;
    }
}
