package at.tugraz.flipvloppers.flipvloppers2015.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.R;
import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

public class MessageAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private List<Message> messages;

    public MessageAdapter(Activity activity, List<Message> messages) {
        this.activity = activity;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    public void addItem(Message message){ messages.add(message);}

    public List<Message> getData(){return messages;}

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Message message = messages.get(i);

        User from = null;

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (message.from_id == ControllerFactory.getCurrentUser().getId_()) {
            if (view == null)
                view = inflater.inflate(R.layout.message_right_post, null);

            from = ControllerFactory.getCurrentUser();
        } else {
            if (view == null)
                view = inflater.inflate(R.layout.message_left_post, null);

            from = ControllerFactory.GetUserControllerInstance().getUser(message.from_id);
        }

        TextView name = (TextView) view.findViewById(R.id.feed_name);
        TextView timestamp = (TextView) view.findViewById(R.id.feed_time);
        TextView feedMsg = (TextView) view.findViewById(R.id.feed_message);


        //TODO get sender name
        name.setText(from.getLastName() + " " + from.getFirstName());
        feedMsg.setText(message.getMessage());
        feedMsg.setVisibility(View.VISIBLE);
        Date date = message.getDate();
        timestamp.setText(sdf.format(date));

        return view;
    }
}