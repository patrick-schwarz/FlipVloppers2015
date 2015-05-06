package at.tugraz.flipvloppers.flipvloppers2015;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.adapter.FeedListAdapter;
import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.NewsFeedController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

public class NewsfeedActivity extends Activity {

    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<NewsFeed> messageList;
    public User user;

    private LinearLayout messageSection;
    private EditText message;
    private Button btnSend;
    private Button btnOpen;
    private NewsFeedController nfCtrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);
        user = ControllerFactory.getCurrentUser();
        nfCtrl = ControllerFactory.GetNewsFeedControllerInstance();
        listView = (ListView) findViewById(R.id.listPosts);
        btnSend = (Button) findViewById(R.id.buttonSend);
        message = (EditText) findViewById(R.id.editTextMessage);
        btnOpen = (Button) findViewById(R.id.buttonOpen);
        messageSection = (LinearLayout) findViewById(R.id.llMessageSection);
        messageSection.setVisibility(View.GONE);

        messageList = getNewsfeed();
        btnClick();
        btnOpen();

        listAdapter = new FeedListAdapter(this, messageList);
        listView.setAdapter(listAdapter);
        Thread thread = new Thread(){
            public void run(){
                refreshNews();
            }
        };

        thread.start();

        listAdapter.notifyDataSetChanged();
    }

    public void refreshNews()
    {
        try {
            while(true) {
                Thread.sleep(5000);
                if (listIsAtTop()) {
                    List<NewsFeed> new_list= getNewsfeed();
                    if (messageList.size() == new_list.size())
                        continue;

                    finish();
                    Intent intent = getParent().getIntent();
                    startActivity(intent);
                    return;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean listIsAtTop() {
        if(listView.getChildCount() == 0) return true;
        return listView.getChildAt(0).getTop() == 0;
    }

    public void btnClick() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                NewsFeed message1 = new NewsFeed(1, user.getUsername_(), user.getLastName(), user.getFirstName(), new Date(),
                        message.getText().toString());

                newFeedPost(message1);

                listAdapter.notifyDataSetChanged();
                finish();
                Intent intent = getParent().getIntent();
                startActivity(intent);
            }
        });
    }

    public void btnOpen() {
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (btnOpen.getText().equals("+")) {
                    messageSection.setVisibility(View.VISIBLE);
                    btnOpen.setText("-");
                } else {
                    messageSection.setVisibility(View.GONE);
                    btnOpen.setText("+");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    public List<NewsFeed> getNewsfeed() {
        List<NewsFeed> msgs = nfCtrl.GetNewsFeedList();

        for (NewsFeed feed : msgs) {
            Log.e("NewsFeedActivity", "msg: " + feed.getMessage());
        }
        return msgs;
    }

    public void newFeedPost(NewsFeed new_feed) {
        Log.v("newFeedPost", "sending data to database newFeedPost");
        nfCtrl.SendNewsfeedPost( new_feed.getMessage());
        messageList = nfCtrl.GetNewsFeedList();
    }

}
