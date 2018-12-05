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

public class RTMAdapter extends RecyclerView.Adapter<RTMAdapter.RestaurantMenuViewHolder> {

    public static class RestaurantMenuViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView restaurantID;
        TextView itemName;
        TextView itemDescription;
        TextView itemPrice;
        private View.OnClickListener mClickListener;
        //ImageView restaurantPhoto;

        private View.OnClickListener mMyLocalClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message.message(v.getContext(),"clicked " + itemName.getText() + " " + cv.getId());
            }
        };

        RestaurantMenuViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardmenu);
            itemName = (TextView)itemView.findViewById(R.id.restaurant_name);
            itemDescription = (TextView)itemView.findViewById(R.id.restaurant_description);
            itemPrice = (TextView)itemView.findViewById(R.id.restaurant_price);
            //restaurantPhoto = (ImageView)itemView.findViewById(R.id.restaurant_photo);
            cv.setOnClickListener(mMyLocalClickListener);
        }
    }

    List<RestaurantMenu> restaurantMenus;

    RTMAdapter(List<RestaurantMenu> restaurantMenus){
        this.restaurantMenus = restaurantMenus;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RestaurantMenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.restmenuitem, viewGroup, false);
        RestaurantMenuViewHolder rvh = new RestaurantMenuViewHolder(view);
        return rvh;
    }


    @Override
    public void onBindViewHolder(RestaurantMenuViewHolder restaurantMenuViewHolder, int i) {
        restaurantMenuViewHolder.itemName.setText(restaurantMenus.get(i).name);
        restaurantMenuViewHolder.itemDescription.setText(restaurantMenus.get(i).description);
        restaurantMenuViewHolder.itemPrice.setText("$ " + restaurantMenus.get(i).price);
        //personViewHolder.restaurantPhoto.setImageResource(restaurants.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return restaurantMenus.size();
    }

}