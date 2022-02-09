package com.example.projectindvokta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectindvokta.ui.home.PesertaFragment;

import org.w3c.dom.Text;

import java.util.HashMap;

public class TambahDataPesertaActivity extends AppCompatActivity implements View.OnClickListener {
    EditText add_nama_pst, add_email_pst, add_hp_pst;
    EditText add_nama_ins_pst;
    Button btn_simpan_pst, btn_lihat_pst;
    TextView judul_tambah_pst;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_peserta);


        add_nama_pst = findViewById(R.id.add_nama_pst);
        add_email_pst = findViewById(R.id.add_email_pst);
        add_hp_pst = findViewById(R.id.add_hp_pst);
        btn_simpan_pst = findViewById(R.id.btn_simpan_pst);
        btn_lihat_pst = findViewById(R.id.btn_lihat_pst);
        add_nama_ins_pst = findViewById(R.id.add_nama_ins_pst);
        judul_tambah_pst = findViewById(R.id.judul_tambah_pst);

        btn_lihat_pst.setOnClickListener(this);
        btn_simpan_pst.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        if (v == btn_lihat_pst)
        {
            startActivity(new Intent(TambahDataPesertaActivity.this,
                    PesertaFragment.class));
        } else if(v == btn_simpan_pst)
        {
            simpanDataPeserta();
        }

    }

    private void simpanDataPeserta()
    {
        final String nama_pst = add_nama_pst.getText().toString().trim();
        final String email_pst = add_email_pst.getText().toString().trim();
        final String hp_pst = add_hp_pst.getText().toString().trim();
        final String ins_pst = add_nama_ins_pst.getText().toString().trim();

        class SimpanDataPeserta extends AsyncTask<Void, Void, String>
        {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataPesertaActivity.this,
                        "Menyimpan Data", "Harap Tunggu",
                        false,false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_PST_NAMA, nama_pst);
                params.put(Konfigurasi.KEY_PST_EMAIL, email_pst);
                params.put(Konfigurasi.KEY_PST_HP, hp_pst);
                params.put(Konfigurasi.KEY_PST_INSTANSI,ins_pst);
                HttpHandler handler = new HttpHandler();
                String hasil = handler.sendPostRequest(Konfigurasi.URL_ADD_PESERTA, params);
                return hasil;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(TambahDataPesertaActivity.this, "Pesan: " + message,
                        Toast.LENGTH_SHORT).show();
                clearText();
            }
        }
        SimpanDataPeserta simpanDataPeserta = new SimpanDataPeserta();
        simpanDataPeserta.execute();
    }

    private void clearText()
    {
        add_nama_pst.setText("");
        add_email_pst.setText("");
        add_hp_pst.setText("");
        add_nama_ins_pst.setText("");
        add_nama_pst.requestFocus();
    }
}