package com.example.secondactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends BaseActivity {
    NetworkBroadcastReceiver networkBroadcastReceiver;
    //ForceOfflineReceiver forceOfflineReceiver;
    IntentFilter intentFilter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        networkBroadcastReceiver = new NetworkBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkBroadcastReceiver,intentFilter);

        Button button =(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.secondactivity.FORCE_OFFLINE");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                Log.d("BroadcastSender", "Broadcasting intent: " + intent.getAction());
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkBroadcastReceiver);
        ActivityCollector.removeActivity(this);
    }

    class NetworkBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Network Changes",Toast.LENGTH_SHORT).show();
        }
    }

}