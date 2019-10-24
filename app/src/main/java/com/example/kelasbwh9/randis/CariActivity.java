package com.example.kelasbwh9.randis;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

public class CariActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView editsearch;
    public EditText pencarian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);

        //pencarian = findViewById(R.id.editNomor);
        editsearch = findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
    }


    public void pindahHalmana(View view){


            String data_pencarian = pencarian.getText().toString();

            Intent intent = new Intent(CariActivity.this,DataActivity.class);
            intent.putExtra("PENCARIAN_QUERY", data_pencarian);

            //ambil data dari search view
        // kemudian kirim melalui intent

            startActivity(intent);

    }


    public void TombolQR(View view){

        Intent intent = new Intent(CariActivity.this,QRCodeActivity.class);
        startActivity(intent);

    }

    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(getApplicationContext(),""+query+"", Toast.LENGTH_SHORT).show();
        Intent intentObj = new Intent(this, DataActivity.class);
        intentObj.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentObj.putExtra("PENCARIAN_QUERY", query);
        startActivity(intentObj);
        CariActivity.this.finish();
        return false;
    }

    public boolean onQueryTextChange(String newText) {
        String text = newText;
        return false;
    }
}
