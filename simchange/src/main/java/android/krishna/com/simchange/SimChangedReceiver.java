package android.krishna.com.simchange;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Set;

public class SimChangedReceiver extends BroadcastReceiver {

    public SimChangedReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
       Bundle b = intent.getExtras();
        Set<String> keys = b.keySet();
        for (String key : keys) {
            Log.d("key is:", key);
            Object o = b.get(key);
            if(o!=null) {
                Log.d("value is:", o.toString());
            }

        }
        Log.d("SimChangedReceiver", "--> SIM state changed <--");

        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        System.out.println("telephoneManager" + telephonyManager);
        String mobileNumber = telephonyManager.getLine1Number();

        String MSI = telephonyManager.getSubscriberId();
        if(mobileNumber != null){

        }else{
            mobileNumber = "Value is null";
        }

        if(MSI != null){

        }else{
            MSI = "MSI Value is null";
        }
        System.out.println("MSI is :" + MSI);
        System.out.println("mobileNumber" + mobileNumber);
        Toast.makeText(context, "mobileNumber is"+mobileNumber, Toast.LENGTH_LONG).show();
//        Intent intent1 = new Intent(context,SecondActivity.class);
//        context.startActivity(intent1);
    }
}
