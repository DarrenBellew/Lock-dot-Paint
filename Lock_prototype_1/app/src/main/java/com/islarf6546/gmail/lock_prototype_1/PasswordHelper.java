package com.islarf6546.gmail.lock_prototype_1;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
* Author: Darren Bellew
*
* This class contains static methods which help with the password.
* such as storing, is the pw set and comparing the password.
*
* Basic json tutorial: https://www.tutorialspoint.com/android/android_json_parser.htm
* Most was discovered through auto-complete provided with AndroidStudio
*
*/

public class PasswordHelper {




    static private String filename;
    static private String passwordTag;
    static private String lengthTag;



    public static void storeNew(ArrayList<Stroke> strokes, Context ctx)  {
        /*
        * This method creates the JSON to store as the new password; stored in strokes.
        */
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
        /*
        * A testing method used to display the current stored password via Toast.
        */

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
        /*
        * A method that checks if a password is set; if set returns a boolean true, otherwise false.
        */

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
        /*
        * A method that compares the inputted password (input) with the stored password which it gets from the stores json file.
        * It compares the password by using GeometryMath.isCorrect().
        */
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

    private static void initTags(Context ctx)  {
        /*
        * Initialise static values by getting the values from strings.xml.
        */
        filename = ctx.getString(R.string.filename);
        passwordTag = ctx.getString(R.string.pwTag);
        lengthTag = ctx.getString(R.string.lengthTag);
    }

}
