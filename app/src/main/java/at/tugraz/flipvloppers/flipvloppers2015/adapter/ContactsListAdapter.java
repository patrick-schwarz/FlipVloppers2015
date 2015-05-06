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
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * Created by Admin on 06.05.2015.
 */
public class ContactsListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private List<User> users;

    public ContactsListAdapter(Activity activity, List<User> users) {
        this.activity = activity;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
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
            view = inflater.inflate(R.layout.contact_item, null);

        TextView contact_lastname_firstname = (TextView) view.findViewById(R.id.contact_lastname_firstname);

        User user = users.get(i);

        //TODO get sender name
        contact_lastname_firstname.setText(user.getLastName() + " " + user.getFirstName());
        contact_lastname_firstname.setVisibility(View.VISIBLE);


        return view;
    }
}
