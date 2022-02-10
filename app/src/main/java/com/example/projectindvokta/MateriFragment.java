package com.example.projectindvokta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MateriFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView list_view_materi;
    Button btn_tambah_materi;
    String JSON_STRING;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_instruktur, container, false);
        list_view_materi = view.findViewById(R.id.list_view_ins);
        btn_tambah_materi = view.findViewById(R.id.btn_tambah_materi);

        btn_tambah_materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), TambahDataMateriActivity.class);
                startActivity(intent);
            }
        });
        list_view_materi.setOnItemClickListener(this);
        getJSON();
        return view;
    }

    private void getJSON()
    {
        class GetJSON extends AsyncTask<Void, Void, String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Memuat data",
                        "Harap menunggu",false,false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String hasil = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_MATERI);
                return hasil;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Log.d("Data: ",JSON_STRING);
                displayAllDataMateri();
            }
        }
    }

    private void displayAllDataMateri()
    {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<>();

        try
        {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_MAT_ARRAY);
            Log.d("Data: ",JSON_STRING);

            for(int i = 0; i < result.length(); i++)
            {
                JSONObject object = result.getJSONObject(i);
                String id_mat = object.getString(Konfigurasi.TAG_JSON_ID_MAT);
                String nama_mat = object.getString(Konfigurasi.TAG_JSON_NAMA_MAT);

                HashMap<String, String> materi = new HashMap<>();
                materi.put(Konfigurasi.TAG_JSON_ID_MAT, id_mat);
                materi.put(Konfigurasi.TAG_JSON_NAMA_MAT, nama_mat);
                list.add(materi);

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        ListAdapter adapterMat = new SimpleAdapter(getActivity(), list, R.layout.list_materi,
                new String[]{Konfigurasi.TAG_JSON_ID_MAT, Konfigurasi.TAG_JSON_NAMA_MAT},
                new int[]{R.id.txt_dis_nama_materi});
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(getActivity(), DetailDataMateriActivity.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String insId = map.get(Konfigurasi.TAG_JSON_ID_MAT).toString();
        intent.putExtra(Konfigurasi.MAT_ID, insId);
        startActivity(intent);

    }
}



