package at.tugraz.flipvloppers.flipvloppers2015.configuration;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.MultiAutoCompleteTextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import at.tugraz.flipvloppers.flipvloppers2015.controller.ControllerFactory;
import at.tugraz.flipvloppers.flipvloppers2015.model.items.User;

/**
 * Created by Admin on 06.05.2015.
 */

public class Configuration implements Serializable {


    private String filename = "config.bin";

   /* public void save(Activity  activity)
    {
        try
        {
            // Creates a trace file in the primary external storage space of the
            // current application.
            // If the file does not exists, it is created.
            File traceFile = new File(((Context)activity).getExternalFilesDir(null), filename);
            if (!traceFile.exists())
                traceFile.createNewFile();

            FileOutputStream fileOut = new FileOutputStream(traceFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + filename);

        }
        catch(Exception ex)
        {
            Log.e("Configuration", "Failed to Serialize " + filename);
            Log.e("Configuration",ex.getMessage());
        }

    }*/

}
