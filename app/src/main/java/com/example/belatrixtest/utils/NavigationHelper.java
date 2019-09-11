package com.example.belatrixtest.utils;

import android.content.Context;
import android.content.Intent;

public class NavigationHelper {

    public static void goToActivity(Context context, Class customClass){
        Intent intent = new Intent(context, customClass);
        context.startActivity(intent);
    }

}
