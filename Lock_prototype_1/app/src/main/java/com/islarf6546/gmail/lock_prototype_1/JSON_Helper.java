package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;
import android.util.JsonWriter;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.POWER_SERVICE;
import static android.os.ParcelFileDescriptor.MODE_READ_ONLY;
import static android.os.ParcelFileDescriptor.MODE_WORLD_READABLE;
import static android.os.ParcelFileDescriptor.MODE_WRITE_ONLY;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Islarf on 23/12/2016.
 */

public class JSON_Helper {


    Context ctx;
    String filename;


    public JSON_Helper(String filename, Context ctx) {
        this.ctx = ctx;
        this.filename = filename;
    }



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
            AndroidHelper.makeToast(ctx, "File Not Found Exception", false);
            fnf.printStackTrace();
        }
        catch(IOException ioe)  {
            AndroidHelper.makeToast(ctx, "IOException", false);
            ioe.printStackTrace();
        }

        return json;

        /*
        String json="";
        try  {
            InputStream is = ctx.openFileInput(filename);

            if(is!=null)  {
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while((receiveString = bufferedReader.readLine()) != null)  {
                    stringBuilder.append(receiveString);
                }
                is.close();
                json = stringBuilder.toString();

            }
        }
        catch(FileNotFoundException fnf)  {
            fnf.printStackTrace();
        }
        catch(IOException ioe)  {
            ioe.printStackTrace();
        }

        return new JSONObject(json);*/
    }

    public boolean writeJSONToAsset(JSONObject json_data, Context ctx)  {

        try {
            FileOutputStream fos = ctx.openFileOutput(filename, MODE_WRITE_ONLY);

            String str = json_data.toString();
            fos.write(str.getBytes());
            fos.close();

        }
        catch(FileNotFoundException fnf)  {
            AndroidHelper.makeToast(ctx, "File Not Found Exception", false);
            fnf.printStackTrace();
        }
        catch(IOException ioe)  {
            AndroidHelper.makeToast(ctx, "IOException", false);
            ioe.printStackTrace();
        }

        /*
        try  {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ctx.openFileOutput(filename, ctx.MODE_PRIVATE));
            outputStreamWriter.write(json_data.toString());
            outputStreamWriter.close();
        }
        catch(IOException e)  {
            e.printStackTrace();
        }
        */
        return false;
    }
}
