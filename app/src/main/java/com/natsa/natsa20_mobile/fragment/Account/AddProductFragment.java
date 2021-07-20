package com.natsa.natsa20_mobile.fragment.Account;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
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
import com.natsa.natsa20_mobile.server.process.products.AddProduct;
import com.natsa.natsa20_mobile.server.process.regions.GetRegions;
import com.natsa.natsa20_mobile.server.process.vestiges.GetVestiges;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddProductFragment extends Fragment {

    EditText judul, harga, luas, alamat, deskripsi
//            , maps
            ;
    Spinner sertifikasi, tipe, daerah, bekasSawah, irigasiSawah;
    LinearLayout addImage;
    Button addButton;
    RecyclerView newAttachmentListView;
    String[] typeSelectionValueList, typeSelectionList, sertifikasiSelectionValueList,
            sertifikasiSelectionList;
    private ArrayList<AttachmentListData> newAttachmentList = new ArrayList<>();
    AttachmentListAdapter attachmentListAdapter;
    final Integer REQUEST_CODE = 1111;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.form_product, container, false);

        //init
        judul = view.findViewById(R.id.judul);
        harga = view.findViewById(R.id.harga);
        luas = view.findViewById(R.id.luas);
        alamat = view.findViewById(R.id.alamat);
        deskripsi = view.findViewById(R.id.deskripsi);
//        maps = view.findViewById(R.id.maps);
        sertifikasi = view.findViewById(R.id.sertifikasi);
        tipe = view.findViewById(R.id.tipe);
        daerah = view.findViewById(R.id.daerah);
        bekasSawah = view.findViewById(R.id.bekas_sawah);
        irigasiSawah = view.findViewById(R.id.irigasi_sawah);
        addImage = view.findViewById(R.id.add_image);
        addButton = view.findViewById(R.id.add_button);
        newAttachmentListView = view.findViewById(R.id.newAttachmentList);

        //isi value spinner option

        typeSelectionValueList = new String[]{
                "", "jual", "sewa"
        };

        typeSelectionList = new String[]{
                "---", "Dijual", "Disewakan"
        };

        sertifikasiSelectionValueList = new String[]{
                "", "shm", "sgb", "adat", "lainnya"
        };

        sertifikasiSelectionList = new String[]{
                "---", "SHM", "SGB", "Adat", "Lainnya"
        };

        //set spinner option
        ArrayAdapter<String> sertifikasiAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, sertifikasiSelectionList);
        sertifikasi.setAdapter(sertifikasiAdapter);
        ArrayAdapter<String> tipeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, typeSelectionList);
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

        generateNewAttachmentList(newAttachmentList);

        //set onclick listener
        addImage.setOnClickListener(v -> {
            openGalery();
        });

        addButton.setOnClickListener(v -> {
            addDataToServer();
        });

        return view;
    }

    private void openGalery(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_PICK);
                openGaleryActivityResultLauncher.launch(Intent.createChooser(intent, "Select File"));
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_PICK);
            openGaleryActivityResultLauncher.launch(Intent.createChooser(intent, "Select File"));
        }
    }

    ActivityResultLauncher<Intent> openGaleryActivityResultLauncher = registerForActivityResult(
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
                        attachmentListAdapter.notifyDataSetChanged();
                    }

                }

                private void setData(Uri returnUri) {
                    Cursor returnCursor = getActivity().getContentResolver().query(returnUri, null, null, null, null);
                    int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    returnCursor.moveToFirst();
                    AttachmentListData attachmentListData = new AttachmentListData();
                    attachmentListData.setImageName(returnCursor.getString(nameIndex));
                    attachmentListData.setImageUri(returnUri.toString());
                    attachmentListData.setImagePath(getRealPathFromURI(returnUri));
                    attachmentListData.setRealUri(returnUri);
                    newAttachmentList.add(attachmentListData);
                }
            });

    private void addDataToServer() {
        List<MultipartBody.Part> productImagesParts = new ArrayList<>();

        for (int index = 0; index < newAttachmentList.size(); index++) {
            File file = new File(newAttachmentList.get(index).getImagePath());
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            productImagesParts.add(MultipartBody.Part.createFormData("photo[]",
                    file.getName(), body));
        }

        new AddProduct(getActivity()).addProductToServer(
                RequestBody.create(MediaType.parse("text/plain"), judul.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), harga.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), luas.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), alamat.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), "NULL"),
                RequestBody.create(MediaType.parse("text/plain"), deskripsi.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), sertifikasiSelectionValueList[sertifikasi.getSelectedItemPosition()]),
                RequestBody.create(MediaType.parse("text/plain"), typeSelectionValueList[tipe.getSelectedItemPosition()]),
                RequestBody.create(MediaType.parse("text/plain"), GetVestiges.getVestigesIdList().get(bekasSawah.getSelectedItemPosition()) == null
                        ? "" : GetVestiges.getVestigesIdList().get(bekasSawah.getSelectedItemPosition()).toString()),
                RequestBody.create(MediaType.parse("text/plain"), GetIrrigations.getIrrigationsIdList().get(irigasiSawah.getSelectedItemPosition()) == null
                        ? "" : GetIrrigations.getIrrigationsIdList().get(irigasiSawah.getSelectedItemPosition()).toString()),
                RequestBody.create(MediaType.parse("text/plain"), GetRegions.getRegionsIdList().get(daerah.getSelectedItemPosition()) == null
                        ? "" : GetRegions.getRegionsIdList().get(daerah.getSelectedItemPosition()).toString()),
                productImagesParts
        );

    }

    private void generateNewAttachmentList(ArrayList<AttachmentListData> newAttachmentList) {
        newAttachmentListView.setHasFixedSize(false);
        attachmentListAdapter = new AttachmentListAdapter(getActivity(), newAttachmentList);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getContext());
        newAttachmentListView.setLayoutManager(MyLayoutManager);
        newAttachmentListView.setAdapter(attachmentListAdapter);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            return uri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
}
