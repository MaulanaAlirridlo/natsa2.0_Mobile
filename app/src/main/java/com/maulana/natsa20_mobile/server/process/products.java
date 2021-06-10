package com.maulana.natsa20_mobile.server.process;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;

import com.maulana.natsa20_mobile.adapter.ProductsAdapter;
import com.maulana.natsa20_mobile.model.Products;
import com.maulana.natsa20_mobile.server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class products {

    private static ArrayList<Products> productsList = new ArrayList<>();
    static String DATA_JSON_STRING, data_json_string, idProduct;

    public static ArrayList<Products> getProductsArrayList(){
        return productsList;
    }

    public void getData(Context context, ProductsAdapter adapter, String idProduct){
        this.idProduct = products.idProduct;
        getJSON();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                readDataFromServer(context, adapter);
            }
        }, 1000);
    }

    public void getJSON() {
        new BackgroundTask().execute();
    }

    private static class BackgroundTask {

        String json_url;
        Thread localThread;

        public void execute()
        {
            this.onPreExecute();

            this.localThread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    BackgroundTask.this.onPostExecute(BackgroundTask.this.doInBackground());
                }
            });
            this.localThread.start();
        }

        protected void onPreExecute() {
            if (idProduct == null) {
                json_url = Server.getProducts;
            } else {
                json_url = Server.getProducts+idProduct;
            }
        }

        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((DATA_JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(DATA_JSON_STRING + "\n");
                }


                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            data_json_string = result;
        }
    }

    public void readDataFromServer(Context context, ProductsAdapter adapter) {
        if (checkNetworkConnection(context)) {
            productsList.clear();
            try {
                JSONObject respone = new JSONObject(data_json_string);
                respone = respone.getJSONObject("riceField");
                JSONArray data = respone.getJSONArray("data");
                String id, image, title, price;

                for (int i=0; i < data.length(); i++){
                    JSONObject jsonObject = data.getJSONObject(i);
                    id = jsonObject.getString("id");
                    image = "@drawable/bg";
                    title = jsonObject.getString("title");
                    price = jsonObject.getString("harga");
                    productsList.add(new Products(id, image, title, price));
                }

                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkNetworkConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
