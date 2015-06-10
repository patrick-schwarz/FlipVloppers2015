package at.tugraz.flipvloppers.flipvloppers2015;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.gson.Gson;

import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.adapter.ContactsListAdapter;
import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.UserController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;


public class ContactsActivity extends Fragment {


    private ListView listContacts;
    private ContactsListAdapter listAdapter;
    private User user;
    private SearchView searchContacts;

    private UserController uCtrl = null;
    private ContactsActivity contactsActivity;
    private View v;
    private Activity activity_;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_contacts, container, false);
        activity_ = getActivity();
        //setContentView(R.layout.activity_contacts);
        contactsActivity = this;
        user = ControllerFactory.getCurrentUser();
        uCtrl = ControllerFactory.GetUserControllerInstance();
        listContacts = (ListView) v.findViewById(R.id.listContacts);
        searchContacts = (SearchView) v.findViewById(R.id.searchViewContacts);
        searchContacts.setFocusable(true);
        searchContacts.setIconified(false);
        searchContacts.requestFocusFromTouch();

        btnSearch();

        List<User> users = uCtrl.getUsers();
        Log.e("Contacts", "Got " + users.size() + " Users");
        listAdapter = new ContactsListAdapter(this, uCtrl.getUsers());
        listContacts.setAdapter(listAdapter);


        listContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                User from = (User) listAdapter.getItem(position);
                Intent nextScreen = new Intent(getActivity().getApplicationContext(), MessageActivity.class);
                nextScreen.putExtra("from", from);
                startActivity(nextScreen);
            }
        });

        //Generate test data

        //NewsFeed new_msg = new NewsFeed(0, "1", "username", "Mr", new Date(1000), "erster test");

        //messageList.add(new_msg);

        listAdapter.notifyDataSetChanged();

        return v;
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


    public void btnSearch() {
        searchContacts.setOnCloseListener(new SearchView.OnCloseListener() {

            @Override
            public boolean onClose() {

                listAdapter = new ContactsListAdapter(contactsActivity, uCtrl.getUsers());
                listContacts.setAdapter(listAdapter);
                return false;
            }

        });
        searchContacts.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.replace(" ", "").equals("")) {
                    listAdapter = new ContactsListAdapter(contactsActivity, uCtrl.getUsers());
                    listContacts.setAdapter(listAdapter);
                } else {
                    listAdapter = new ContactsListAdapter(contactsActivity, uCtrl.getUsers(newText));
                    listContacts.setAdapter(listAdapter);
                }
                return false;
            }

        });
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }*/
}
