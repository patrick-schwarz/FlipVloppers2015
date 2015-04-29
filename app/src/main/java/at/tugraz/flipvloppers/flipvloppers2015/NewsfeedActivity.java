package at.tugraz.flipvloppers.flipvloppers2015;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.adapter.FeedListAdapter;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;

public class NewsfeedActivity extends Activity{

    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        listView = (ListView) findViewById(R.id.list);

        messageList = new ArrayList<Message>();

        listAdapter = new FeedListAdapter(this, messageList);
        listView.setAdapter(listAdapter);

        //Generate test data

        Message new_msg = new Message();
        new_msg.setId_user_sender(1);
        new_msg.setMessage_type_id(1);
        new_msg.setCreate_time(new Date(10000));
        new_msg.setText("erster test");

        messageList.add(new_msg);

        listAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

}
