package com.example.zahid.mzahidsyafnel_1202154196_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ListToDo extends AppCompatActivity {
    //variable yang telah dideklarasi dan akan digunakan
    database db;
    RecyclerView RV;
    adapter adtpr;
    ArrayList<AddData> listdata;

    //method untuk membuat activity list to do
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_to_do);
        //Untuk menset title menjadi To Do List
        setTitle("To Do List");

        //Untuk mengakses recyclerview yang ada pada layout
        RV = findViewById(R.id.recview);
        //Untuk membuat araylist baru
        listdata = new ArrayList<>();
        //Untuk membuat database baru
        db = new database(this);
        //Untuk memanggil method read data
        db.readdata(listdata);

        //Untuk menginisialisasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //Untuk membuat adapter baru
        adtpr = new adapter(this,listdata, color);
        //Untuk menghindari perubahan ukuran yang tidak perlu ketika menambahkan / hapus item pada recycler view
        RV.setHasFixedSize(true);
        //Untuk menampilkan layoutnya linier
        RV.setLayoutManager(new LinearLayoutManager(this));
        //Untuk menginisiasi adapter untuk recycler view
        RV.setAdapter(adtpr);

        //Untuk menjalankan method hapus data pada list to do
        hapusgeser();
    }

    //method untuk membuat dan menghapus item pada to do list
    public void hapusgeser(){
        //membuat touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            //Method untuk recycler view
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //Method untuk Swiped saat menghapus data list
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AddData current = adtpr.getData(position);
                //jika apabila item di swipe ke arah kiri
                if(direction==ItemTouchHelper.LEFT){
                    //remove item yang dipilih dengan mengenali todonya sebagai primary key
                    if(db.removedata(current.getTodo())){
                        //untuk menghapus data
                        adtpr.deleteData(position);
                        //untuk membuat snack bar dan pemberitahuan bahwa item sudah terhapus dengan durasi 1 sekon
                        Snackbar.make(findViewById(R.id.coor), "Data Deleted", 1000).show();
                    }
                }
            }
        };
        //untuk menentukan item touch helper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(RV);
    }
    //Method ketika menu pada activity di buat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_to_do_list, menu);
        return true;
    }

    //Method yang dijalankan ketika item di pilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Untuk mendapatkan id dari item yang
        int id = item.getItemId();
        //Jika item yang dipilih adalah setting
        if (id==R.id.action_settings){
            //Untuk membuat intent baru dari list to do ke pengaturan
            Intent intent = new Intent(ListToDo.this, Configure.class);
            //Untuk memulai intent
            startActivity(intent);
            //Untuk menutup aktivitas setelah intent dijalankan
            finish();
        }
        return true;
    }

    //method yang akan dijalankan ketika tombol add di klik
    public void add(View view) {
        //intent dari list to do ke add to do
        Intent intent = new Intent(ListToDo.this, MainActivity.class);
        //memulai intent
        startActivity(intent);
    }
}
