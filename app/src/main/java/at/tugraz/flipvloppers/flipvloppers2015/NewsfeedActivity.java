package at.tugraz.flipvloppers.flipvloppers2015;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.adapter.FeedListAdapter;
import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.NewsFeedController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;

public class NewsfeedActivity extends Activity{

    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<Message> messageList;
    private NewsFeedController newsfeedController;

    private EditText message;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        newsfeedController = ControllerFactory.GetNewsFeedControllerInstance();

        listView = (ListView) findViewById(R.id.listPosts);
        message = (EditText) findViewById(R.id.editTextMessage);
        btnSend = (Button) findViewById(R.id.buttonSend);

        btnClick();

        messageList = new ArrayList<Message>();
        messageList = newsfeedController.getList(0,0);

        listAdapter = new FeedListAdapter(this, messageList);
        listView.setAdapter(listAdapter);

        listAdapter.notifyDataSetChanged();
    }

    public void btnClick() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Message message1 = new Message();
                message1.setId_user_sender(1);
                message1.setMessage_type_id(1);
                message1.setCreate_time(new Date());
                message1.setText(message.getText().toString());
                messageList.add(message1);
                listAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

}
