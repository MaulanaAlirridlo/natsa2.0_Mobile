package com.maulana.natsa20_mobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FaqFragment extends Fragment {

    LinearLayout pertanyaan1, pertanyaan2;
    TextView jawaban1, jawaban2, plusPertanyaan1, plusPertanyaan2;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_faq, container, false);
        pertanyaan1 = view.findViewById(R.id.pertanyaan1);
        pertanyaan2 = view.findViewById(R.id.pertanyaan2);
        plusPertanyaan1 = view.findViewById(R.id.plusPertanyaan1);
        plusPertanyaan2 = view.findViewById(R.id.plusPertanyaan2);
        jawaban1 = view.findViewById(R.id.jawaban1);
        jawaban2 = view.findViewById(R.id.jawaban2);

        pertanyaan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jawaban1.getVisibility() == TextView.GONE) {
                    jawaban1.setVisibility(TextView.VISIBLE);
                    plusPertanyaan1.setText("-");
                } else {
                    jawaban1.setVisibility(TextView.GONE);
                    plusPertanyaan1.setText("+");
                }
            }
        });

        pertanyaan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jawaban2.getVisibility() == TextView.GONE) {
                    jawaban2.setVisibility(TextView.VISIBLE);
                    plusPertanyaan2.setText("-");
                } else {
                    jawaban2.setVisibility(TextView.GONE);
                    plusPertanyaan2.setText("+");
                }
            }
        });

        return view;
    }
}