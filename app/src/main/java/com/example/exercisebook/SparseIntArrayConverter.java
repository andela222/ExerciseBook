package com.example.exercisebook;

import android.util.Log;
import android.util.SparseIntArray;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class SparseIntArrayConverter {
    @TypeConverter
    public static SparseIntArray fromString(String value) {
        SparseIntArray intDict = new SparseIntArray();
        if (value != null) {
            try {
                JSONArray jsonArray = new JSONArray(value);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    intDict.put(item.getInt("key"), item.getInt("order"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return intDict;
    }

    @TypeConverter
    public static String fromSparseIntArray(SparseIntArray intDict) {
        String stringFormat = null;

        JSONArray json = new JSONArray();
        StringBuffer data = new StringBuffer().append("[");
        for(int i = 0; i < intDict.size(); i++) {
            data.append("{")
                    .append("\"key\": ")
                    .append(intDict.keyAt(i)).append(",")
                    .append("\"order\": ")
                    .append(intDict.valueAt(i))
                    .append("},");
            json.put(data);
        }
        data.append("]");



        if(intDict.size() > 0)
            stringFormat = data.toString();

        return stringFormat;
    }
}
