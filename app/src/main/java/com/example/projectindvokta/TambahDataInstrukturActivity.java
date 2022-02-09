package com.example.projectindvokta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectindvokta.ui.gallery.InstrukturFragment;

import java.util.HashMap;

public class TambahDataInstrukturActivity extends AppCompatActivity implements View.OnClickListener {
    EditText add_nama_ins, add_email_ins, add_hp_ins;
    Button btn_add_ins, btn_view_ins;
    TextView judul_tambah_ins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_instruktur);

        add_nama_ins = findViewById(R.id.add_nama_ins);
        add_email_ins = findViewById(R.id.add_email_ins);
        add_hp_ins = findViewById(R.id.add_hp_ins);
        judul_tambah_ins = findViewById(R.id.judul_tambah_ins);
        btn_add_ins = findViewById(R.id.btn_add_ins);
        btn_view_ins = findViewById(R.id.btn_view_ins);

        btn_add_ins.setOnClickListener(this);
        btn_view_ins.setOnClickListener(this);


    }

    @Override
    public void onClick(View view)
    {
        if(view == btn_add_ins)
        {
            simpanDataInstruktur();
        }
        else if (view == btn_view_ins)
        {
            startActivity(new Intent(TambahDataInstrukturActivity.this,
                    InstrukturFragment.class));
        }

    }

    private void simpanDataInstruktur()
    {
        final String nama_ins = add_nama_ins.getText().toString().trim();
        final String email_ins = add_email_ins.getText().toString().trim();
        final String hp_ins = add_hp_ins.getText().toString().trim();

        class SimpanDataInstruktur extends AsyncTask<Void, Void, String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataInstrukturActivity.this, "Memasukan Data",
                        "Harap Tunggu",false,false);
            }

            @Override
            protected String doInBackground(Void... voids)
            {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_INS_NAMA,nama_ins);
                params.put(Konfigurasi.KEY_INS_EMAIL,email_ins);
                params.put(Konfigurasi.KEY_INS_HP,hp_ins);
                HttpHandler handler = new HttpHandler();
                String hasil = handler.sendPostRequest(Konfigurasi.URL_ADD_INSTRUKTUR, params);
                return hasil;
            }

            @Override
            protected void onPostExecute(String message)
            {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(TambahDataInstrukturActivity.this, "Pesan: " + message,
                        Toast.LENGTH_SHORT).show();
                clearTextIns();
            }
        }
        SimpanDataInstruktur simpanDataInstruktur = new SimpanDataInstruktur();
        simpanDataInstruktur.execute();
    }

    private void clearTextIns()
    {
        add_nama_ins.setText("");
        add_email_ins.setText("");
        add_hp_ins.setText("");
        add_nama_ins.requestFocus();
    }
}