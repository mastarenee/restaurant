package com.gist.bajantech.restaurantmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.ProgressBar;
import android.os.CountDownTimer;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SetupActivity extends AppCompatActivity implements Animation.AnimationListener{

    myDbAdapter helper;
    public int counter;
    ProgressBar simpleProgressBar;
    TextView setuptext;
    LinearLayout setUpSteps;

    Animation animSlidedown;
    Animation animSlideup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupactivity);

        helper = new myDbAdapter(this);

        // Animation handler
        // Animation Down
        animSlidedown = AnimationUtils.loadAnimation(this,
                R.anim.slide_down);
        animSlidedown.setAnimationListener(this);

        // Animation Down
        animSlideup = AnimationUtils.loadAnimation(this,
                R.anim.slide_up);
        animSlideup.setAnimationListener(this);

        // Get Elements
        simpleProgressBar=(ProgressBar) findViewById(R.id.simpleProgressBar);
        setuptext =(TextView) findViewById(R.id.setuptext);
        setUpSteps = (LinearLayout) findViewById(R.id.setUpSteps);

        // Set ProgressBar Max
        simpleProgressBar.setMax(100);

        // Start Countdown
        new CountDownTimer(7000, 1000){
            public void onTick(long millisUntilFinished){
                simpleProgressBar.setProgress(counter * 10);
                counter++;
            }
            public void onFinish(){
                simpleProgressBar.setProgress(100);
                simpleProgressBar.startAnimation(animSlidedown);
                setuptext.startAnimation(animSlidedown);
            }
        }.start();

        // Setup Database
        setupRestaurantsDB();

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
    }

    private void setupRestaurantsDB(){

        if ( helper.restaurantDataInstalled() == false ) {
            helper.insertRestaurantData("Live Love Home", "222-3934532", "First Can", "1");
            helper.insertRestaurantData("Fresh Mush", "298-3980934", "First Can", "1");
            helper.insertRestaurantData("Lorem Ipsum", "9087-34534", "Second Can", "1");
            helper.insertRestaurantData("Dumplins & Breakfast", "345211-3455", "Second Can", "1");

            helper.insertRestaurantMenuData("Pasta With Chicken", "", "Live Love Home", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nunc pretium, ornare leo tincidunt, iaculis enim.", "10", "1");
            helper.insertRestaurantMenuData("Rice With Chicken", "", "Live Love Home", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nunc pretium, ornare leo tincidunt, iaculis enim.", "6", "1");
            helper.insertRestaurantMenuData("Potatoes With Chicken", "", "Live Love Home", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nunc pretium, ornare leo tincidunt, iaculis enim.", "5", "1");
            helper.insertRestaurantMenuData("Rice With Pork", "", "Fresh Mush", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nunc pretium, ornare leo tincidunt, iaculis enim.", "3", "1");
            helper.insertRestaurantMenuData("Potatoes With Pork", "", "Fresh Mush", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nunc pretium, ornare leo tincidunt, iaculis enim.", "12", "1");

            helper.insertRestaurantMenuData("Dumplins", "", "Fresh Mush", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nunc pretium, ornare leo tincidunt, iaculis enim.", "10", "1");
            helper.insertRestaurantMenuData("Jam Pastry Regular", "", "Lorem Ipsum", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nunc pretium, ornare leo tincidunt, iaculis enim.", "3", "1");
            helper.insertRestaurantMenuData("Chopped Pizza", "", "Lorem Ipsum", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nunc pretium, ornare leo tincidunt, iaculis enim.", "11", "1");
            helper.insertRestaurantMenuData("Jam Pastry Purple", "", "Dumplins & Breakfast", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nunc pretium, ornare leo tincidunt, iaculis enim.", "10", "1");
            helper.insertRestaurantMenuData("Egg Roll", "", "Dumplins & Breakfast", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nunc pretium, ornare leo tincidunt, iaculis enim.", "10", "1");

            helper.insertRestaurantAdminData("romario", "123456", "1");
            helper.insertRestaurantAdminData("admin", "123456", "1");
        }

        //final boolean checkStatusOfAdmin = helper.restaurantAdminInstalled();
        //Message.message(this, "checkStatusOfAdmin " + checkStatusOfAdmin);

        /*final boolean checkStatusOfProduct = helper.restaurantProductInstalled();
        Message.message(this, "checkStatusOfProduct " + checkStatusOfProduct);*/

    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        simpleProgressBar.setVisibility(View.GONE);
        setuptext.setVisibility(View.GONE);
        setUpSteps.setAlpha(1);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    public void action_login(View view) {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }

    public void action_signup(View view) {
        Intent intent = new Intent(getApplicationContext(),Signup.class);
        startActivity(intent);
    }

}
