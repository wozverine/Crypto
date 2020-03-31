package com.example.android.crypto;

import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

public class CryptoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private  ListItemClickListener mListener;
    public TextView listItemView;
    public TextView priceView;
    public ImageView iconItemView;


    public CryptoViewHolder(View itemView, ListItemClickListener listener) {
        super(itemView);
        listItemView = (TextView) itemView.findViewById(R.id.item_name);
        priceView = (TextView) itemView.findViewById(R.id.price);
        iconItemView = (ImageView) itemView.findViewById(R.id.icon);
        mListener            = listener;
        itemView.setOnClickListener(this);
    }
    void bind(CoinInfo s) {
        listItemView.setText(s.getAbb());
        priceView.setText(s.getPrice());
        int color;
        try{
            color=Color.parseColor(s.getColor());
        }catch (IllegalArgumentException e){
            color = Color.parseColor("#E53935");
        }
        priceView.setTextColor(color);
        listItemView.setTextColor(color);

        Uri uri = Uri.parse(s.getIcon());

        GlideToVectorYou
                .init()
                .with(itemView.getContext())
                .setPlaceHolder(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground)
                .load(uri, iconItemView);
    }

    @Override
    public void onClick(View view) {
        int clickedPosition = getAdapterPosition();
        mListener.onListItemClick(clickedPosition);

    }
}
