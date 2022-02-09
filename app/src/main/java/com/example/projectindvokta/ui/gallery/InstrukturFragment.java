package com.example.projectindvokta.ui.gallery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projectindvokta.DetailDataInstrukturActivity;
import com.example.projectindvokta.HttpHandler;
import com.example.projectindvokta.Konfigurasi;
import com.example.projectindvokta.R;
import com.example.projectindvokta.TambahDataInstrukturActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class InstrukturFragment extends Fragment implements AdapterView.OnItemClickListener {
    Button btn_tambah_ins;
    ListView list_view_ins;
    private String JSON_STRING;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_instruktur, container, false);
        list_view_ins = view.findViewById(R.id.list_view_ins);

        list_view_ins.setOnItemClickListener(this);
        btn_tambah_ins = view.findViewById(R.id.btn_tambah_ins);
        btn_tambah_ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TambahDataInstrukturActivity.class));
            }
        });


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
                loading = ProgressDialog.show(getActivity(), "Mengambil Data",
                        "Harap tunggu", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String hasil = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_INSTRUKTUR);
                return hasil;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                JSON_STRING = message;
                Log.d("Data JSON: ", JSON_STRING);

                displayAllDataInstruktur();
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void displayAllDataInstruktur()
    {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        try
        {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_INS_ARRAY);
            Log.d("DATA_JSON: ", JSON_STRING);

            for (int i = 0; i < result.length(); i++)
            {
                JSONObject object = result.getJSONObject(i);
                String id_ins = object.getString(Konfigurasi.TAG_JSON_ID_INS);
                String nama_ins = object.getString(Konfigurasi.TAG_JSON_NAMA_INS);

                HashMap<String, String> instruktur = new HashMap<>();
                instruktur.put(Konfigurasi.TAG_JSON_ID_INS, id_ins);
                instruktur.put(Konfigurasi.TAG_JSON_NAMA_INS, nama_ins);
                list.add(instruktur);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        ListAdapter adapterIns = new SimpleAdapter(getActivity(), list, R.layout.list_instruktur,
                new String[]{Konfigurasi.TAG_JSON_ID_INS, Konfigurasi.TAG_JSON_NAMA_INS},
                new int[]{R.id.txt_id_ins, R.id.txt_nama_ins});
        list_view_ins.setAdapter(adapterIns);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(getActivity(), DetailDataInstrukturActivity.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String insId = map.get(Konfigurasi.TAG_JSON_ID_INS).toString();
        intent.putExtra(Konfigurasi.INS_ID, insId);
        startActivity(intent);
        
    }
}