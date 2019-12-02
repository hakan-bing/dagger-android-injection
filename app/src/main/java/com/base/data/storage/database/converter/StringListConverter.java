package com.base.data.storage.database.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * This class used for json to List{@link List<Object>} and List{@link List<Object>} to json.
 * When create entity{@link android.arch.persistence.room.Entity} has a List{@link List<Object>}, you must define type converter{@link TypeConverter}
 *
 * @author Hakan Bingöl
 */
public class StringListConverter {

    @TypeConverter
    public static List<String> fromString(String value) {
        Type listType = new TypeToken<List<String>>(){}.getType();
        return new GsonBuilder().create().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<String> list) {
        return new GsonBuilder().create().toJson(list);
    }
}
