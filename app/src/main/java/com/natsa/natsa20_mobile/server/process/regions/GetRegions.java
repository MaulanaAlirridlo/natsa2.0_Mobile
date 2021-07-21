package com.natsa.natsa20_mobile.server.process.regions;

import android.widget.ArrayAdapter;

import com.natsa.natsa20_mobile.adapter.ProductAdapter;
import com.natsa.natsa20_mobile.model.regions.Data;
import com.natsa.natsa20_mobile.model.regions.Region;
import com.natsa.natsa20_mobile.model.regions.Regions;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRegions {

    public static List<Integer> regionsIdList = new ArrayList<>();
    public static List<String> regionsStringIdList = new ArrayList<>();
    public static List<String> regionsStringList = new ArrayList<>();
    public static Data regionData;

    public static List<Integer> getRegionsIdList() {
        return regionsIdList;
    }

    public static List<String> getRegionsStringIdList() {
        return regionsStringIdList;
    }

    public static List<String> getRegionsStringList() {
        return regionsStringList;
    }

    public static Data getRegionData() {
        return regionData;
    }

    public void setProducts(List<Data> regionsDataList, ArrayAdapter<String> adapter) {
        regionsIdList.clear();
        regionsStringIdList.clear();
        regionsStringList.clear();
        regionsIdList.add(null);
        regionsStringIdList.add(null);
        regionsStringList.add("---");
        for (Iterator<Data> i = regionsDataList.iterator(); i.hasNext();) {
            Data region = i.next();
            regionsIdList.add(region.getId());
            regionsStringIdList.add(String.valueOf(region.getId()));
            regionsStringList.add(region.getProvinsi()+", "+region.getKabupaten());
        }
        adapter.notifyDataSetChanged();
    }

    public void getRegionsFromApi(ArrayAdapter<String> adapter) {
        RetrofitBuilder.endPoint().getRegions()
                .enqueue(new Callback<Regions>() {
                    @Override
                    public void onResponse(Call<Regions> call, Response<Regions> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            List<Data> data = response.body().getData();
                            setProducts(data, adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<Regions> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void getRegionsFromApiByApi(int id, ProductAdapter adapter) {
        RetrofitBuilder.endPoint().getRegionById(id)
                .enqueue(new Callback<Region>() {
                    @Override
                    public void onResponse(Call<Region> call, Response<Region> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            regionData = response.body().getData();
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Region> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
