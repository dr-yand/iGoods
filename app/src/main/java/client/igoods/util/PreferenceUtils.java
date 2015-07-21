package client.igoods.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import client.igoods.model.User;

public class PreferenceUtils {

    public static void saveUser(Context context, User user){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        /*editor.putString("id", user.id);
        editor.putString("login", user.login);
        editor.putString("password", user.password);
        editor.putBoolean("activated", user.activated);*/
        editor.commit();
    }

    public static void removeUser(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("id");
        editor.remove("login");
        editor.remove("password");
        editor.commit();
    }

    public static User getUser(Context context) {
        User result = new User();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        /*result.id=preferences.getString("id", "");
        result.login=preferences.getString("login", "");
        result.password=preferences.getString("password", "");
        result.activated=preferences.getBoolean("activated",false);*/
        return result;
    }
}
