package com.example.projectindvokta.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projectindvokta.DetailDataActivity;
import com.example.projectindvokta.HttpHandler;
import com.example.projectindvokta.Konfigurasi;
import com.example.projectindvokta.R;
import com.example.projectindvokta.databinding.FragmentPesertaBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PesertaFragment extends Fragment implements AdapterView.OnItemClickListener {
    private String JSON_STRING;

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

    private void getJSON()
    {
        class GetJSON extends AsyncTask<Void,Void, String>
        {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading =ProgressDialog.show(getActivity(), "Mengambil Data",
                        "Harap menunggu...", false, false);

            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String hasil = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_PESERTA);
                return hasil;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                JSON_STRING = message;
                Log.d("Data JSON: ", JSON_STRING);

                displayAllData();
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void displayAllData()
    {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        try
        {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            Log.d("DATA_JSON: ", JSON_STRING);

            for (int i = 0; i < result.length(); i++)
            {
                JSONObject object = result.getJSONObject(i);
                String id_pst = object.getString(Konfigurasi.TAG_JSON_ID_PST);
                String name_pst = object.getString(Konfigurasi.TAG_JSON_NAMA_PST);

                HashMap<String, String> peserta = new HashMap<>();
                peserta.put(Konfigurasi.TAG_JSON_ID_PST, id_pst);
                peserta.put(Konfigurasi.TAG_JSON_NAMA_PST, name_pst);
                // ubah format json menjadi array list
                list.add(peserta);
            }
        } catch (JSONException e)
            {
                e.printStackTrace();
            }
        ListAdapter listAdapter =new SimpleAdapter(getActivity(),list, R.layout.list_peserta,
                new String[]{Konfigurasi.TAG_JSON_ID_PST, Konfigurasi.TAG_JSON_NAMA_PST},
                new int[]{R.id.txt_id_pst, R.id.txt_nama_pst});

        list_view_pst.setAdapter(listAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(getActivity(), DetailDataActivity.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String pstId = map.get(Konfigurasi.TAG_JSON_ID_PST).toString();
        intent.putExtra(Konfigurasi.PST_ID, pstId);
        startActivity(intent);
    }

}