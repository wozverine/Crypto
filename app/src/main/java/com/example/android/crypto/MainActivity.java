package com.example.android.crypto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements ListItemClickListener{
    private CryptoAdapter cAdapter;
    private RecyclerView mNumbersList;
    String URLString="https://api.coinranking.com/v1/public/coins";


    private static final int WORDS_LIST_ITEMS = 5;
    private static final CoinInfo[] LIST= {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNumbersList = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);
        mNumbersList.setHasFixedSize(true);

        cAdapter = new CryptoAdapter(WORDS_LIST_ITEMS, LIST, this);
        mNumbersList.setAdapter(cAdapter);
        FetchInfo cTask = new FetchInfo(cAdapter);
        cTask.execute(URLString);

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        CoinInfo[] cInfos = cAdapter.getdata();
        CoinInfo singleItemInfo = cInfos[clickedItemIndex];
        intent.putExtra("icon",singleItemInfo.getIcon());
        intent.putExtra("name",singleItemInfo.getName());
        intent.putExtra("price",singleItemInfo.getPrice());
        intent.putExtra("desc",singleItemInfo.getDesc());
        intent.putExtra("abb",singleItemInfo.getAbb());
        startActivity(intent);
    }
}
