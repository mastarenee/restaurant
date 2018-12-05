package com.gist.bajantech.restaurantmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Button loginb;
    private Button signupb;
    private List<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        //rv.setHasFixedSize(true);

        //LinearLayoutManager llm = new LinearLayoutManager(this);
        //rv.setLayoutManager(llm);

        //RVAdapter adapter = new RVAdapter(persons);
        //rv.setAdapter(adapter);

    }

}
