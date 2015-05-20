package at.tugraz.flipvloppers.flipvloppers2015;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

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
    private ImageButton btnOpen;
    private NewsFeedController nfCtrl = null;
    private View v;
    private Activity activity_;

    private ImageView imageSad, imageSmile, imageAngry, imageAnonymous,
            imageCoffee, imageTongue, imageThumb, imageDevil, imageGentleman,
            imageBlink, imageBigeyes, imageParty;

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
        btnOpen = (ImageButton) v.findViewById(R.id.buttonOpen);
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


        // Init Emojis
        imageSad = (ImageView) v.findViewById(R.id.imageSad);
        imageSmile = (ImageView) v.findViewById(R.id.imageSmile);
        imageAngry = (ImageView) v.findViewById(R.id.imageAngry);
        imageAnonymous = (ImageView) v.findViewById(R.id.imageAnonymous);
        imageCoffee = (ImageView) v.findViewById(R.id.imageCoffee);
        imageTongue = (ImageView) v.findViewById(R.id.imageTongue);
        imageThumb = (ImageView) v.findViewById(R.id.imageThumbs);
        imageDevil = (ImageView) v.findViewById(R.id.imageDevil);
        imageGentleman = (ImageView) v.findViewById(R.id.imageGentleman);
        imageBlink = (ImageView) v.findViewById(R.id.imageBlink);
        imageBigeyes = (ImageView) v.findViewById(R.id.imageBigeyes);
        imageParty = (ImageView) v.findViewById(R.id.imageParty);

        imageSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectionCursor = message.getSelectionStart();
                message.getText().insert(selectionCursor, ":sad:");

                //ImageView tmp = imageSad;
                //addImageBetweentext(tmp.getDrawable());
            }
        });


        return v;
    }

    private void addImageBetweentext(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 2, drawable.getIntrinsicHeight() / 2);

        int selectionCursor = message.getSelectionStart();
        message.getText().insert(selectionCursor, ":sad:");
        selectionCursor = message.getSelectionStart();

        SpannableStringBuilder builder = new SpannableStringBuilder(message.getText());
        builder.setSpan(new ImageSpan(drawable), selectionCursor - ":sad:".length(), selectionCursor, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        message.setText(builder);
        message.setSelection(selectionCursor);

        
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
                                if (listIsAtTop()) {
                                    if ((messageList.size() != updatedMessageList.size()) && updatedMessageList.size() > 0) {
                                        messageList.clear();
                                        messageList.addAll(updatedMessageList);
                                        listAdapter.notifyDataSetChanged();
                                    }


                                    return;
                                }
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    boolean btnOpen_open = false;
    public void btnOpen()
    {
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (!btnOpen_open)
                {
                    messageSection.setVisibility(View.VISIBLE);
                    btnOpen.setImageResource(R.mipmap.ic_action_overflow);
                    btnOpen_open = true;
                } else {
                    messageSection.setVisibility(View.GONE);
                    btnOpen.setImageResource(R.mipmap.ic_action_mail_add);
                    btnOpen_open = false;
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

       /* for (NewsFeed feed : msgs) {
            Log.e("NewsFeedActivity", "msg: " + feed.getMessage());
        }*/
        return msgs;
    }

    public void newFeedPost(NewsFeed new_feed) {
        Log.v("newFeedPost", "sending data to database newFeedPost");
        nfCtrl.SendNewsfeedPost( new_feed.getMessage());
        messageList = nfCtrl.GetNewsFeedList();
    }

}
