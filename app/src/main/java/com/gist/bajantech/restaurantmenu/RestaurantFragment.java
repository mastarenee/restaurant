package com.gist.bajantech.restaurantmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class RestaurantFragment extends Fragment{

    View view;
    Button firstButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_restaurant_info, container, false);

        // get the reference of Button
        firstButton = (Button) view.findViewById(R.id.firstButton);

        // perform setOnClickListener on first Button
        firstButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            // display a message by using a Toast
                Toast.makeText(getActivity(), "Restaurant Fragment", Toast.LENGTH_LONG).show();
                getActivity().getFragmentManager().popBackStackImmediate();
            }

        });

        return view;

    }


}
