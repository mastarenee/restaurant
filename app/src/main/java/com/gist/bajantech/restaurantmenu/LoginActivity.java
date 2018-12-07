package com.gist.bajantech.restaurantmenu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

public class LoginActivity extends AppCompatActivity {

    private ImageView loginbg;
    private ImageView logo;
    private TextView lusernametxt;
    private EditText lusername;
    private TextView lpasswordtxt;
    private EditText lpassword;
    private Button loginbutton;
    private Button signupbutton;
    private TextView loginError;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        helper = new myDbAdapter(this);

        lusernametxt = (TextView) findViewById(R.id.lusernametxt);
        lusername = (EditText) findViewById(R.id.lusername);
        lpasswordtxt = (TextView) findViewById(R.id.lpasswordtxt);
        lpassword =(EditText) findViewById(R.id.lpassword);

        loginbutton =(Button) findViewById(R.id.loginbutton);
        signupbutton =(Button) findViewById(R.id.signupbutton);

        loginError = (TextView) findViewById(R.id.loginError);

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

        signupbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);

            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LinearLayout loginWrap = (LinearLayout) findViewById(R.id.loginInformation);
                final boolean visibleornot = loginWrap.isShown();

                //Message.message(getApplicationContext(), "Credentials " + lusername.getText().toString() + " " + lpassword.getText().toString() );

                if( visibleornot ){

                    if( lusername.getText().toString().isEmpty() || lpassword.getText().toString().isEmpty()){

                        Message.message(getApplicationContext(), "Invalid Credentials Entered");
                        loginError.setText("Invalid Credentials");
                        loginError.setVisibility(View.VISIBLE);

                    }else{

                        final boolean state = true;//helper.validateUser(lusername.toString(),lpassword.toString());
                        Message.message(getApplicationContext(), "" + state);
                        if( state == true ){
                            loginError.setVisibility(View.GONE);
                            openrestauranthome();
                        }else{
                            loginError.setText("Invalid Credentials");
                            loginError.setVisibility(View.VISIBLE);
                        }

                    }

                }else{
                    loginWrap.setVisibility(View.VISIBLE);
                }

            }

            private void openrestauranthome() {
                Intent intent = new Intent(getApplicationContext(),RestaurantListActivity.class);
                startActivity(intent);
            }
        });
    }


}
