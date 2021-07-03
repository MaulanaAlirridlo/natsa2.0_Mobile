package com.natsa.natsa20_mobile.fragment.Account;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.adapter.AttachmentListAdapter;
import com.natsa.natsa20_mobile.model.AttachmentListData;
import com.natsa.natsa20_mobile.server.process.irrigations.GetIrrigations;
import com.natsa.natsa20_mobile.server.process.regions.GetRegions;
import com.natsa.natsa20_mobile.server.process.vestiges.GetVestiges;

import java.util.ArrayList;

public class AddProductFragment extends Fragment {

    EditText judul, harga, luas, alamat, deskripsi, maps;
    Spinner sertifikasi, tipe, daerah, bekasSawah, irigasiSawah;
    LinearLayout addImage;
    Button addButton;
    private String[] sertifikasiList, tipeList;
    RecyclerView newAttachmentListView;
    private ArrayList<AttachmentListData> newAttachmentList = new ArrayList<>();
    AttachmentListAdapter attachmentListAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.form_product, container, false);

        //init
        judul = view.findViewById(R.id.judul);
        harga = view.findViewById(R.id.harga);
        luas = view.findViewById(R.id.luas);
        alamat = view.findViewById(R.id.alamat);
        deskripsi = view.findViewById(R.id.desktipsi);
        maps = view.findViewById(R.id.maps);
        sertifikasi = view.findViewById(R.id.sertifikasi);
        tipe = view.findViewById(R.id.tipe);
        daerah = view.findViewById(R.id.daerah);
        bekasSawah = view.findViewById(R.id.bekas_sawah);
        irigasiSawah = view.findViewById(R.id.irigasi_sawah);
        addImage = view.findViewById(R.id.add_image);
        addButton = view.findViewById(R.id.add_button);
        newAttachmentListView = view.findViewById(R.id.newAttachmentList);

        //isi value spinner option
        sertifikasiList = new String[]{
                "---", "SHM", "SGB", "Adat", "Lainnya"
        };
        tipeList = new String[]{
                "---", "Jual", "Sewa"
        };

        //set spinner option
        ArrayAdapter<String> sertifikasiAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, sertifikasiList);
        sertifikasi.setAdapter(sertifikasiAdapter);
        ArrayAdapter<String> tipeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, tipeList);
        tipe.setAdapter(tipeAdapter);
        ArrayAdapter<String> daerahAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, GetRegions.getRegionsStringList());
        //get regions from api
        new GetRegions().getRegionsFromApi(daerahAdapter);
        daerah.setAdapter(daerahAdapter);
        ArrayAdapter<String> bekasSawahAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, GetVestiges.getVestigesStringList());
        //get vestiges from api
        new GetVestiges().getVestigesFromApi(bekasSawahAdapter);
        bekasSawah.setAdapter(bekasSawahAdapter);
        ArrayAdapter<String> irigasiSawahAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, GetIrrigations.getIrrigationsStringList());
        //get irrigations from api
        new GetIrrigations().getIrrigationsFromApi(irigasiSawahAdapter);
        irigasiSawah.setAdapter(irigasiSawahAdapter);

        //set onclick listener
        addImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            activityResultLauncher.launch(Intent.createChooser(intent, "Select File"));
        });

        addButton.setOnClickListener(v -> {
            //
        });

        return view;
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        if (data.getClipData() != null) {
                            int count = data.getClipData().getItemCount();
                            for (int i = 0; i < count; i++) {
                                Uri returnUri = data.getClipData().getItemAt(i).getUri();
                                setData(returnUri);
                            }

                        } else if (data.getData() != null) {
                            Uri returnUri = data.getData();
                            setData(returnUri);
                        }
                        generateNewAttachmentList(newAttachmentList);

                    }

                }

                private void generateNewAttachmentList(ArrayList<AttachmentListData> newAttachmentList) {
                    newAttachmentListView.setHasFixedSize(false);
                    attachmentListAdapter = new AttachmentListAdapter(newAttachmentList);
                    LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getContext());
                    newAttachmentListView.setLayoutManager(MyLayoutManager);
                    newAttachmentListView.setAdapter(attachmentListAdapter);
                }

                private void setData(Uri returnUri){
                    Cursor returnCursor = getActivity().getContentResolver().query(returnUri, null, null, null, null);
                    int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    returnCursor.moveToFirst();
                    AttachmentListData attachmentListData = new AttachmentListData();
                    attachmentListData.setImageName(returnCursor.getString(nameIndex));
                    attachmentListData.setImageUri(returnUri.toString());
                    Log.d("TAG", "setData: "+returnUri.toString());
                    newAttachmentList.add(attachmentListData);
                }
            });




}