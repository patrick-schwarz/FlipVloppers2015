package at.tugraz.flipvloppers.flipvloppers2015;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.controller.LoginController;


public class SettingsAcitivity extends Fragment implements View.OnClickListener {

    private LoginController loginCtrl = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_settings_acitivity, container, false);

        Button btnlogout = (Button) v.findViewById(R.id.btnLogout);
        btnlogout.setOnClickListener(this);
        return v;
    }

    public void onClick (View v) {

        switch (v.getId()) {

            case R.id.btnLogout:

                System.out.println("Vorher is suppa");


                SharedPreferences prefs = this.getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("checked", false);
                editor.commit();

                this.getActivity().finish();

                System.out.println("nacher is suppa");

        }
    }

   }

