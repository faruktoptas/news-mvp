package me.toptas.rssreader.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    public static String readFromAssets(Context context, String fileName) {
        String ret = null;
        AssetManager assetManager = context.getAssets();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(assetManager.open(fileName)));
            String mLine;
            StringBuilder builder = new StringBuilder();
            while ((mLine = reader.readLine()) != null) {
                builder.append(mLine);
            }
            ret = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}