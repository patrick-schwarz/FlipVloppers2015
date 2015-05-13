package at.tugraz.flipvloppers.flipvloppers2015;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class TabContainerActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_container);

        Intent intent;
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {

            @Override
            public void onViewDetachedFromWindow(View v) {}

            @Override
            public void onViewAttachedToWindow(View v) {
                mTabHost.getViewTreeObserver().removeOnTouchModeChangeListener(mTabHost);
            }
        });

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        View newsView = LayoutInflater.from(TabContainerActivity.this).inflate(R.layout.tab_newsfeed,null);
        mTabHost.addTab(
                mTabHost.newTabSpec("newsfeed").setIndicator(newsView), NewsfeedActivity.class, null
        );
        View contactsView = LayoutInflater.from(TabContainerActivity.this).inflate(R.layout.tab_contacts,null);
        mTabHost.addTab(
                mTabHost.newTabSpec("contacts").setIndicator(contactsView),
                ContactsActivity.class, null
        );
        View settingsView = LayoutInflater.from(TabContainerActivity.this).inflate(R.layout.tab_settings,null);
        mTabHost.addTab(
                mTabHost.newTabSpec("settings").setIndicator(settingsView),
                SettingsAcitivity.class, null
        );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_container, menu);
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
