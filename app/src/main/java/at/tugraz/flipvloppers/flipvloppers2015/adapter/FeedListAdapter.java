package at.tugraz.flipvloppers.flipvloppers2015.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.R;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.Message;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;

public class FeedListAdapter extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<NewsFeed> messages;

    public FeedListAdapter(Activity activity, List<NewsFeed> messages) {
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

        NewsFeed message = messages.get(i);

        //TODO get sender name
        name.setText(message.getLast() + " " + message.getFirst());
        timestamp.setText(message.getDate().toString());
        feedMsg.setText(message.getMessage());
        feedMsg.setVisibility(View.VISIBLE);


        return view;
    }
}
