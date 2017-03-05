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

    /*
        This class is just a helper class with a collection of static methods to store new and compare passwords
     */


    final static private String filename = "password.json";
    final static private String passwordTag = "password";
    final static private String lengthTag = "length";



    public static void storeNew(ArrayList<Stroke> strokes, Context ctx)  {
        JSON_Helper jhelp = new JSON_Helper(filename);
        JSONObject objToStore = new JSONObject();
        JSONArray arrToStore = new JSONArray();

        try {
            for(Stroke stroke : strokes) {
                JSONArray subArr = new JSONArray();
                JSONObject subObj = new JSONObject();

                subObj.put("x", stroke.getStart().getX());
                subObj.put("y", stroke.getStart().getY());
                subArr.put(subObj);
                subObj.put("x", stroke.getEnd().getX());
                subObj.put("y", stroke.getEnd().getY());
                subArr.put(subObj);
                arrToStore.put(subArr);


            }
            objToStore.put(passwordTag, arrToStore);
            objToStore.put(lengthTag, strokes.size());
            jhelp.writeJSON(objToStore, ctx);
        }
        catch(JSONException jse){
            AndroidHelper.makeToast(ctx, "Error creating new password", false);
            jse.printStackTrace();
        }
    }

    public static boolean comparePw(ArrayList<Stroke> input, int freedom, Context ctx)  {

        ArrayList<Stroke> storepw = new ArrayList<Stroke>();
        try {
            JSON_Helper jhelp = new JSON_Helper(filename);
            JSONObject jsonpw = jhelp.loadJSON(ctx);
            JSONArray ja = jsonpw.getJSONArray("password");

            for(int i=0; i<jsonpw.getInt(lengthTag); i++) {
                JSONObject obj1 = (JSONObject) ((JSONArray) ja.get(i)).get(0);
                JSONObject obj2 = (JSONObject) ((JSONArray) ja.get(i)).get(1);

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
            return true;
        }
        else  {
            return false;
        }
    }
}
