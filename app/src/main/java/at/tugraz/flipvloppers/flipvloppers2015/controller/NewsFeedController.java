package at.tugraz.flipvloppers.flipvloppers2015.controller;

import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;

/**
 * Created by Admin on 22.04.2015.
 */
public class NewsFeedController {
    public void SendNewsfeedPost(String message) {
        ControllerFactory.GetWebserviceControllerInstance().SendNewsfeedPost(message);
    }

    public List<NewsFeed> GetNewsFeedList()
    {
        return ControllerFactory.GetWebserviceControllerInstance().GetNewsFeedList();
    }

    public void DeleteNewsfeedPost(String message) {
        ControllerFactory.GetWebserviceControllerInstance().DeleteNewsfeedPost(message);
    }

}
