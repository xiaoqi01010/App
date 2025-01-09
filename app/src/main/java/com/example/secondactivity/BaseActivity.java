package com.example.secondactivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    private ForceOfflineReceiver forceOfflineReceiver;
    public void onCreate(Bundle savedInstance) {
        Log.d("TAG", "onCreate: ");
        super.onCreate(savedInstance);
        ActivityCollector.addActivity(this);
    }

protected void onResume() {
        super.onResume();
        Log.d("TAG", "onResume: ");
        IntentFilter intentFilter = new IntentFilter("com.example.FORCE_OFFLINE");
        forceOfflineReceiver = new ForceOfflineReceiver();
        registerReceiver(forceOfflineReceiver,intentFilter,RECEIVER_NOT_EXPORTED);
}

protected void onPause(){
        Log.d("TAG", "onPause: ");
        super.onPause();
        if(forceOfflineReceiver != null) {
            unregisterReceiver(forceOfflineReceiver);
            forceOfflineReceiver=null;
        }
}

protected void onDestroy(){
        super.onDestroy();
}
}

