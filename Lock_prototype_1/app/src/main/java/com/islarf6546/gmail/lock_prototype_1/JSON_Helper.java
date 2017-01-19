package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Islarf on 23/12/2016.
 */

public class JSON_Helper {

    JSONArray data;
    JSONObject json_object;
    FileOutputStream fos;
    FileInputStream fis;


    public JSON_Helper()  {
        this.data = new JSONArray();
        this.json_object = new JSONObject();
    }

    public boolean createFile(JSONObject new_object, String filename, Context ctx) throws IOException, JSONException {
        try {
            String text;
            JSONArray data_temp;
            data_temp = new JSONArray();
            data_temp.put(new_object);

            String text = data_temp.toString();

            FileOutputStream fos = ctx.openFileOutput(filename, MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();

            Toast.makeText(ctx, "JSON object made", LENGTH_SHORT);
            return true;
        }
        catch (JSONException e)  {
            e.printStackTrace();
            return false;
        }
    }
}
