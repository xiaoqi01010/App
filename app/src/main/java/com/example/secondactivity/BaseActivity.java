package com.example.secondactivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    protected ForceOfflineReceiver forceOfflineReceiver;
    public void onCreate(Bundle savedInstance) {
        Log.d("TAG", "onCreate: ");
        super.onCreate(savedInstance);
        ActivityCollector.addActivity(this);
    }

protected void onResume() {
        super.onResume();
        Log.d("TAG", "onResume: ");
        IntentFilter intentFilter1 = new IntentFilter("com.example.secondactivity.FORCE_OFFLINE");
        intentFilter1.addCategory(Intent.CATEGORY_DEFAULT);
        forceOfflineReceiver = new ForceOfflineReceiver();
        LocalBroadcastManager.getInstance(BaseActivity.this).registerReceiver(forceOfflineReceiver, intentFilter1);
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

    class ForceOfflineReceiver extends android.content.BroadcastReceiver{
        public ForceOfflineReceiver(){
            super();
        }
        @Override
        public void onReceive(Context context, Intent intent){
            Log.d("TAG", "onReceive: ");
            Toast.makeText(context,"Turning Offline soon",Toast.LENGTH_SHORT).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
            builder.setTitle("Warning");
            builder.setMessage("You are about to log out");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();
                    Intent intent1 = new Intent(context,LogIn.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);
                }
            });
            builder.show();
        }
    }

}

