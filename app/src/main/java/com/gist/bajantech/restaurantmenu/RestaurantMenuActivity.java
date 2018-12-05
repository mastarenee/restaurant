package com.gist.bajantech.restaurantmenu;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.TypedValue;
import android.view.View;
import android.app.Dialog;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.content.DialogInterface;
import android.net.Uri;
import android.content.pm.PackageManager;
import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.ActivityCompat;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Button;
import android.os.Vibrator;
import android.os.VibrationEffect;
import android.os.Build;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMenuActivity extends AppCompatActivity {

    RelativeLayout mRelativeLayout;
    private Context mContext;
    public int cardTop = 15;
    public String mRestaurantNumber;

    private List<RestaurantMenu> restaurantMenus;
    myDbAdapter helper;
    private RecyclerView rv;

    private static final int PERMISSIONS_REQUEST_SEND_SMS=0;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    Button sendButton;
    EditText phoneNumbertxt;
    EditText smsMessage;
    String phoneNumber;
    String sms;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurantlistactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){

        // Grab activity information
        String restaurant_id = getIntent().getStringExtra("RESTAURANT_ID");
        String restaurant_title = getIntent().getStringExtra("RESTAURANT_TITLE");
        this.mRestaurantNumber = getIntent().getStringExtra("RESTAURANT_NUMBER");

        // Update Activity Name
        setTitle(restaurant_title);

        helper = new myDbAdapter(this);

        // Grab them from the database
        restaurantMenus = new ArrayList<>();
        restaurantMenus = helper.getRestaurantMenu(restaurant_title);
        if( restaurantMenus.isEmpty() ){
            Message.message(this,"There was an error retrieving the menu options");
        }
    }

    private void initializeAdapter(){
        RTMAdapter adapter = new RTMAdapter(restaurantMenus);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.restaurant_menu_menu, menu);
        return true;
    }

    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to place this order");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

                                if (Build.VERSION.SDK_INT >= 26) {
                                    vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                                } else {
                                    vibrator.vibrate(200);
                                }

                                sendSMSMessage();
                                Message.message(getApplicationContext(), "Order Placed");
                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void makeCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0377778888"));

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }

    String phoneNo;
    String message;

    protected void sendSMSMessage() {
        phoneNo = "0377778888"; //txtphoneNo.getText().toString();
        message = "hello"; //txtMessage.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

}
