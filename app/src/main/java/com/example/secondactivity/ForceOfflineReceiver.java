package com.example.secondactivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ForceOfflineReceiver extends android.content.BroadcastReceiver{
    public ForceOfflineReceiver(){
        super();
    }
    @Override
    public void onReceive(Context context, Intent intent){
        Log.d("TAG", "onReceive: ");
        Toast.makeText(context,"Turning Offline soon",Toast.LENGTH_SHORT).show();
    }
}