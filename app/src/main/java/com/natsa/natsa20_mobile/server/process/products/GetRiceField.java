package com.natsa.natsa20_mobile.server.process.products;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.natsa.natsa20_mobile.adapter.AttachmentListAdapter;
import com.natsa.natsa20_mobile.fragment.Account.AddProductFragment;
import com.natsa.natsa20_mobile.model.AttachmentListData;
import com.natsa.natsa20_mobile.model.products.get_ricefield.GetRiceFieldResponse;
import com.natsa.natsa20_mobile.model.products.get_ricefield.RiceField;
import com.natsa.natsa20_mobile.model.products.product.Photo;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;
import com.natsa.natsa20_mobile.server.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRiceField {
    private final static List<RiceField> riceFIeldData = new ArrayList<>();

    public static List<RiceField> getRiceFIeldData() {
        return riceFIeldData;
    }

    public void getRiceFieldFromApi(Integer id, List<AttachmentListData> attachmentListData,
                                    AttachmentListAdapter attachmentListAdapter,
                                    ArrayAdapter<String> sertifikasiValueAdapter,
                                    ArrayAdapter<String> tipeValueAdapter,
                                    ArrayAdapter<String> daerahValueAdapter,
                                    ArrayAdapter<String> bekasSawahValueAdapter,
                                    ArrayAdapter<String> irigasiSawahValueAdapter) {
        RetrofitBuilder.endPoint().showRiceField(id)
                .enqueue(new Callback<GetRiceFieldResponse>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<GetRiceFieldResponse> call, Response<GetRiceFieldResponse> response) {
                        if (response.isSuccessful()) {
                            RiceField riceField = response.body().getRiceField();
                            AddProductFragment.judul.setText(riceField.getTitle());
                            AddProductFragment.harga.setText(riceField.getHarga().toString());
                            AddProductFragment.luas.setText(riceField.getLuas().toString());
                            AddProductFragment.alamat.setText(riceField.getAlamat());
                            AddProductFragment.deskripsi.setText(riceField.getDeskripsi());
                            AddProductFragment.sertifikasi.setSelection(sertifikasiValueAdapter
                                    .getPosition(riceField.getSertifikasi()));
                            AddProductFragment.tipe.setSelection(tipeValueAdapter
                                    .getPosition(riceField.getTipe()));
                            AddProductFragment.daerah.setSelection(daerahValueAdapter
                                    .getPosition(riceField.getRegion_id().toString()));
                            AddProductFragment.bekasSawah.setSelection(bekasSawahValueAdapter
                                    .getPosition(riceField.getVestige_id().toString()));
                            AddProductFragment.irigasiSawah.setSelection(irigasiSawahValueAdapter
                                    .getPosition(riceField.getIrrigation_id().toString()));
                            attachmentListData.clear();
                            Integer i = 1;
                            for (Photo photo : riceField.getPhotos()) {
                                AttachmentListData attachmentData = new AttachmentListData();
                                attachmentData.setImageName(i.toString());
                                attachmentData.setImageUri(Server.storage + photo.getPhoto_path());
                                attachmentListData.add(attachmentData);
                                i++;
                            }
                            attachmentListAdapter.notifyDataSetChanged();
                        }
                        Log.d("TAG", "onResponse: "+response.message());
                    }

                    @Override
                    public void onFailure(Call<GetRiceFieldResponse> call, Throwable t) {

                    }
                });
    }
}
