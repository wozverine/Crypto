package com.example.android.crypto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

public class DetailsActivity extends AppCompatActivity {

    ImageView iconView;
    TextView nameView;
    TextView priceView;
    TextView descView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);

        Intent intent = getIntent();
        String icon = intent.getStringExtra("icon");
        String name = intent.getExtras().getString("name");
        String price = intent.getExtras().getString("price");
        String desc = intent.getExtras().getString("desc");
        String abb = intent.getExtras().getString("abb");
        iconView = findViewById(R.id.detail_icon);
        nameView = findViewById(R.id.detail_name);
        priceView = findViewById(R.id.detail_price);
        descView = findViewById(R.id.detail_desc);

        toolbar.setTitle(abb);
        setSupportActionBar(toolbar);

        Uri uri = Uri.parse(icon);

        GlideToVectorYou
                .init()
                .with(DetailsActivity.this)
                .setPlaceHolder(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground)
                .load(uri, iconView);
        nameView.setText(name);
        priceView.setText(price);
        if (desc.equals("null") ){
            desc="No description";
        }
        descView.setText(desc);
    }
}
