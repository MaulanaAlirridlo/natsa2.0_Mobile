package com.natsa.natsa20_mobile.server.process.vestiges;

import android.widget.ArrayAdapter;

import com.natsa.natsa20_mobile.adapter.ProductAdapter;
import com.natsa.natsa20_mobile.model.vestiges.Data;
import com.natsa.natsa20_mobile.model.vestiges.Vestige;
import com.natsa.natsa20_mobile.model.vestiges.Vestiges;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetVestiges {

    public static List<String> vestigesStringList = new ArrayList<>();
    public static List<Integer> vestigesIdList = new ArrayList<>();
    public static List<String> vestigesStringIdList = new ArrayList<>();
    public static Data vestigeData;

    public static List<String> getVestigesStringList() {
        return vestigesStringList;
    }
    public static List<Integer> getVestigesIdList() {
        return vestigesIdList;
    }

    public static List<String> getVestigesStringIdList() {
        return vestigesStringIdList;
    }

    public static Data getVestigeData() {
        return vestigeData;
    }

    public void setProducts(List<Data> vestigesDataList, ArrayAdapter<String> adapter) {
        vestigesStringList.clear();
        vestigesIdList.clear();
        vestigesStringIdList.clear();
        vestigesStringList.add("---");
        vestigesIdList.add(null);
        vestigesStringIdList.add(null);
        for (Iterator<Data> i = vestigesDataList.iterator(); i.hasNext();) {
            Data vestige = i.next();
            vestigesIdList.add(vestige.getId());
            vestigesStringIdList.add(String.valueOf(vestige.getId()));
            vestigesStringList.add(vestige.getVestige());
        }
        adapter.notifyDataSetChanged();
    }

    public void getVestigesFromApi(ArrayAdapter<String> adapter) {
        RetrofitBuilder.endPoint().getVestiges()
                .enqueue(new Callback<Vestiges>() {
                    @Override
                    public void onResponse(Call<Vestiges> call, Response<Vestiges> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            List<Data> data = response.body().getData();
                            setProducts(data, adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<Vestiges> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void getVestigesFromApiById(int id, ProductAdapter adapter) {
        RetrofitBuilder.endPoint().getVestigeById(id)
                .enqueue(new Callback<Vestige>() {
                    @Override
                    public void onResponse(Call<Vestige> call, Response<Vestige> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            vestigeData = response.body().getData();
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Vestige> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
