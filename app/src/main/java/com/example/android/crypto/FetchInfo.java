package com.example.android.crypto;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchInfo extends AsyncTask<String, Void, CoinInfo[]> {
    CryptoAdapter cryptoAdapter;

    public FetchInfo(CryptoAdapter cAdapter){
        cryptoAdapter = cAdapter;
    }

    @Override
    protected CoinInfo[] doInBackground(String... urlStrings) {

        HttpURLConnection urlConnection   = null;
        BufferedReader reader          = null;
        String 		      cJsonStr = null;

        try {
            URL weatherURL = new URL(urlStrings[0]);
            urlConnection  = (HttpURLConnection) weatherURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer     = new StringBuffer();

            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() != 0) {
                    cJsonStr = buffer.toString();
                }
            }
        } catch (IOException e) {
            Log.e("MainActivity", "Error ", e);
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("MainActivity", "Error closing stream", e);
                }
            }
        }

        try {
            return getDataFromJson(cJsonStr);
        } catch (JSONException e) {
            Log.e("Fetch", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(CoinInfo[] wordInfo) {
        super.onPostExecute(wordInfo);

        cryptoAdapter.setData(wordInfo);
    }

    private CoinInfo[] getDataFromJson(String cJsonStr) throws JSONException {

        final String C_DATA             = "data";
        final String C_COINS            = "coins";
        final String C_SYMBOL           = "symbol";
        final String C_URL              = "iconUrl";
        final String C_DESC              = "description";
        final String C_COLOR              = "color";
        final String C_NAME              = "name";
        final String C_PRICE              = "price";

        JSONObject cJsonObj  = new JSONObject(cJsonStr);
        JSONObject c2JsonObj  = cJsonObj.getJSONObject(C_DATA);
        JSONArray  cJsonArr  = c2JsonObj.getJSONArray(C_COINS);

        CoinInfo[] resultStrs = new CoinInfo[cJsonArr.length()];

        for(int i = 0; i < cJsonArr.length(); i++) {
            resultStrs[i]=new CoinInfo();
            JSONObject obj=cJsonArr.getJSONObject(i);

            String abb = obj.getString(C_SYMBOL);
            resultStrs[i].setAbb(abb);

            String urlstring = obj.getString(C_URL);
            resultStrs[i].setIcon(urlstring);

            String desc = obj.getString(C_DESC);
            resultStrs[i].setDesc(desc);

            String color = obj.getString(C_COLOR);
            resultStrs[i].setColor(color);

            String name = obj.getString(C_NAME);
            resultStrs[i].setName(name);

            String price = String.format("%.2f", obj.getDouble(C_PRICE));
            resultStrs[i].setPrice(price);
        }
        return resultStrs;
    }
}
