package com.gist.bajantech.restaurantmenu;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.view.ViewGroup;
import java.util.List;

public class RTAdapter extends RecyclerView.Adapter<RTAdapter.RestaurantViewHolder> {

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView restaurantID;
        TextView restaurantName;
        TextView restaurantPhone;
        TextView restaurantCanteen;
        private View.OnClickListener mClickListener;
        //ImageView restaurantPhoto;

        private View.OnClickListener mMyLocalClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message.message(v.getContext(),"clicked " + restaurantName.getText() + " " + cv.getId());

                Intent myIntent = new Intent(v.getContext(), RestaurantMenuActivity.class);

                myIntent.putExtra("RESTAURANT_ID", cv.getId());
                myIntent.putExtra("RESTAURANT_TITLE", restaurantName.getText());
                myIntent.putExtra("RESTAURANT_NUMBER", restaurantPhone.getText());

                v.getContext().startActivity(myIntent);
            }
        };

        RestaurantViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.rcv);
            restaurantName = (TextView)itemView.findViewById(R.id.restaurant_name);
            restaurantPhone = (TextView)itemView.findViewById(R.id.restaurant_phone);
            restaurantCanteen = (TextView)itemView.findViewById(R.id.restaurant_canteen);
            //restaurantPhoto = (ImageView)itemView.findViewById(R.id.restaurant_photo);
            cv.setOnClickListener(mMyLocalClickListener);
        }
    }

    List<Restaurant> restaurants;

    RTAdapter(List<Restaurant> restaurants){
        this.restaurants = restaurants;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.restitem, viewGroup, false);
        RestaurantViewHolder rvh = new RestaurantViewHolder(view);
        return rvh;
    }


    @Override
    public void onBindViewHolder(RestaurantViewHolder restaurantViewHolder, int i) {
        restaurantViewHolder.restaurantName.setText(restaurants.get(i).name);
        restaurantViewHolder.restaurantPhone.setText("Tel: " + restaurants.get(i).phone);
        restaurantViewHolder.restaurantCanteen.setText("View Menu");
        //restaurants.get(i).canteen
        //personViewHolder.restaurantPhoto.setImageResource(restaurants.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

}