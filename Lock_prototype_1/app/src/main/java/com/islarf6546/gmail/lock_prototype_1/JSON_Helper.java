package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.os.ParcelFileDescriptor.MODE_WRITE_ONLY;

/*
* Author: Darren Bellew
*
* This class provides interaction with a json file (filename given via constructior).
*
* Reading JSON: http://stackoverflow.com/questions/13814503/reading-a-json-file-in-android
* Writing JSON:
*   FileOutputStream: https://developer.android.com/reference/java/io/FileOutputStream.html
*
*/

public class JSON_Helper {



    String filename;

    public JSON_Helper(String filename) { this.filename = filename; }



    public JSONObject loadJSON(Context ctx) throws JSONException {
        /*
        * This method loads the json file, and returns the content in a JSONObject
        */

        JSONObject json = null;

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

        /*
        * This method writes the JSONObject sent to it to the json file (filename).
        * It returns a boolean on success/failure.
        */

        try {
            FileOutputStream fos = ctx.openFileOutput(filename, MODE_WRITE_ONLY);

            String str = json_data.toString();
            fos.write(str.getBytes());
            fos.close();
            return true;

        }
        catch(FileNotFoundException fnf)  {
            fnf.printStackTrace();
        }
        catch(IOException ioe)  {
            ioe.printStackTrace();
        }

        return false;
    }
}
