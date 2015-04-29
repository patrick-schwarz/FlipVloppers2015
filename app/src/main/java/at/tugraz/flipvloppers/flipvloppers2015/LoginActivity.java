package at.tugraz.flipvloppers.flipvloppers2015;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.LoginController;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
<<<<<<< HEAD
    private LoginController ctrlLogin;
=======
    private Button btnlogin;
>>>>>>> 05a08938f98bc88e9aa8849ea5ea39b71ac766e5

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ctrlLogin = ControllerFactory.GetLoginControllerInstance();

        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);
        btnlogin = (Button) findViewById(R.id.buttonLogin);


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
                break;
            case R.id.editTextPassword:
                password.setText("");
                break;
<<<<<<< HEAD
            case R.id.buttonLogin:
                if(ctrlLogin.CheckLogin(username.getText().toString(),password.getText().toString())) {
                    //TODO Link to next page
                }
=======

            case R.id.buttonLogin:
                Intent nextScreen = new Intent(getApplicationContext(), NewsfeedActivity.class);

                //Sending data to another Activity
                /*
                nextScreen.putExtra("name", inputName.getText().toString());
                nextScreen.putExtra("email", inputEmail.getText().toString());

                Log.e("n", inputName.getText()+"."+ inputEmail.getText());*/

                startActivity(nextScreen);
>>>>>>> 05a08938f98bc88e9aa8849ea5ea39b71ac766e5
                break;
        }
    }
}
