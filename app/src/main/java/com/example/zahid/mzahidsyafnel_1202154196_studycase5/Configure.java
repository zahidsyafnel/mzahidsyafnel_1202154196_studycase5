package com.example.zahid.mzahidsyafnel_1202154196_studycase5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

//Method Konstruktor
public class Configure extends AppCompatActivity {
    //variable yang telah dideklarasi dan akan digunakan
    TextView shapeclr;
    int idcolor;
    AlertDialog.Builder alert;
    SharedPreferences.Editor spr;

    //Method untuk membuat view dari configure
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        setTitle("Configure");

        //Untuk membuat alert dialog baru bernama alert
        alert = new AlertDialog.Builder(this);

        //Untuk menginisialisasi shared preference
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0);
        spr = sharedP.edit();
        idcolor = sharedP.getInt("Colourground", R.color.white);
        //Untuk text view pada layout
        shapeclr = findViewById(R.id.shapecolor);
        //Untuk mensetting shape color dengan color id yang terpilih
        shapeclr.setText(getShapeColor(idcolor));
    }

    //Method untuk tombol back di tekan
    @Override
    public void onBackPressed() {
        //Untuk intent baru dari pengaturan menuju list to do
        Intent intent = new Intent(Configure.this, ListToDo.class);
        //Untuk memulai intent
        startActivity(intent);
        //Untuk menutup aktivity setelah intent di jalanlan
        finish();
    }

    //method untuk dijalankan ketika pilihan pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            //Untuk menjalankan method onbackpressed
            this.onBackPressed();
        }//kondisi jika benar
        return true;
    }

    //Method untuk mendapatkan string warna yang akan digunakan untuk mengubah shape color
    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else{
            return "Default";
        }
    }

    //Method untuk mendapatkan id dari warna yang akan digunakan
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.green){
            return R.id.green;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else{
            return R.id.white;
        }
    }

    //Method untuk Memilih warna
    public void choosecolor(View view) {
        //Untuk menseting title dari alert dialog menjadi Shape Color
        alert.setTitle("Shape Color");
        //Untuk membuat view baru
        View view1 = getLayoutInflater().inflate(R.layout.colorsettings, null);
        //Untuk menampilkan view yang telah dibuat
        alert.setView(view1);

        //Untuk mengakses radio group pada layout
        final RadioGroup radG = view1.findViewById(R.id.radiocolor);
        radG.check(getColorid(idcolor));

        //Jika ketika menekan Ok pada alert dialog
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Untuk mendapatkan id radio button yang di pilih
                int a = radG.getCheckedRadioButtonId();
                switch (a){
                    case R.id.red:
                        idcolor = R.color.red;
                        break;
                    case R.id.green:
                        idcolor = R.color.green;
                        break;
                    case R.id.blue:
                        idcolor = R.color.blue;
                        break;
                    case R.id.white:
                        idcolor = R.color.white;
                        break;
                }
                //Untuk mensetting shape color menjadi color id yang dipilih
                shapeclr.setText(getShapeColor(idcolor));
                //Untuk menaruh shared preference
                spr.putInt("Colourground", idcolor);
                //Untuk commit shared preference
                spr.commit();
            }
        });

        //Untuk alert ketika menekan Cancel pada alert dialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //Untuk membuat dan menampilkan alert dialog
        alert.create().show();
    }
}
