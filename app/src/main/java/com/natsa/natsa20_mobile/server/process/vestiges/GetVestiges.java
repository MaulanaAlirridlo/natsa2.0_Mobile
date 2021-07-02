package com.natsa.natsa20_mobile.server.process.vestiges;

import android.widget.ArrayAdapter;

import com.natsa.natsa20_mobile.model.vestiges.Data;
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

    public static List<String> getVestigesStringList() {
        return vestigesStringList;
    }

    public void setProducts(List<Data> vestigesDataList, ArrayAdapter<String> adapter) {
        vestigesStringList.clear();
        vestigesStringList.add("---");
        for (Iterator<Data> i = vestigesDataList.iterator(); i.hasNext();) {
            Data vestige = i.next();
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
}
