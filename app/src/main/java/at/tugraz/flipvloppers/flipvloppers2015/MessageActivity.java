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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.adapter.FeedListAdapter;
import at.tugraz.flipvloppers.flipvloppers2015.adapter.MessageAdapter;
import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.EmojiController;
import at.tugraz.flipvloppers.flipvloppers2015.controller.MessageController;
import at.tugraz.flipvloppers.flipvloppers2015.controller.NewsFeedController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;


public class MessageActivity extends ActionBarActivity {
    private ListView listView;
    private MessageAdapter listAdapter = null;
    private User user;
    private User from;

    private EditText message;
    private Button btnSend;

    private ImageView imageSad, imageSmile, imageAngry, imageAnonymous,
            imageCoffee, imageTongue, imageThumb, imageDevil, imageGentleman,
            imageBlink, imageBigeyes, imageParty;

    private MessageController messageCtrl = null;
    private EmojiController emojiCtrl = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                from= null;
            } else {
                from= (User) extras.getSerializable("from");
            }
        } else {
            from= (User) savedInstanceState.getSerializable("from");
        }

        user = ControllerFactory.getCurrentUser();
        emojiCtrl = ControllerFactory.GetEmojiControllerInstance();
        messageCtrl = ControllerFactory.GetMessageControllerInstance();
        listView = (ListView) findViewById(R.id.listPosts);
        btnSend = (Button) findViewById(R.id.buttonSend);
        message = (EditText) findViewById(R.id.editTextMessage);

        btnClick();

        refreshView();

        Thread thread = new Thread(){
            public void run(){
                refreshNews();
            }
        };
        thread.start();

        // Init Emojis
        imageSad = (ImageView) findViewById(R.id.imageSad);
        imageSmile = (ImageView) findViewById(R.id.imageSmile);
        imageAngry = (ImageView) findViewById(R.id.imageAngry);
        imageAnonymous = (ImageView) findViewById(R.id.imageAnonymous);
        imageCoffee = (ImageView) findViewById(R.id.imageCoffee);
        imageTongue = (ImageView) findViewById(R.id.imageTongue);
        imageThumb = (ImageView) findViewById(R.id.imageThumbs);
        imageDevil = (ImageView) findViewById(R.id.imageDevil);
        imageGentleman = (ImageView) findViewById(R.id.imageGentleman);
        imageBlink = (ImageView) findViewById(R.id.imageBlink);
        imageBigeyes = (ImageView) findViewById(R.id.imageBigeyes);
        imageParty = (ImageView) findViewById(R.id.imageParty);

        imageSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionCursor = message.getSelectionStart();
                message.getText().insert(selectionCursor, ":sad:");
            }
        });

        imageSmile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionCursor = message.getSelectionStart();
                message.getText().insert(selectionCursor, ":smile:");
            }
        });

        imageAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionCursor = message.getSelectionStart();
                message.getText().insert(selectionCursor, ":angry:");
            }
        });

        imageAnonymous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionCursor = message.getSelectionStart();
                message.getText().insert(selectionCursor, ":anonymous:");
            }
        });

        imageCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionCursor = message.getSelectionStart();
                message.getText().insert(selectionCursor, ":coffee:");
            }
        });

        imageTongue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionCursor = message.getSelectionStart();
                message.getText().insert(selectionCursor, ":tongue:");
            }
        });

        imageThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionCursor = message.getSelectionStart();
                message.getText().insert(selectionCursor, ":thumb:");
            }
        });

        imageDevil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionCursor = message.getSelectionStart();
                message.getText().insert(selectionCursor, ":devil:");
            }
        });

        imageGentleman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionCursor = message.getSelectionStart();
                message.getText().insert(selectionCursor, ":gentleman:");
            }
        });

        imageBlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionCursor = message.getSelectionStart();
                message.getText().insert(selectionCursor, ":blink:");
            }
        });

        imageBigeyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionCursor = message.getSelectionStart();
                message.getText().insert(selectionCursor, ":bigeyes:");
            }
        });

        imageParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionCursor = message.getSelectionStart();
                message.getText().insert(selectionCursor, ":party:");
            }
        });
    }

    public void btnClick() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Message m = new Message(0, user.getId_(), from.getId_(),
                        emojiCtrl.parseEmoji(message.getText().toString()));
                message.setText("");

                messageCtrl.SendMessageToUser(m.getMessage(), from.getId_());

                listAdapter.addItem(m);
                listAdapter.notifyDataSetChanged();

                listView.setSelection(listAdapter.getCount() - 1);

                refreshView();
            }
        });
    }



    private void refreshView()
    {
        List<Message> updatedMessageList = messageCtrl.GetMessagesFromUser(from.getUsername_());

        if(listAdapter == null)
        {
            listAdapter = new MessageAdapter(this,updatedMessageList);
            listView.setAdapter(listAdapter);
            listView.setSelection(listAdapter.getCount() - 1);
        }
        else if (listAdapter.getData().size() < updatedMessageList.size()) {
            listAdapter.notifyDataSetChanged();

            for(int index = listAdapter.getData().size();index < updatedMessageList.size();index++) {
                listAdapter.addItem(updatedMessageList.get(index - 1));
            }

            listAdapter.notifyDataSetChanged();
        }
    }

    public void refreshNews()
    {
        try {
            while(true) {
                Thread.sleep(5000);
                refreshView();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

}
