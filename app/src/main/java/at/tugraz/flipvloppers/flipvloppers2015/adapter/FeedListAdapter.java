package at.tugraz.flipvloppers.flipvloppers2015.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.R;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;

public class FeedListAdapter extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<Message> messages;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public FeedListAdapter(Activity activity, List<Message> messages) {
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

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.feed_post, null);

        TextView name = (TextView) view.findViewById(R.id.feed_name);
        TextView timestamp = (TextView) view.findViewById(R.id.feed_time);
        TextView feedMsg = (TextView) view.findViewById(R.id.feed_message);

        Message message = messages.get(i);

        //TODO get sender name
        name.setText(message.getId_user_sender() + " username");
        Date date = message.getCreate_time();
        timestamp.setText(sdf.format(date));

        if (message.getMessage_type_id() == 1) //TODO Newsfeed message type check
        {
           feedMsg.setText(message.getText());
           feedMsg.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
