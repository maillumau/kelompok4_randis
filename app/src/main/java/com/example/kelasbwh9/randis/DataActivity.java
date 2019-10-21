package com.example.kelasbwh9.randis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Process;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DataActivity extends AppCompatActivity {

    private ListView listView;
    private String JSON_STRING;
    public String pencarian_query;

    public TextView teks_plat_nomor;
    public TextView teks_jabatan;
    public TextView teks_jenis;
    public TextView teks_type;
    public TextView teks_merk;
    public TextView teks_no_mesin;
    public TextView teks_no_rangka;
    public TextView teks_silinder;
    public TextView teks_tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);
        pencarian_query = getIntent().getStringExtra("PENCARIAN_QUERY");
        Toast.makeText(getApplicationContext(), "" + pencarian_query + "", Toast.LENGTH_SHORT).show();

        teks_plat_nomor = findViewById(R.id.id_plat_nomor);
        teks_jabatan = findViewById(R.id.id_jabatan);
        teks_jenis = findViewById(R.id.id_jenis);
        teks_type = findViewById(R.id.id_type);
        teks_merk = findViewById(R.id.id_merk);
        teks_no_mesin = findViewById(R.id.id_mesin);
        teks_no_rangka = findViewById(R.id.id_rangka);
        teks_silinder = findViewById(R.id.id_silinder);
        teks_tahun = findViewById(R.id.id_tahun);


        //listView = findViewById(R.id.listView);
        getJSON();


    }


    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DataActivity.this, "Mengambil Data", "Mohon Tunggu...", false, false);
            }

            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                LihatData(JSON_STRING);
            }

            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_GET_BY, pencarian_query);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void LihatData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String no_plat = c.getString(Konfigurasi.TAG_NOMOR_PLAT);
            String jenis = c.getString(Konfigurasi.TAG_JENIS);
            String type = c.getString(Konfigurasi.TAG_TYPE);
            String merk = c.getString(Konfigurasi.TAG_MERK);
            String jabatan = c.getString(Konfigurasi.TAG_JABATAN);
            String no_mesin = c.getString(Konfigurasi.TAG_NO_MESIN);
            String no_rangka = c.getString(Konfigurasi.TAG_NO_RANGKA);
            String silinder = c.getString(Konfigurasi.TAG_SILINDER);
            String tahun = c.getString(Konfigurasi.TAG_TAHUN);
            String gambar = c.getString(Konfigurasi.TAG_GAMBAR);


            teks_plat_nomor.setText(no_plat);
            teks_jenis.setText(jenis);
            teks_type.setText(type);
            teks_merk.setText(merk);
            teks_jabatan.setText(jabatan);
            teks_no_mesin.setText(no_mesin);
            teks_silinder.setText(silinder);
            teks_tahun.setText(tahun);
            teks_no_rangka.setText(no_rangka);


            Toast.makeText(getApplicationContext(),""+Konfigurasi.URL_HTTP+gambar+"", Toast.LENGTH_SHORT).show();
            if(gambar == null || gambar == "" || gambar.length()==0 || gambar == "null"){
                Picasso.get().load(Konfigurasi.URL_HTTP+"no_image.png").into((ImageView)findViewById(R.id.id_foto)) ;
            } else {
                Picasso.get().load(Konfigurasi.URL_HTTP+gambar).into((ImageView)findViewById(R.id.id_foto)) ;
            }



        } catch (JSONException e) {
            e.printStackTrace();

        }


    }
}

