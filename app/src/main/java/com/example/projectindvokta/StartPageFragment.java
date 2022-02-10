package com.example.projectindvokta;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projectindvokta.ui.home.PesertaFragment;


public class StartPageFragment extends Fragment {
    Button btn_to_peserta, btn_to_instruktur, btn_to_materi, btn_to_kelas;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_to_peserta = btn_to_peserta.findViewById(R.id.btn_to_peserta);
        btn_to_instruktur = btn_to_instruktur.findViewById(R.id.btn_to_instruktur);
        btn_to_materi = btn_to_materi.findViewById(R.id.btn_to_materi);
        btn_to_kelas = btn_to_kelas.findViewById(R.id.btn_to_kelas);

        btn_to_peserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PesertaFragment.class);
                startActivity(intent);
            }
        });

    }


}