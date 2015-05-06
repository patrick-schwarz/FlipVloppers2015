package at.tugraz.flipvloppers.flipvloppers2015;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.Date;
import java.util.List;

import at.tugraz.flipvloppers.flipvloppers2015.adapter.ContactsListAdapter;
import at.tugraz.flipvloppers.flipvloppers2015.adapter.FeedListAdapter;
import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.NewsFeedController;
import at.tugraz.flipvloppers.flipvloppers2015.controller.UserController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.NewsFeed;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;


public class ContactsActivity extends ActionBarActivity {


    private ListView listContacts;
    private ContactsListAdapter listAdapter;
    private User user;
    private SearchView searchContacts;

    private UserController uCtrl = null;
    private ContactsActivity contactsActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        contactsActivity = this;
        user = ControllerFactory.getCurrentUser();
        uCtrl = ControllerFactory.GetUserControllerInstance();
        listContacts = (ListView) findViewById(R.id.listContacts);
        searchContacts = (SearchView) findViewById(R.id.searchViewContacts);

        btnSearch();

        List<User> users =  uCtrl.GetUsers();
        Log.e("Contacts", "Got " + users.size() + " Users");
        listAdapter = new ContactsListAdapter( this, uCtrl.GetUsers());
        listContacts.setAdapter(listAdapter);

        //Generate test data

        //NewsFeed new_msg = new NewsFeed(0, "1", "username", "Mr", new Date(1000), "erster test");

        //messageList.add(new_msg);

        listAdapter.notifyDataSetChanged();
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

        searchContacts.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                listAdapter = new ContactsListAdapter(contactsActivity, uCtrl.GetUsers(query));
                listContacts.setAdapter(listAdapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
}
