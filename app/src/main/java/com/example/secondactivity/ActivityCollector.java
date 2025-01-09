package com.example.secondactivity;
import java.util.*;
import android.app.Activity;
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();
    public static void addActivity(Activity a) {
        activities.add(a);
    }

    public static void finishAll(){
        for(Activity a: activities)if(!a.isFinishing())a.finish();
    }
    public static void removeActivity(Activity a) {
        activities.remove(a);
    }
}
