package com.example.kelasbwh9.randis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataActivityQR extends AppCompatActivity {

   // public String query_search;
    public TextView teks_plat_nomor;
    public TextView teks_jabatan;
    public TextView teks_jenis;
    public TextView teks_type;
    public TextView teks_merk;
    public TextView teks_no_mesin;
    public TextView teks_no_rangka;
    public TextView teks_silinder;
    public TextView teks_tahun;
    //private String JSON_STRING;
    public String pencarian_query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // super.onCreate(savedInstanceState);
        //query_search = getIntent().getStringExtra("QUERY_SEARCHQR");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);
        pencarian_query = getIntent().getStringExtra("PENCARIAN_QUERY");
        setContentView(R.layout.activity_data_qr);
        teks_plat_nomor = findViewById(R.id.id_plat_nomor);
        teks_jabatan = findViewById(R.id.id_jabatan);
        teks_jenis = findViewById(R.id.id_jenis);
        teks_type = findViewById(R.id.id_type);
        teks_merk = findViewById(R.id.id_merk);
        teks_no_mesin = findViewById(R.id.id_mesin);
        teks_no_rangka = findViewById(R.id.id_rangka);
        teks_silinder = findViewById(R.id.id_silinder);
        teks_tahun = findViewById(R.id.id_tahun);

        getJSONDetail();
        Toast.makeText(getApplicationContext(),"Pencarian Data", Toast.LENGTH_SHORT).show();
    }

    public void getJSONDetail() {
        class GetDataRandis extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DataActivityQR.this,"Mengambil Data","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showDataDetails(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();

                // URLS urlsObj = new URLS();
                //String h = urlsObj.TAG_JSON_ARRAY;

                String s = rh.sendGetRequestParam(Konfigurasi.URL_GET_BY, pencarian_query);
                return s;
            }
        }
        GetDataRandis ge = new GetDataRandis();
        ge.execute();

    }

    public void showDataDetails(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            if (result.length() == 0) {
                Toast.makeText(getApplicationContext(), "Data Randis Tidak Ditemukan", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }
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

            Toast.makeText(getApplicationContext(),""+Konfigurasi.URL_GAMBAR+gambar+"", Toast.LENGTH_SHORT).show();
            if(gambar == null || gambar == "" || gambar.length()==0 || gambar == "null"){
                Picasso.get().load(Konfigurasi.URL_GAMBAR+"no_image.png").into((ImageView)findViewById(R.id.id_foto)) ;
            } else {
                Picasso.get().load(Konfigurasi.URL_GAMBAR+gambar).into((ImageView)findViewById(R.id.id_foto)) ;
            }


            teks_plat_nomor.setText(no_plat);
            teks_jenis.setText(jenis);
            teks_type.setText(type);
            teks_merk.setText(merk);
            teks_jabatan.setText(jabatan);
            teks_no_mesin.setText(no_mesin);
            teks_silinder.setText(silinder);
            teks_tahun.setText(tahun);
            teks_no_rangka.setText(no_rangka);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

