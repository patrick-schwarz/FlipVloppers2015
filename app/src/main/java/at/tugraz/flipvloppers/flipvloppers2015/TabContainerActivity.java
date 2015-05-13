package at.tugraz.flipvloppers.flipvloppers2015;

import android.app.TabActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.TabHost;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;


public class TabContainerActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_container);

        Intent intent;
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        View newsView = LayoutInflater.from(TabContainerActivity.this).inflate(R.layout.tab_newsfeed,null);
        mTabHost.addTab(
                mTabHost.newTabSpec("newsfeed").setIndicator(newsView), NewsfeedActivity.class, null
        );
        View settingsView = LayoutInflater.from(TabContainerActivity.this).inflate(R.layout.tab_settings,null);
        mTabHost.addTab(
                mTabHost.newTabSpec("settings").setIndicator(settingsView),
                SettingsAcitivity.class, null
        );


//        TabHost tabHost = getTabHost();
//        TabHost.TabSpec spec;
//        Intent intent;
//        Resources res = getResources();
//        Drawable drawable = res.getDrawable(R.mipmap.ic_action_mail);
//
//        intent = new Intent().setClass(this, NewsfeedActivity.class);
//        String userstring = getIntent().getExtras().getString("user");
//        intent.putExtra("user", userstring);
//        spec = tabHost.newTabSpec("Newsfeed").setIndicator("News",drawable)
//                .setContent(intent);
//        tabHost.addTab(spec);
//
//        drawable = res.getDrawable(R.mipmap.ic_action_user);
//        intent = new Intent().setClass(this, SettingsAcitivity.class);
//        spec = tabHost.newTabSpec("Contacts").setIndicator("Contacts",drawable)
//                .setContent(intent);
//        tabHost.addTab(spec);
//
//        drawable = res.getDrawable(R.mipmap.ic_action_mail_add);
//        intent = new Intent().setClass(this, SettingsAcitivity.class);
//        spec = tabHost.newTabSpec("PrivateMessage").setIndicator("Chat",drawable)
//                .setContent(intent);
//        tabHost.addTab(spec);
//
//        drawable = res.getDrawable(R.mipmap.ic_action_star);
//        intent = new Intent().setClass(this, SettingsAcitivity.class);
//        spec = tabHost.newTabSpec("Settings").setIndicator("Settings",drawable)
//                .setContent(intent);
//        tabHost.addTab(spec);

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
