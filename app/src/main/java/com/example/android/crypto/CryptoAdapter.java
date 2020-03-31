package com.example.android.crypto;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoViewHolder> {
    private int mNumberItems;
    private CoinInfo[] items;
    private ListItemClickListener mOnClickListener;
    int ID = 0;

    public CryptoAdapter(int numberOfItems, CoinInfo[] itemList, ListItemClickListener listener) {
        mNumberItems        = numberOfItems;
        items               = itemList;
        mOnClickListener    = listener;
    }

    @Override
    public CryptoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        ID=R.layout.list_view;

        int            layoutIdForListItem             = ID;
        LayoutInflater inflater                        = LayoutInflater.from(context);
        boolean        shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        CryptoViewHolder viewHolder = new CryptoViewHolder(view, mOnClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CryptoViewHolder holder, int position) {
        holder.bind(items[position]);
    }

    @Override
    public int getItemCount() {

        return items.length;
    }

    public void setData(CoinInfo[] data) {
        items        = data;
        mNumberItems = data.length;
        notifyDataSetChanged();
    }
    public CoinInfo[] getdata() {
        return items;
    }
}
