package at.tugraz.flipvloppers.flipvloppers2015.controller;

import java.util.ArrayList;
import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * Created by Admin on 06.05.2015.
 */
public class UserController {

    private List<User> users = null;

    public UserController()
    {
        users = new ArrayList<User>();
    }

    public User GetUser(int ID)
    {
        List<User> response = GetUsers();
        for (User user : response) {
            if (user.getId_() == ID);
                return user;
        }
        return null;
    }
    public User GetUser(String username)
    {
        List<User> response = GetUsers();
        for (User user : response) {
            if (user.getUsername_().toLowerCase() == username.toLowerCase());
                return user;
        }
        return null;
    }
    public List<User> GetUsers()
    {
        if(users.size() == 0)
            users.add(new User());
        return users;
    }
    public List<User> GetUsers(String filter) {
        List<User> response = GetUsers();
        List<User> result = new ArrayList<User>();

        for (User user : response) {
            if (user.getUsername_().toLowerCase().startsWith(filter.toLowerCase()) ||
                    user.getFirstName().toLowerCase().startsWith(filter.toLowerCase()) ||
                    user.getLastName().toLowerCase().startsWith(filter.toLowerCase()))
                result.add(user);
        }

        return result;
    }
}
