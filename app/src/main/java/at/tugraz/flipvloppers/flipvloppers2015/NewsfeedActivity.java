package at.tugraz.flipvloppers.flipvloppers2015;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.google.gson.Gson;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import at.tugraz.flipvloppers.flipvloppers2015.controller.NewsFeedController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.*;

import at.tugraz.flipvloppers.flipvloppers2015.adapter.FeedListAdapter;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;

public class NewsfeedActivity extends Activity{

    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<NewsFeed> messageList;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);
        user =  new Gson().fromJson(getIntent().getExtras().getString("user"), User.class);
        listView = (ListView) findViewById(R.id.list);

        messageList = getNewsfeed();

        listAdapter = new FeedListAdapter(this, messageList);
        listView.setAdapter(listAdapter);

        //Generate test data

        NewsFeed new_msg = new NewsFeed(0, "1", "username", "Mr", new Date(1000), "erster test");

        messageList.add(new_msg);

        listAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    public ArrayList<NewsFeed> getNewsfeed()
    {
        try {
            ArrayList<NewsFeed> msgs = new NewsFeedController().execute(user).get();

            for (NewsFeed feed : msgs)
            {
                Log.e("NewsFeedActivity", "msg: " + feed.getMessage());
            }
            return msgs;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<NewsFeed>();
    }

}
