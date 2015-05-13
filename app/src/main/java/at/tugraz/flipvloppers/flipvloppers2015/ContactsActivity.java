package at.tugraz.flipvloppers.flipvloppers2015;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.activity_contacts, container, false);
        //setContentView(R.layout.activity_contacts);
        contactsActivity = this;
        user = ControllerFactory.getCurrentUser();
        uCtrl = ControllerFactory.GetUserControllerInstance();
        listContacts = (ListView) v.findViewById(R.id.listContacts);
        searchContacts = (SearchView) v.findViewById(R.id.searchViewContacts);

        btnSearch();

        List<User> users =  uCtrl.getUsers();
        Log.e("Contacts", "Got " + users.size() + " Users");
        listAdapter = new ContactsListAdapter( this, uCtrl.getUsers());
        listContacts.setAdapter(listAdapter);

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
        searchContacts.setOnCloseListener(new SearchView.OnCloseListener(){

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
                if(newText.replace(" ","").equals(""))
                {
                    listAdapter = new ContactsListAdapter(contactsActivity, uCtrl.getUsers());
                    listContacts.setAdapter(listAdapter);
                }
                else
                {
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
