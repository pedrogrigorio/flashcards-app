package com.example.flashcards_app.util;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.core.content.ContextCompat;

public class AppPreferences {
    private static SharedPreferences sharedPreferences;

    // Call `AppPreferences.setup(applicationContext)` in your MainActivity's `onCreate` method
    public static void setup(Context context) {
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    }

    // Replace these example attributes with your stored values
    public static String getAccessToken() {
        return getKey(Key.TOKEN).getString("token");
    }

    public static void setAccessToken(String accessToken) {
        getKey(Key.TOKEN).setString("token", accessToken);
    }

    public static String getUserId() {
        return getKey(Key.USERID).getString("userId");
    }

    public static void setUserId(String userId) {
        getKey(Key.USERID).setString("userId", userId);
    }

    public static void cleanUserSession() {
        getKey(Key.TOKEN).remove("token");
        getKey(Key.USERID).remove("userId");
    }

    private enum Key {
        TOKEN, USERID; // Replace these cases with your stored values keys

        public String getString(String name) {
            return sharedPreferences.contains(name) ? sharedPreferences.getString(name, "") : null;
        }

        public void setString(String name, String value) {
            if (value != null) {
                sharedPreferences.edit().putString(name, value).apply();
            } else {
                remove(name);
            }
        }

        public boolean exists() {
            return sharedPreferences.contains("token");
        }

        public void remove(String name) {
            sharedPreferences.edit().remove(name).apply();
        }
    }

    private static Key getKey(Key key) {
        return key;
    }
}
