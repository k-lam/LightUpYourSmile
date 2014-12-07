package util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by K.Lam on 2014/12/7.
 */
public class Pressure {
    //Pressure取0-1
    float max;
    float min;
    float avg;
    int times;
    Context mContext;

    public Pressure(Context context){
        mContext = context;
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        min = preferences.getFloat("min",0);
        max = preferences.getFloat("max",1);
        avg = preferences.getFloat("avg",0.5f);
        times = preferences.getInt("times",0);
    }


}
