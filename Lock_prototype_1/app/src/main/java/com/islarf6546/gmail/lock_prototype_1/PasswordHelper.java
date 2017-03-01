package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by islarf on 28/02/17.
 */

public class PasswordHelper {

    final static private String filename = "password.json";


    public static void storeNew(JSONObject newPassword, Context ctx)  {
        JSON_Helper json_help = new JSON_Helper(filename);
        json_help.writeJSON(newPassword, ctx);
    }

    public static boolean comparePw(ArrayList<Stroke> input, int freedom, Context ctx)  {

        ArrayList<Stroke> storepw = new ArrayList<Stroke>();
        try {
            JSON_Helper jhelp = new JSON_Helper(filename);
            JSONObject jsonpw = jhelp.loadJSON(ctx);
            JSONArray ja = jsonpw.getJSONArray("password");

            for(int i=0; i<jsonpw.getInt("length"); i++)  {
                JSONObject obj1 = (JSONObject) ((JSONArray)ja.get(i)).get(0);
                JSONObject obj2 = (JSONObject) ((JSONArray)ja.get(i)).get(1);

                Coordinate start = new Coordinate(obj1.getInt("x"), obj1.getInt("y"));
                Coordinate end = new Coordinate(obj2.getInt("x"), obj2.getInt("y"));
                storepw.add(new Stroke(start, end));
            }

        }
        catch(JSONException jse)  {
            AndroidHelper.makeToast(ctx, "Error loading password file", false);
            jse.printStackTrace();
        }

        boolean match = GeometryMath.isCorrect(storepw, input, freedom);
        if(match)  {
            System.out.println("PHONE UNLOCKED");
        }
        else  {
            System.out.println("ACCESS DENIED");
        }

        boolean toRet = false;
        return toRet;
    }
}
