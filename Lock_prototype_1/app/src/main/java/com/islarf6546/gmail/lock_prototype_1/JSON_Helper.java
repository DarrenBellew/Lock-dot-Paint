package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.os.ParcelFileDescriptor.MODE_WRITE_ONLY;

/**
 * Created by Islarf on 23/12/2016.
 */

public class JSON_Helper {

    /*
        JSONHelper class to interact with the JSON file.
     */



    String filename;

    public JSON_Helper(String filename) { this.filename = filename; }



    public JSONObject loadJSON(Context ctx) throws JSONException {


        JSONObject json = null;

        //having to read from LOCAL storage (Not using external storage incase the user does not have an SD card)
        try  {
            FileInputStream fin = ctx.openFileInput(filename);

            int c;
            String temp = "";
            while((c = fin.read()) != -1)  {
                temp = temp+Character.toString((char)c);
            }
            fin.close();

            json = new JSONObject(temp);
        }
        catch(FileNotFoundException fnf)  {
            fnf.printStackTrace();
        }
        catch(IOException ioe)  {
            ioe.printStackTrace();
        }

        return json;
    }

    public boolean writeJSON(JSONObject json_data, Context ctx)  {

        try {
            FileOutputStream fos = ctx.openFileOutput(filename, MODE_WRITE_ONLY);

            String str = json_data.toString();
            fos.write(str.getBytes());
            fos.close();

        }
        catch(FileNotFoundException fnf)  {
            fnf.printStackTrace();
        }
        catch(IOException ioe)  {
            ioe.printStackTrace();
        }

        System.out.println("PATH: " + ctx.getFilesDir().getAbsolutePath());

        return false;
    }
}
