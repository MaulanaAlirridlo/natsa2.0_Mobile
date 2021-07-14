package com.natsa.natsa20_mobile.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.adapter.ProductsAdapter;
import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.server.process.Paging.products.ProductsViewModel;
import com.natsa.natsa20_mobile.server.process.irrigations.GetIrrigations;
import com.natsa.natsa20_mobile.server.process.vestiges.GetVestiges;

public class ProductsFragment extends Fragment {

    ProductsAdapter adapter;
    ProductsViewModel productsViewModel;
    TextView searchKeyword, noData;
    String keyword, tipe, sertifikasi, sort;
    Integer maxluas, minluas, maxharga, minharga, idBekasSawah, idIrigasi;
    ImageView showFilter;
    LinearLayout filter;
    Spinner typeSelection, sertifikasiSelection, bekasSawahSelection, irigasiSelection, sortSelection;
    EditText maxluasInput, minluasInput, maxhargaInput, minhargaInput;
    Button applyFilter;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.productsRecyclerView);
        adapter = new ProductsAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchKeyword = view.findViewById(R.id.search_keyword);
        noData = view.findViewById(R.id.no_data);
        showFilter = view.findViewById(R.id.show_filter);
        filter = view.findViewById(R.id.filter);
        keyword = getActivity().getIntent().getExtras().getString("keyword");
        typeSelection = view.findViewById(R.id.tipe_selection);
        sertifikasiSelection = view.findViewById(R.id.sertifikasi_selection);
        maxluasInput = view.findViewById(R.id.max_luas);
        minluasInput = view.findViewById(R.id.min_luas);
        maxhargaInput =view.findViewById(R.id.max_harga);
        minhargaInput = view.findViewById(R.id.min_harga);
        bekasSawahSelection = view.findViewById(R.id.bekas_sawah_selection);
        irigasiSelection = view.findViewById(R.id.jenis_irigasi_selection);
        sortSelection = view.findViewById(R.id.sort_selection);
        applyFilter = view.findViewById(R.id.apply_filter);

        String[] typeSelectionListValue = new String[]{
                "", "jual", "sewa"
        };

        String[] typeSelectionList = new String[]{
                "---", "Dijual", "Disewakan"
        };

        String[] sertifikasiSelectionListValue = new String[]{
                "", "shm", "sgb", "adat", "lainnya"
        };

        String[] sertifikasiSelectionList = new String[]{
                "---", "SHM", "SGB", "Adat", "Lainnya"
        };

        String[] sortSelectionListValue = new String[]{
                "", "title", "-title", "harga", "-harga", "luas", "-luas"
        };

        String[] sortSelectionList = new String[]{
                "Urut Berdasarkan", "Judul Up", "Judul Down", "Harga Up", "Harga Down", "Luas Up", "Luas Down"
        };

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, typeSelectionList);

        ArrayAdapter<String> sertifikasiAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, sertifikasiSelectionList);

        ArrayAdapter<String> bekasSawahAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, GetVestiges.getVestigesStringList());

        ArrayAdapter<String> irigasiSawahAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, GetIrrigations.getIrrigationsStringList());

        ArrayAdapter<String> sortAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, sortSelectionList);

        typeSelection.setAdapter(typeAdapter);
        sertifikasiSelection.setAdapter(sertifikasiAdapter);
        new GetVestiges().getVestigesFromApi(bekasSawahAdapter);
        bekasSawahSelection.setAdapter(bekasSawahAdapter);
        new GetIrrigations().getIrrigationsFromApi(irigasiSawahAdapter);
        irigasiSelection.setAdapter(irigasiSawahAdapter);
        sortSelection.setAdapter(sortAdapter);


        if (keyword == null) {
            searchKeyword.setVisibility(TextView.GONE);
        } else {
            searchKeyword.setText("Menampilakan hasil dari '" + keyword + "'");
        }

        productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);
        productsViewModel.customConstructor(keyword, tipe, sertifikasi,
                maxluas, minluas, maxharga, minharga, idBekasSawah, idIrigasi, sort, noData);

        productsViewModel.ProductsViewModel().observe(getActivity(), new Observer<PagedList<Data>>() {
            @Override
            public void onChanged(@Nullable PagedList<Data> items) {
                adapter.submitList(items);
            }
        });

        recyclerView.setAdapter(adapter);

        showFilter.setOnClickListener(v -> {
            if (filter.getVisibility() == LinearLayout.GONE) {
                filter.setVisibility(LinearLayout.VISIBLE);
            } else {
                filter.setVisibility(LinearLayout.GONE);
            }
        });

        sortSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sort = sortSelectionListValue[position];
                productsViewModel.customConstructor(keyword, tipe, sertifikasi,
                        maxluas, minluas, maxharga, minharga, idBekasSawah, idIrigasi, sort, noData);

                productsViewModel.ProductsViewModel().observe(getActivity(), new Observer<PagedList<Data>>() {
                    @Override
                    public void onChanged(@Nullable PagedList<Data> items) {
                        adapter.submitList(items);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        applyFilter.setOnClickListener(v -> {

            tipe = typeSelectionListValue[typeSelection.getSelectedItemPosition()];
            sertifikasi = sertifikasiSelectionListValue[sertifikasiSelection.getSelectedItemPosition()];
            maxluas = maxluasInput.getText().toString().equals("") ? null :
                    Integer.parseInt(maxluasInput.getText().toString());
            minluas = minluasInput.getText().toString().equals("") ? null :
                    Integer.parseInt(minluasInput.getText().toString());
            maxharga = maxhargaInput.getText().toString().equals("") ? null :
                    Integer.parseInt(maxhargaInput.getText().toString());
            minharga = minhargaInput.getText().toString().equals("") ? null :
                    Integer.parseInt(minhargaInput.getText().toString());
            idBekasSawah = GetVestiges.getVestigesIdList().get(bekasSawahSelection.getSelectedItemPosition());
            idIrigasi = GetIrrigations.getIrrigationsIdList().get(irigasiSelection.getSelectedItemPosition());

            productsViewModel.customConstructor(keyword, tipe, sertifikasi,
                    maxluas, minluas, maxharga, minharga, idBekasSawah, idIrigasi, sort, noData);

            productsViewModel.ProductsViewModel().observe(getActivity(), new Observer<PagedList<Data>>() {
                @Override
                public void onChanged(@Nullable PagedList<Data> items) {
                    adapter.submitList(items);
                }
            });

            filter.setVisibility(LinearLayout.GONE);
        });

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            productsViewModel.refresh();
            new Handler(Looper.getMainLooper()).postDelayed(() -> pullToRefresh.setRefreshing(false), 700);
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        productsViewModel.refresh();
    }
}