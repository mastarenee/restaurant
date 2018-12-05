package com.gist.bajantech.restaurantmenu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.View;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.widget.LinearLayout.LayoutParams;
import android.graphics.Color;
import java.util.ArrayList;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.view.LayoutInflater;

import static com.gist.bajantech.restaurantmenu.R.*;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    CardView restaurantFragment;
    myDbAdapter helper;
    RelativeLayout mRelativeLayout;
    public int cardTop = 15;
    private List<Person> persons;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case id.navigation_dashboard:
                    mTextMessage.setText(string.title_dashboard);
                    return true;
                case id.navigation_notifications:
                    mTextMessage.setText(string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        helper = new myDbAdapter(this);
        mRelativeLayout = (RelativeLayout) findViewById(id.rl);

        new RecyclerViewActivity();

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);

        // Grab them from the database
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        restaurantList = helper.getRestaurantData();
        //System.out.println("RESTAURANT LISTING");
        //System.out.println(restaurantList);
        for (int i = 0; i < restaurantList.size(); i++){
            //System.out.println(restaurantList.get(i).getTitle());
            Restaurant resta = restaurantList.get(i);
            CreateCardsDyn(resta.getId(), resta.getTitle(), resta.getNumber(), resta.getCanteen() );
        }

        mTextMessage = (TextView) findViewById(id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void viewRestaurantData(View view)
    {


        //Message.message(this, "Restaurants Loaded");
    }

    private Context mContext;


    public void CreateCardsDyn(int id, String title, String number, String Canteen){

        // Get the application context
        mContext = getApplicationContext();

        // Initialize a new CardView
        CardView card = new CardView(mContext);

        // Set the CardView layoutParams
        LayoutParams params = new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        card.setLayoutParams(params);

        // Set CardView corner radius
        card.setRadius(9);

        card.setTranslationY(cardTop);

        // Set cardView content padding
        card.setContentPadding(15, 15, 15, 15);
        cardTop = cardTop + 290;

        card.setTop(cardTop);

        // Set a background color for CardView
        card.setCardBackgroundColor(Color.WHITE);

        // Set the CardView maximum elevation
        card.setMaxCardElevation(15);

        // Set CardView elevation
        card.setCardElevation(9);

        // Initialize a new TextView to put in CardView
        TextView tv = new TextView(mContext);
        tv.setLayoutParams(params);
        tv.setText(title + " \n " + number);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        tv.setTextColor(Color.BLACK);
        tv.setTypeface(null, Typeface.BOLD);

        // Put the TextView in CardView
        card.addView(tv);

        final int rest_id = id;
        final String rest_title = title;
        final String rest_number = number;

        // Perform setOnClickListener event on Restaurant Card
        card.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Message.message(view.getContext(), "Restaurants Loaded");

                Intent myIntent = new Intent(view.getContext(), RestaurantMenuActivity.class);

                myIntent.putExtra("RESTAURANT_ID", rest_id);
                myIntent.putExtra("RESTAURANT_TITLE", rest_title);
                myIntent.putExtra("RESTAURANT_NUMBER", rest_number);

                startActivity(myIntent);
            }

        });

        // Finally, add the CardView in root layout
        mRelativeLayout.addView(card);

    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}
