package at.tugraz.flipvloppers.flipvloppers2015.model.items;

public class LoginResponseUser {

    public boolean error;
    public int id;
    public String first_name;
    public String last_name;

    public LoginResponseUser(boolean error, int id, String first_name, String last_name) {
        this.error = error;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
