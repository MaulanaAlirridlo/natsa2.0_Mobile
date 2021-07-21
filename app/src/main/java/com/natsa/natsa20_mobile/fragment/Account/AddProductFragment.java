package com.natsa.natsa20_mobile.fragment.Account;

import android.Manifest;
import android.annotation.SuppressLint;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
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
import com.natsa.natsa20_mobile.server.process.products.DeletePhoto;
import com.natsa.natsa20_mobile.server.process.products.GetRiceField;
import com.natsa.natsa20_mobile.server.process.products.UpdateProduct;
import com.natsa.natsa20_mobile.server.process.regions.GetRegions;
import com.natsa.natsa20_mobile.server.process.vestiges.GetVestiges;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddProductFragment extends Fragment {

    public static EditText judul, harga, luas, alamat, deskripsi
//            , maps
            ;
    public static Spinner sertifikasi, tipe, daerah, bekasSawah, irigasiSawah;
    LinearLayout addImage;
    Button addButton;
    RecyclerView newAttachmentListView, showAttachmentListView;
    String[] typeSelectionValueList, typeSelectionList, sertifikasiSelectionValueList,
            sertifikasiSelectionList;
    private ArrayList<AttachmentListData> newAttachmentList = new ArrayList<>();
    private ArrayList<AttachmentListData> showAttachmentList = new ArrayList<>();
    AttachmentListAdapter newAttachmentListAdapter, showAttachmentListAdapter;
    final Integer REQUEST_CODE = 1111;
    Boolean isEdit = false;
    ArrayAdapter<String> sertifikasiAdapter, sertifikasiValueAdapter, tipeAdapter, tipeValueAdapter,
            daerahAdapter, daerahValueAdapter, bekasSawahAdapter, bekasSawahValueAdapter,
            irigasiSawahAdapter, irigasiSawahValueAdapter;
    Integer idSawah;
    public static List<Integer> riceFieldDeletedIdList = new ArrayList<>();

    public AddProductFragment(Integer idSawah) {
        this.idSawah = idSawah;
        this.isEdit = idSawah != null;
    }

    public static List<Integer> getRiceFieldDeletedIdList() {
        return riceFieldDeletedIdList;
    }

    public static void addRiceFieldDeletedIdList(Integer riceFieldDeletedIdList) {
        AddProductFragment.riceFieldDeletedIdList.add(riceFieldDeletedIdList);
    }

    @SuppressLint("SetTextI18n")
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
        showAttachmentListView = view.findViewById(R.id.showAttachmentList);

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
        sertifikasiAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, sertifikasiSelectionList);
        sertifikasi.setAdapter(sertifikasiAdapter);
        tipeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, typeSelectionList);
        tipe.setAdapter(tipeAdapter);
        daerahAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, GetRegions.getRegionsStringList());
        daerah.setAdapter(daerahAdapter);
        bekasSawahAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, GetVestiges.getVestigesStringList());
        bekasSawah.setAdapter(bekasSawahAdapter);
        irigasiSawahAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, GetIrrigations.getIrrigationsStringList());
        irigasiSawah.setAdapter(irigasiSawahAdapter);

        generateNewAttachmentList(newAttachmentList);

        //set onclick listener
        addImage.setOnClickListener(v -> {
            openGalery();
        });

        new GetRegions().getRegionsFromApi(daerahAdapter);
        new GetVestiges().getVestigesFromApi(bekasSawahAdapter);
        new GetIrrigations().getIrrigationsFromApi(irigasiSawahAdapter);

        if (isEdit) {
            addButton.setText("Update");
            showAttachmentListView.setVisibility(RecyclerView.VISIBLE);
            generateShowAttachmentList(showAttachmentList);
            sertifikasiValueAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, sertifikasiSelectionValueList);
            tipeValueAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, typeSelectionValueList);
            daerahValueAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, GetRegions.getRegionsStringIdList());
            bekasSawahValueAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, GetVestiges.getVestigesStringIdList());
            bekasSawah.setAdapter(bekasSawahAdapter);
            irigasiSawahValueAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, GetIrrigations.getIrrigationsStringIdList());

            new GetRiceField().getRiceFieldFromApi(idSawah, showAttachmentList, showAttachmentListAdapter,
                    sertifikasiValueAdapter, tipeValueAdapter, daerahValueAdapter, bekasSawahValueAdapter,
                    irigasiSawahValueAdapter);

            addButton.setOnClickListener(v -> {
                updateData();
            });
        } else {
            addButton.setOnClickListener(v -> {
                addDataToServer();
            });
        }

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            new GetRegions().getRegionsFromApi(daerahAdapter);
            new GetVestiges().getVestigesFromApi(bekasSawahAdapter);
            new GetIrrigations().getIrrigationsFromApi(irigasiSawahAdapter);
            new Handler(Looper.getMainLooper()).postDelayed(() -> pullToRefresh.setRefreshing(false), 700);
            if (isEdit){
                new GetRiceField().getRiceFieldFromApi(idSawah, showAttachmentList, showAttachmentListAdapter,
                        sertifikasiValueAdapter, tipeValueAdapter, daerahValueAdapter, bekasSawahValueAdapter,
                        irigasiSawahValueAdapter);
            }
        });

        return view;
    }

    private void openGalery() {
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
                        newAttachmentListAdapter.notifyDataSetChanged();
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
            }
    );

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

    private void updateData() {
        List<MultipartBody.Part> productImagesParts = new ArrayList<>();

        for (Integer id : riceFieldDeletedIdList) {
            new DeletePhoto().deletePhoto(getActivity(), id);
        }

        for (int index = 0; index < newAttachmentList.size(); index++) {
            File file = new File(newAttachmentList.get(index).getImagePath());
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            productImagesParts.add(MultipartBody.Part.createFormData("photo[]",
                    file.getName(), body));
        }

        new UpdateProduct(getActivity()).addProductToServer(
                idSawah,
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
        newAttachmentListAdapter = new AttachmentListAdapter(getActivity(), newAttachmentList, false);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getContext());
        newAttachmentListView.setLayoutManager(MyLayoutManager);
        newAttachmentListView.setAdapter(newAttachmentListAdapter);
    }

    private void generateShowAttachmentList(ArrayList<AttachmentListData> showAttachmentList) {
        showAttachmentListView.setHasFixedSize(false);
        showAttachmentListAdapter = new AttachmentListAdapter(getActivity(), showAttachmentList, true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getContext());
        showAttachmentListView.setLayoutManager(MyLayoutManager);
        showAttachmentListView.setAdapter(showAttachmentListAdapter);
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
