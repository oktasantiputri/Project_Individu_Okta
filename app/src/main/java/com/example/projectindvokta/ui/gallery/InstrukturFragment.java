package com.example.projectindvokta.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projectindvokta.R;
import com.example.projectindvokta.TambahDataPesertaActivity;
//import com.example.projectindvokta.databinding.FragmentGalleryBinding;

public class InstrukturFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView list_view_pst;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_peserta, container, false);
        list_view_pst = view.findViewById(R.id.list_view_peserta);

        list_view_pst.setOnItemClickListener(this);
        
        getJSON();
        return view;
    }

    private void getJSON() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        
    }
}