package com.example.projectindvokta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectindvokta.ui.home.PesertaFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;

public class DetailDataActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txt_dis_id_pst, txt_dis_nama_pst, txt_dis_email_pst, txt_dis_hp_pst,
    txt_dis_ins_pst;
    Button btn_edit_pst, btn_delete_pst;
    String id_pst;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        txt_dis_id_pst = findViewById(R.id.txt_dis_id_pst);
        txt_dis_nama_pst = findViewById(R.id.txt_dis_nama_pst);
        txt_dis_email_pst = findViewById(R.id.txt_dis_email_pst);
        txt_dis_hp_pst = findViewById(R.id.txt_dis_hp_pst);
        txt_dis_ins_pst = findViewById(R.id.txt_dis_ins_pst);
        btn_edit_pst = findViewById(R.id.btn_edit_pst);
        btn_delete_pst = findViewById(R.id.btn_delete_pst);

        Intent terimaIntent =getIntent();
        id_pst = terimaIntent.getStringExtra(Konfigurasi.PST_ID);
        txt_dis_id_pst.setText(id_pst);

        btn_edit_pst.setOnClickListener(this);
        btn_delete_pst.setOnClickListener(this);

        getJSON();

    }

    private void getJSON()
    {
        class GetJSON extends AsyncTask <Void, Void, String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailDataActivity.this, "Memuat Data",
                        "Harap Menunggu", false, false);
            }

            @Override
            protected String doInBackground(Void... voids)
            {
                HttpHandler handler = new HttpHandler();
                String hasil = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_PESERTA, id_pst);
                return hasil;
            }

            @Override
            protected void onPostExecute(String message)
            {
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();

    }

    private void displayDetailData(String json)
    {
        try
        {
            JSONObject jsonObject =new JSONObject(json);
            JSONArray result =jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject object = result.getJSONObject(0);

            String nama_pst =object.getString(Konfigurasi.TAG_JSON_NAMA_PST);
            String email_pst = object.getString(Konfigurasi.TAG_JSON_EMAIL_PST);
            String hp_pst = object.getString(Konfigurasi.TAG_JSON_HP_PST);
            String ins_pst = object.getString(Konfigurasi.TAG_JSON_INSTANSI_PST);

            txt_dis_nama_pst.setText(nama_pst);
            txt_dis_email_pst.setText(email_pst);
            txt_dis_hp_pst.setText(hp_pst);
            txt_dis_ins_pst.setText(ins_pst);

        }catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }


    @Override
    public void onClick(View view)
    {
        if(view == btn_edit_pst)
        {
            updateDataPeserta();
        }
        else if(view == btn_delete_pst)
        {
            confirmDeletePeserta();
        }

    }

    private void confirmDeletePeserta()
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Menghapus Data");
        builder.setMessage("Apakah anda yakin menghapus data?");
        builder.setIcon(getResources().getDrawable(android.R.drawable.ic_delete));
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            { deleteDataPeserta(); }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteDataPeserta()
    {
        class DeleteDataPeserta extends AsyncTask<Void, Void, String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailDataActivity.this, "Menghapus Data",
                        "Harap menunggu...",false,false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String hasil = handler.sendGetResponse(Konfigurasi.URL_DELETE_PESERTA,id_pst);
                return null;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(DetailDataActivity.this, "Hapus " + message,
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetailDataActivity.this, PesertaFragment.class));
            }
        }
        DeleteDataPeserta deleteDataPeserta = new DeleteDataPeserta();
        deleteDataPeserta.execute();
    }

    private void updateDataPeserta()
    {
        final String nama_pst = txt_dis_nama_pst.getText().toString().trim();
        final String email_pst = txt_dis_email_pst.getText().toString().trim();
        final String hp_pst = txt_dis_hp_pst.getText().toString().trim();
        final String ins_pst = txt_dis_ins_pst.getText().toString().trim();

        class UpdateDataPeserta extends AsyncTask <Void,Void,String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailDataActivity.this, "Memperbaharui Data",
                        "Harap tunggu...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) 
            {
                    HashMap<String, String> params = new HashMap<>();
                    params.put(Konfigurasi.KEY_PST_ID, id_pst);
                    params.put(Konfigurasi.KEY_PST_NAMA, nama_pst);
                    params.put(Konfigurasi.KEY_PST_EMAIL, email_pst);
                    params.put(Konfigurasi.KEY_PST_HP, hp_pst);
                    params.put(Konfigurasi.KEY_PST_INSTANSI, ins_pst);

                    HttpHandler handler = new HttpHandler();
                    String hasil = handler.sendPostRequest(Konfigurasi.URL_UPDATE_PESERTA,params);
                    return hasil;

            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(DetailDataActivity.this, "pesan: " + message,
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetailDataActivity.this, PesertaFragment.class));
            }
        }
        UpdateDataPeserta updateDataPeserta = new UpdateDataPeserta();
        updateDataPeserta.execute();
    }
}