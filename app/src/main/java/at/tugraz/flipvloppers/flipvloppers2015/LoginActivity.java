package at.tugraz.flipvloppers.flipvloppers2015;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.LoginController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private TextView error_msg;
    private Button btnlogin;
    private CheckBox stayloggedin;
    private boolean ischecked;
    private LoginController loginCtrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginCtrl = ControllerFactory.GetLoginControllerInstance();
        retrieveAccount();
        setContentView(R.layout.activity_login);


        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);
        btnlogin = (Button) findViewById(R.id.buttonLogin);



        stayloggedin = (CheckBox) findViewById(R.id.checkBoxStayLoggedIn);


        error_msg = (TextView) findViewById(R.id.textViewError);

        username.setOnClickListener(this);
        password.setOnClickListener(this);
        btnlogin.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    private boolean del_emtpytext_password = true;
    private boolean del_emtpytext_username = true;
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.editTextUsername:
                String username_s = username.getText().toString();
                if(del_emtpytext_username) {
                    username.setText("");
                    del_emtpytext_username = false;
                }
                error_msg.setVisibility(View.INVISIBLE);
                break;
            case R.id.editTextPassword:
                if(del_emtpytext_password) {
                    password.setText("");
                    del_emtpytext_password = false;
                }
                error_msg.setVisibility(View.INVISIBLE);
                break;

            case R.id.buttonLogin:

                ischecked = stayloggedin.isChecked();

                if (ischecked) {
                    saveAccount();

                }

                login();

                break;

            //Sending data to another Activity
                /*
                nextScreen.putExtra("name", inputName.getText().toString());
                nextScreen.putExtra("email", inputEmail.getText().toString());

                Log.e("n", inputName.getText()+"."+ inputEmail.getText());*/

            //startActivity(nextScreen);
            //  break;
        }
    }



    private void login() {
        Log.v("USER", "calling execute with: " + username.getText().toString() + " " + password.getText().toString());
        if (!loginCtrl.Login(username.getText().toString(), password.getText().toString())) {
            error_msg.setVisibility(View.VISIBLE);
            return;
        }

        Intent nextScreen = new Intent(getApplicationContext(), TabContainerActivity.class);
        nextScreen.putExtra("user", new Gson().toJson(ControllerFactory.getCurrentUser()));


        //Sending data to another Activity
                /*
                nextScreen.putExtra("name", inputName.getText().toString());
                nextScreen.putExtra("email", inputEmail.getText().toString());

                Log.e("n", inputName.getText()+"."+ inputEmail.getText());*/

        startActivity(nextScreen);
    }

    public boolean saveAccount() {


        String pass = password.getText().toString();

        ArrayList<Integer> encrypted = new ArrayList<Integer>();
        for (int i = 0; i < pass.length(); i++)
        {
            char text = pass.charAt(i);
            int input = (int) text;
            encrypted.add(encrypt(input));
        }

        // store preference

        setIntegerArrayPref("loginPrefs", "pass", encrypted);


        SharedPreferences prefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", username.getText().toString());
        editor.putBoolean("checked", ischecked);
        editor.commit();


        if (pass != null)
           return true;
        else
           return false;
    }

    public boolean retrieveAccount() {

        // retrieve preferences
        // getting Username
        SharedPreferences read = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        String user = read.getString("username", null);
        // getting Password
        ArrayList<Integer> encrypted_new = new ArrayList<Integer>();
        encrypted_new = getStringArrayPref("loginPrefs", "pass");
        // getting Checkbox Status
        Boolean checked = read.getBoolean("checked", true); // getting Boolean

        if ( user != null && encrypted_new.size() != 0 && checked)
        {
            ArrayList<Character> decrypted = new ArrayList<Character>();
            for (int i = 0; i < encrypted_new.size(); i++)
            {
                char decrypted_char = (char) decrypt(encrypted_new.get(i));
                decrypted.add(decrypted_char);
            }
            StringBuffer encrypted_out = new StringBuffer();
            for (int i = 0; i < encrypted_new.size(); i++)
            {
                encrypted_out.append(decrypted.get(i));
            }

            String pass_fin = encrypted_out.toString();


            Log.v("USER", "calling execute with: " + user + " " + pass_fin);
            if (!loginCtrl.Login(user, pass_fin)) {
                error_msg.setVisibility(View.VISIBLE);
            }

            Intent nextScreen = new Intent(getApplicationContext(), TabContainerActivity.class);
            nextScreen.putExtra("user", new Gson().toJson(ControllerFactory.getCurrentUser()));


            //Sending data to another Activity
                /*
                nextScreen.putExtra("name", inputName.getText().toString());
                nextScreen.putExtra("email", inputEmail.getText().toString());

                Log.e("n", inputName.getText()+"."+ inputEmail.getText());*/

            startActivity(nextScreen);

        }

       return true;

    }

    public int encrypt(int input) {
        int e = 11;
        int n = 270703;
        int ret = ((BigInteger.valueOf(input).pow(e).mod(BigInteger.valueOf(n)))).intValue();
        return ret;
    }

    public int decrypt(int input) {
        int d = 98051;
        int n = 270703;
        int ret = ((BigInteger.valueOf(input).pow(d).mod(BigInteger.valueOf(n)))).intValue();
        return ret;
    }

    public void setIntegerArrayPref(String context, String key, ArrayList<Integer> values) {
        SharedPreferences prefs = getSharedPreferences(context, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.commit();
    }

    public ArrayList<Integer> getStringArrayPref(String context, String key) {
        SharedPreferences prefs = getSharedPreferences(context, MODE_PRIVATE);
        String json = prefs.getString(key, null);
        ArrayList<Integer> pass = new ArrayList<Integer>();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    int url_int = Integer.parseInt(url);
                    pass.add(url_int);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return pass;
    }
}
