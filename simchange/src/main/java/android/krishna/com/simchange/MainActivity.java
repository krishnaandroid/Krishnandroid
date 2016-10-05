package android.krishna.com.simchange;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    public void buttonClick(){
        Log.d("buttonClick", "first");
        TelephonyManager telephonyManager = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        System.out.println("telephoneManager" + telephonyManager);
        String mobileNumber = telephonyManager.getLine1Number();
        if(mobileNumber != null){

        }else{
            mobileNumber = "Value is null";
        }
        System.out.println("mobileNumber" + mobileNumber);
        int versionNumber = Build.VERSION.SDK_INT;

         Toast.makeText(getBaseContext(), "mobileNumber is"+mobileNumber, Toast.LENGTH_LONG).show();

        SubscriptionManager subscriptionManager = SubscriptionManager.from(this.getApplicationContext());
        int count = subscriptionManager.getActiveSubscriptionInfoCount();
        final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
        Toast.makeText(getBaseContext(), subscriptionInfoList.size()+"", Toast.LENGTH_LONG).show();
        int index = 0;
        String[] mobileid = new String[subscriptionInfoList.size()];

        for(SubscriptionInfo s : subscriptionInfoList){
            int subid = s.getSubscriptionId();
            //  Toast.makeText(getBaseContext(), subid+"", Toast.LENGTH_LONG).show();
            mobileid[index++] = s.getCarrierName().toString();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Mobile Number Registered With the Bank");
        builder.setItems(mobileid, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SmsManager sms = SmsManager.getSmsManagerForSubscriptionId(subscriptionInfoList.get(which).getSubscriptionId());
                Toast.makeText(getBaseContext(), "sms object is"+sms, Toast.LENGTH_LONG).show();
                sms.sendTextMessage("+919703517026",null,"hi",null,null);
              }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
