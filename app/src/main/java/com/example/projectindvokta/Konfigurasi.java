package com.example.projectindvokta;

public class Konfigurasi
{
    // url dimana web API berada
    //PESERTA
    public static final String URL_GET_ALL_PESERTA = "http://192.168.100.7/inixtraining/peserta/tr_datas_peserta.php";
    public static final String URL_GET_DETAIL_PESERTA = "http://192.168.100.7/inixtraining/peserta/tr_detail_peserta.php?id_pst=";
    public static final String URL_ADD_PESERTA = "http://192.168.100.7/inixtraining/peserta/tr_add_peserta.php";
    public static final String URL_UPDATE_PESERTA = "http://192.168.100.7/inixtraining/peserta/tr_update_peserta.php?id_pst=";
    public static final String URL_DELETE_PESERTA = "http://192.168.100.7/inixtraining/peserta/tr_delete_peserta.php?id_pst=";

    //INSTRUKTUR
    public static final String URL_GET_ALL_INSTRUKTUR = "http://192.168.100.7/inixtraining/instruktur/tr_datas_instruktur.php";
    public static final String URL_GET_DETAIL_INSTRUKTUR = "http://192.168.100.7/inixtraining/instruktur/tr_detail_instruktur.php?id_ins=";
    public static final String URL_ADD_INSTRUKTUR = "http://192.168.100.7/inixtraining/instruktur/tr_add_instruktur.php";
    public static final String URL_UPDATE_INSTRUKTUR = "http://192.168.100.7/inixtraining/instruktur/tr_update_instruktur.php?id_ins=";
    public static final String URL_DELETE_INSTRUKTUR = "http://192.168.100.7/inixtraining/instruktur/tr_delete_instruktur.php?id_ins=";

    // key and value JSON yang muncul di browser
    //PESERTA
    public static final String KEY_PST_ID = "id_pst";
    public static final String KEY_PST_NAMA = "nama_pst";
    public static final String KEY_PST_EMAIL = "email_pst";
    public static final String KEY_PST_HP = "hp_pst";
    public static final String KEY_PST_INSTANSI = "instansi_pst";

    //INSTRUKTUR
    public static final String KEY_INS_ID = "id_ins";
    public static final String KEY_INS_NAMA = "nama_ins";
    public static final String KEY_INS_EMAIL = "email_ins";
    public static final String KEY_INS_HP = "hp_ins";


    // flag JSON
    //PESERTA
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_JSON_ID_PST = "id_pst";
    public static final String TAG_JSON_NAMA_PST = "nama_pst";
    public static final String TAG_JSON_EMAIL_PST = "email_pst";
    public static final String TAG_JSON_HP_PST = "hp_pst";
    public static final String TAG_JSON_INS_PST = "instansi_pst";

    //INSTRUKTUR
    public static final String TAG_JSON_INS_ARRAY = "result";
    public static final String TAG_JSON_ID_INS = "id_ins";
    public static final String TAG_JSON_NAMA_INS = "nama_ins";
    public static final String TAG_JSON_EMAIL_INS = "email_ins";
    public static final String TAG_JSON_HP_INS = "hp_ins";


    // variabel ID peserta
    public static final String PST_ID = "id_pst";
    public static final String INS_ID = "id_ins";
}
