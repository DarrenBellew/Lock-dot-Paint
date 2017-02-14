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


}
