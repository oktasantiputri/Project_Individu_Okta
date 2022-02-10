package com.example.projectindvokta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectindvokta.ui.gallery.InstrukturFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class DetailDataInstrukturActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txt_dis_id_ins, txt_dis_nama_ins, txt_dis_email_ins, txt_dis_hp_ins;
    TextView page_edit_ins;
    Button btn_edit_ins, btn_del_ins;
    String id_ins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_instruktur);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Data Instruktur");

        txt_dis_id_ins = findViewById(R.id.txt_dis_id_ins);
        txt_dis_nama_ins = findViewById(R.id.txt_dis_nama_ins);
        txt_dis_email_ins = findViewById(R.id.txt_dis_email_ins);
        txt_dis_hp_ins = findViewById(R.id.txt_dis_hp_ins);
        btn_edit_ins = findViewById(R.id.btn_edit_ins);
        btn_del_ins = findViewById(R.id.btn_del_ins);
        page_edit_ins = findViewById(R.id.page_edit_ins);

        Intent terimaIntent =getIntent();
        id_ins = terimaIntent.getStringExtra(Konfigurasi.INS_ID);
        txt_dis_id_ins.setText(id_ins);

        btn_edit_ins.setOnClickListener(this);
        btn_del_ins.setOnClickListener(this);

        getJSON();
    }

    private void getJSON() {

        class GetJSON extends AsyncTask<Void,Void, String>
        {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailDataInstrukturActivity.this, "Memuat Data",
                        "Harap menunggu", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String hasil = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_INSTRUKTUR, id_ins);
                return hasil;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                displayDataInstruktur(message);
                Toast.makeText(DetailDataInstrukturActivity.this, "Update: " + message,
                        Toast.LENGTH_SHORT).show();

            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void displayDataInstruktur(String json)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_INS_ARRAY);
            JSONObject object = result.getJSONObject(0);

            String nama_ins = object.getString(Konfigurasi.TAG_JSON_NAMA_INS);
            String email_ins = object.getString(Konfigurasi.TAG_JSON_EMAIL_INS);
            String hp_ins = object.getString(Konfigurasi.TAG_JSON_HP_INS);

            txt_dis_nama_ins.setText(nama_ins);
            txt_dis_email_ins.setText(email_ins);
            txt_dis_hp_ins.setText(hp_ins);

        }
        catch (Exception e)
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
        if(view == btn_edit_ins)
        {
            updateDataInstruktur();
        }
        else if(view == btn_del_ins)
        {
            confirmDeleteDataIntruktur();
        }
    }

    private void updateDataInstruktur()
    {
        final String nama_ins = txt_dis_nama_ins.getText().toString().trim();
        final String email_ins = txt_dis_email_ins.getText().toString().trim();
        final String hp_ins = txt_dis_hp_ins.getText().toString().trim();

        class UpdateDatainstruktur extends AsyncTask<Void, Void, String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailDataInstrukturActivity.this, "Memperbaharui Data",
                        "Harap tunggu",false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_INS_ID, id_ins);
                params.put(Konfigurasi.KEY_INS_NAMA, nama_ins);
                params.put(Konfigurasi.KEY_INS_EMAIL, email_ins);
                params.put(Konfigurasi.KEY_INS_HP, hp_ins);
                HttpHandler handler = new HttpHandler();
                String hasil = handler.sendPostRequest(Konfigurasi.URL_UPDATE_INSTRUKTUR, params);
                return hasil;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(DetailDataInstrukturActivity.this, "Pesan: " + message,
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetailDataInstrukturActivity.this, InstrukturFragment.class));
            }
        }
        UpdateDatainstruktur updateDatainstruktur = new UpdateDatainstruktur();
        updateDatainstruktur.execute();
    }

    private void confirmDeleteDataIntruktur()
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
            { deleteDataInstruktur(); }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteDataInstruktur()
    {
        class DeleteDataInstruktur extends AsyncTask<Void, Void, String>
        {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailDataInstrukturActivity.this, "Menghapus Data",
                        "Harap tunggu",false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String hasil = handler.sendGetResponse(Konfigurasi.URL_DELETE_INSTRUKTUR, id_ins);
                return hasil;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(DetailDataInstrukturActivity.this, "Hapus: " + message,
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetailDataInstrukturActivity.this,InstrukturFragment.class));
            }
        }
        DeleteDataInstruktur deleteDataInstruktur = new DeleteDataInstruktur();
        deleteDataInstruktur.execute();
    }
}