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



    static private String filename;
    static private String passwordTag;
    static private String lengthTag;



    public static void storeNew(ArrayList<Stroke> strokes, Context ctx)  {

        initTags(ctx);

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
                subObj = new JSONObject();
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

    public static void displayPw(Context ctx)  {
        initTags(ctx);

        JSON_Helper jhelp = new JSON_Helper(filename);

        try {
            if (isPwSet(ctx)) {
                JSONObject jsonpw = jhelp.loadJSON(ctx);
                AndroidHelper.makeToast(ctx, jsonpw.toString(), false);
            }
        }
        catch (JSONException e)  {
            e.printStackTrace();
        }
    }

    public static boolean isPwSet(Context ctx)  {
        initTags(ctx);

        JSON_Helper jhelp = new JSON_Helper(filename);

        try {
            JSONObject jsonpw = jhelp.loadJSON(ctx);

            System.out.println("JSONPW: " + jsonpw);
            if (jsonpw != null && !jsonpw.get(passwordTag).equals("") && jsonpw.get(passwordTag) != null && !jsonpw.equals(new JSONObject())) {
                return true;
            } else {
                return false;
            }
        }
        catch(JSONException e)  {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean comparePw(ArrayList<Stroke> input, int freedom, Context ctx)  {
        initTags(ctx);

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

    private static void initTags(Context ctx)  {
        filename = ctx.getString(R.string.filename);
        passwordTag = ctx.getString(R.string.pwTag);
        lengthTag = ctx.getString(R.string.lengthTag);
    }


    //TESTING ONLY
    public static void resetPassword(Context ctx)  {
        initTags(ctx);

        JSON_Helper jhelp = new JSON_Helper(filename);
        JSONObject jsonpw = new JSONObject();

        try {
            jsonpw = jhelp.loadJSON(ctx);
            jsonpw.put(passwordTag, "");
            jsonpw.put(lengthTag, "");
        }
        catch(JSONException jse)  {
            jse.printStackTrace();
        }

        jhelp.writeJSON(jsonpw, ctx);

    }
}
