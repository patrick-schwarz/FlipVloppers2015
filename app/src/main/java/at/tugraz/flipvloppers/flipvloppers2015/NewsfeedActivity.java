package at.tugraz.flipvloppers.flipvloppers2015;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import at.tugraz.flipvloppers.flipvloppers2015.adapter.FeedListAdapter;
import at.tugraz.flipvloppers.flipvloppers2015.controller.NewsFeedAddController;
import at.tugraz.flipvloppers.flipvloppers2015.controller.NewsFeedController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

public class NewsfeedActivity extends Activity{

    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<NewsFeed> messageList;
    private User user;

    private EditText message;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);
        user = new Gson().fromJson(getIntent().getExtras().getString("user"), User.class);
        listView = (ListView) findViewById(R.id.listPosts);
        btnSend = (Button) findViewById(R.id.buttonSend);
        message = (EditText) findViewById(R.id.editTextMessage);

        messageList = getNewsfeed();
        btnClick();

        listAdapter = new FeedListAdapter(this, messageList);
        listView.setAdapter(listAdapter);

        //Generate test data

        //NewsFeed new_msg = new NewsFeed(0, "1", "username", "Mr", new Date(1000), "erster test");

        //messageList.add(new_msg);

        listAdapter.notifyDataSetChanged();
    }

    public void btnClick() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                NewsFeed message1 = new NewsFeed(1, user.getUsername_(), user.getLastName(), user.getFirstName(), new Date(),
                        message.getText().toString());
                //messageList.add(message1);
                newFeedPost(message1);

                listAdapter.notifyDataSetChanged();
                finish();
                startActivity(getIntent());
            }
        });
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

    public void newFeedPost(NewsFeed new_feed)
    {
        Log.v("newFeedPost", "sending data to database newFeedPost");
        try {
            new NewsFeedAddController().execute(user.getUsername_(), user.getPassword_(), new_feed.getMessage());
            messageList = new NewsFeedController().execute(user).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
