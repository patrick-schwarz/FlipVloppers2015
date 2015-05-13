package at.tugraz.flipvloppers.flipvloppers2015;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.adapter.FeedListAdapter;
import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.NewsFeedController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

public class NewsfeedActivity extends Fragment{

    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<NewsFeed> messageList;
    private User user;
    private Intent intent;
    private List<NewsFeed> updatedMessageList = new LinkedList<>();

    private LinearLayout messageSection;
    private EditText message;
    private Button btnSend;
    private Button btnOpen;
    private NewsFeedController nfCtrl = null;
    private View v;
    private Activity activity_;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_newsfeed, container, false);
        activity_ = getActivity();
        user = ControllerFactory.getCurrentUser();
        nfCtrl = ControllerFactory.GetNewsFeedControllerInstance();
        listView = (ListView) v.findViewById(R.id.listPosts);
        btnSend = (Button) v.findViewById(R.id.buttonSend);
        message = (EditText) v.findViewById(R.id.editTextMessage);
        btnOpen = (Button) v.findViewById(R.id.buttonOpen);
        messageSection = (LinearLayout) v.findViewById(R.id.llMessageSection);
        if (messageSection!=null)
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


        refreshView();

        listAdapter.notifyDataSetChanged();
        return v;
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Generate test data
     
    }*/
        //NewsFeed new_msg = new NewsFeed(0, "1", "username", "Mr", new Date(1000), "erster test");

    private void refreshView()
    {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(5000);

                        activity_.runOnUiThread(new Runnable() {
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
                updatedMessageList = getNewsfeed();
                System.out.println("UPDATE THIS VIEW? 2");

                refreshView();
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
                //messageList.add(message1);
                newFeedPost(message1);

                listAdapter.notifyDataSetChanged();
                getActivity().finish();
                intent = getActivity().getIntent();
                startActivity(intent);
            }
        });
    }

    public void btnOpen()
    {
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (btnOpen.getText().equals("+"))
                {
                    messageSection.setVisibility(View.VISIBLE);
                    btnOpen.setText("-");
                } else {
                    messageSection.setVisibility(View.GONE);
                    btnOpen.setText("+");
                }
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_login, menu);
//        return true;
//    }

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
