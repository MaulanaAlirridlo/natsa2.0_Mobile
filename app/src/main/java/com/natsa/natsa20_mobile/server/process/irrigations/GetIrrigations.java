package com.natsa.natsa20_mobile.server.process.irrigations;

import android.widget.ArrayAdapter;

import com.natsa.natsa20_mobile.model.irrigations.Data;
import com.natsa.natsa20_mobile.model.irrigations.Irrigations;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetIrrigations {

    public static List<String> irrigationsStringList = new ArrayList<>();

    public static List<String> getIrrigationsStringList() {
        return irrigationsStringList;
    }

    public void setProducts(List<Data> irrigationsDataList, ArrayAdapter<String> adapter) {
        irrigationsStringList.clear();
        irrigationsStringList.add("---");
        for (Iterator<Data> i = irrigationsDataList.iterator(); i.hasNext();) {
            Data irrigation = i.next();
            irrigationsStringList.add(irrigation.getIrrigation());
        }
        adapter.notifyDataSetChanged();
    }

    public void getIrrigationsFromApi(ArrayAdapter<String> adapter) {
        RetrofitBuilder.endPoint().getIrrigations()
                .enqueue(new Callback<Irrigations>() {
                    @Override
                    public void onResponse(Call<Irrigations> call, Response<Irrigations> response) {
                        assert response.body() != null;
                        List<Data> data = response.body().getData();
                        setProducts(data, adapter);
                    }

                    @Override
                    public void onFailure(Call<Irrigations> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
