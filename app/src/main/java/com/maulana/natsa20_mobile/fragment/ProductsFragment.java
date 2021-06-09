package com.maulana.natsa20_mobile.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maulana.natsa20_mobile.R;
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

public class ProductsFragment extends Fragment {

    private ArrayList<Products> productsList = new ArrayList<>();;
    static String DATA_JSON_STRING, data_json_string;
    ProductsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addData();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefresh.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.productsRecyclerView);
        adapter = new ProductsAdapter(getActivity(), productsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void onResume() {
        super.onResume();
        addData();
    }

    void addData(){
        getJSON();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                readDataFromServer();
            }
        }, 1000);
    }

    public void readDataFromServer() {
        if (checkNetworkConnection()) {
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

    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
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
            json_url = Server.getProducts;
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
}