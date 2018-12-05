package com.gist.bajantech.restaurantmenu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Signup extends AppCompatActivity {

    private TextView usernametxt;
    private EditText username;
    private TextView passwordtxt;
    private EditText password;
    private TextView confirmpasswordtxt;
    private EditText confirmpassword;
    private Button submit;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        usernametxt = (TextView) findViewById(R.id.usernametxt);
        username = (EditText) findViewById(R.id.username);
        passwordtxt = (TextView) findViewById(R.id.passwordtxt);
        password = (EditText) findViewById(R.id.password);
        confirmpasswordtxt = (TextView) findViewById(R.id.confirmpasswordtxt);
        confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        submit = (Button) findViewById(R.id.submit);

        // Start Video Playing in Background
        VideoView videoview = (VideoView) findViewById(R.id.videoview);

        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.whipping;
        Uri uri = Uri.parse(uriPath);
        videoview.setVideoURI(uri);
        videoview.start();

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Signup.this,"Signing Up",Toast.LENGTH_SHORT).show();
                signup();
            }
        });
    }

    private void signup() {

        String uname = username.getText().toString();
        String pswd = password.getText().toString();

        helper = new myDbAdapter(this);
        helper.insertRestaurantAdminData(uname,pswd,"1");

        Intent intent = new Intent(getApplicationContext(),RestaurantListActivity.class);
        startActivity(intent);
        
    }
}
