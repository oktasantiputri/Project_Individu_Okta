package com.example.projectindvokta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TambahDataPesertaActivity extends AppCompatActivity {
    EditText add_id_pst, add_nama_pst, add_email_pst, add_hp_pst, add_nama_ins_pst;
    RadioButton radbut_personal, radbut_instansi;
    RadioGroup rg_ins_pst;
    Button btn_simpan_pst;
    TextView add_ins_pst;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_peserta);

        add_id_pst = findViewById(R.id.add_id_pst);
        add_nama_pst = findViewById(R.id.add_nama_pst);
        add_email_pst = findViewById(R.id.add_email_pst);
        add_hp_pst = findViewById(R.id.add_hp_pst);
        add_nama_ins_pst = findViewById(R.id.add_nama_ins_pst);
        radbut_personal = findViewById(R.id.radbut_personal);
        radbut_instansi = findViewById(R.id.radbut_instansi);
        btn_simpan_pst = findViewById(R.id.btn_simpan_pst);
        add_ins_pst = findViewById(R.id.add_ins_pst);
        rg_ins_pst = findViewById(R.id.rgInsPst);



    }


}