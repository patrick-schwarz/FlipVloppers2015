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
        users = ControllerFactory.GetWebserviceControllerInstance().getUsers();
    }

    public User getUser(int ID)
    {
        List<User> response = getUsers();
        for (User user : response) {
            if (user.getId_() == ID);
                return user;
        }
        return null;
    }
    public User getUser(String username)
    {
        List<User> response = getUsers();
        for (User user : response) {
            if (user.getUsername_().toLowerCase() == username.toLowerCase());
                return user;
        }
        return null;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public List<User> getUsers(String filter) {
        List<User> response = getUsers();
        List<User> result = new ArrayList<User>();

        if(filter.replace(" ","") == "")
            return response;

        for (User user : response) {
            if (user.getUsername_().toLowerCase().startsWith(filter.toLowerCase()) ||
                    user.getFirstName().toLowerCase().startsWith(filter.toLowerCase()) ||
                    user.getLastName().toLowerCase().startsWith(filter.toLowerCase()))
                result.add(user);
        }

        return result;
    }
}
