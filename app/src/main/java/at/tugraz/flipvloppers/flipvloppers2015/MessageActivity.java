package at.tugraz.flipvloppers.flipvloppers2015;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.adapter.FeedListAdapter;
import at.tugraz.flipvloppers.flipvloppers2015.adapter.MessageAdapter;
import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.MessageController;
import at.tugraz.flipvloppers.flipvloppers2015.controller.NewsFeedController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;


public class MessageActivity extends ActionBarActivity {
    private ListView listView;
    private MessageAdapter listAdapter;
    private List<Message> messageList;
    private List<Message> updatedMessageList = new LinkedList<Message>();
    private User user;
    private User from;

    private LinearLayout messageSection;
    private EditText message;
    private Button btnSend;


    private MessageController messageCtrl = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                from= null;
            } else {
                from= (User) new Gson().fromJson(extras.getString("from"),User.class);
            }
        } else {
            from= (User) savedInstanceState.getSerializable("from");
        }

        user = ControllerFactory.getCurrentUser();
        messageCtrl = ControllerFactory.GetMessageControllerInstance();
        listView = (ListView) findViewById(R.id.listPosts);
        btnSend = (Button) findViewById(R.id.buttonSend);
        message = (EditText) findViewById(R.id.editTextMessage);
        messageSection = (LinearLayout) findViewById(R.id.llMessageSection);

        messageList = getMessages();
        btnClick();

        listAdapter = new MessageAdapter(this, messageList);
        listView.setAdapter(listAdapter);
        Thread thread = new Thread(){
            public void run(){
                refreshNews();
            }
        };
        thread.start();


        refreshView();

        listAdapter.notifyDataSetChanged();
    }

    public void btnClick() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                NewsFeed message1 = new NewsFeed(1, user.getUsername_(), user.getLastName(), user.getFirstName(), new Date(),
                        message.getText().toString());
                //messageList.add(message1);
                newMessagePost(message1);

                listAdapter.notifyDataSetChanged();
            }
        });
    }

    public void refreshNews()
    {
        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    try {
                        while(true) {
                            Thread.sleep(5000);
                            if (listIsAtTop()) {
                                List<NewsFeed> new_list= getNewsfeed();
                                updatedMessageList.clear();
                                updatedMessageList.addAll(new_list);
                                refreshView();
                                return;
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        });*/

        try {
            while(true) {
                Thread.sleep(5000);

                System.out.println("UPDATE THIS VIEW? 1");
                updatedMessageList = getMessages();
                System.out.println("UPDATE THIS VIEW? 2");

                refreshView();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Message> getMessages() {
        List<Message> msgs = messageCtrl.GetMessagesFromUser(from.getUsername_());

        for (Message feed : msgs) {
            Log.e("MessageActivity", "msg: " + feed.getMessage());
        }
        return msgs;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newMessagePost(NewsFeed new_feed) {
        Log.v("newFeedPost", "sending data to database newFeedPost");
        messageCtrl.SendMessageToUser( new_feed.getMessage(),from.getUsername_());
        messageList = messageCtrl.GetMessagesFromUser(from.getUsername_());
    }

    private void refreshView()
    {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(5000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("HELLO!!!!!!! UPDATE 1 !!!!! THIS");
                                if (listIsAtTop()) {
                                    //List<NewsFeed> new_list= getNewsfeed();
                                    System.out.println("CHECK FOR SIZE 889911");
                                    System.out.println("Updated Message List Size is " + updatedMessageList.size());
                                    if ((messageList.size() != updatedMessageList.size()) && updatedMessageList.size() > 0) {
                                        messageList.clear();
                                        messageList.addAll(updatedMessageList);
                                        System.out.println("HELLO!!!!!!! CLEAR THIS");
                                        listAdapter.notifyDataSetChanged();
                                    }


                                    return;
                                }
                                System.out.println("HELLO!!!!!!! !!!!!!!!!!!!! UPDATE 2 THIS");
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
                //startActivity(mainActivity);
            };
        };
        thread.start();
    }

    private boolean listIsAtTop() {
        if(listView.getChildCount() == 0) return true;
        return listView.getChildAt(0).getTop() == 0;
    }

}
