package at.tugraz.flipvloppers.flipvloppers2015;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import at.tugraz.flipvloppers.flipvloppers2015.controller.LoginController;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private TextView error_msg;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);
        btnlogin = (Button) findViewById(R.id.buttonLogin);

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

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.editTextUsername:
                username.setText("");
                error_msg.setVisibility(View.INVISIBLE);
                break;
            case R.id.editTextPassword:
                password.setText("");
                error_msg.setVisibility(View.INVISIBLE);
                break;

            case R.id.buttonLogin:
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

    private void login()
    {
        try {
            Log.v("USER", "calling execute with: " + username.getText().toString() + " " + password.getText().toString());
            User user = new LoginController().execute(username.getText().toString(), password.getText().toString()).get();
            if (user == null)
            {
                error_msg.setVisibility(View.VISIBLE);
                return;
            }
            Intent nextScreen = new Intent(getApplicationContext(), TabContainerActivity.class);
            nextScreen.putExtra("user", new Gson().toJson(user));


            //Sending data to another Activity
                /*
                nextScreen.putExtra("name", inputName.getText().toString());
                nextScreen.putExtra("email", inputEmail.getText().toString());

                Log.e("n", inputName.getText()+"."+ inputEmail.getText());*/

            startActivity(nextScreen);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
