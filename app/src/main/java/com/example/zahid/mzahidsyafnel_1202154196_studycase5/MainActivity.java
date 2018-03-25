package com.example.zahid.mzahidsyafnel_1202154196_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//Mtehod Construktor dari Main Activity
public class MainActivity extends AppCompatActivity {
    //variable yang telah dideklarasi dan akan digunakan
    EditText ToDo, describe, Priority;
    database DTbase;

    //Method oncreate dari activity main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Untuk set title menjadi add to do
        setTitle("Add To Do");

        //Untuk mengakses id edit text pada layout
        ToDo = (EditText) findViewById(R.id.editTodo);
        describe = (EditText) findViewById(R.id.editDesc);
        Priority = (EditText) findViewById(R.id.editPriority);
        DTbase = new database(this);
    }

    //Method untuk kembali setelah di klik
    @Override
    public void onBackPressed() {
        //Untuk membuat intent dari add to do menuju list to do
        Intent intent = new Intent(MainActivity.this, ListToDo.class);
        //Untuk memulai intent
        startActivity(intent);
        //Untuk menutup aktivitas setelah intent dijalankan
        this.finish();
    }

    //method yang dijalanan ketika tombol tambah to do di klik
    public void tambah(View view) {
        //Jika data todoname, deskripsi dan prioritas di isi,
        if (DTbase.inputdata(new AddData(ToDo.getText().toString(), describe.getText().toString(), Priority.getText().toString()))){
            //Jika menampilkan toast bahwa data berhasil di tambahkan ke dalam list
            Toast.makeText(this, "To Do List added!", Toast.LENGTH_SHORT).show();
            //Untuk berpindah dari add to do ke list to do
            startActivity(new Intent(MainActivity.this, ListToDo.class));
            //Untuk menutup aktivitas agar tidak kembali ke activity yang dijalankan setelah intent
            this.finish();
        }else {
            //jika edit text kosong maka akan muncul toast bahwa tidak bisa menambah ke dalam list
            Toast.makeText(this, "Cannot add the list", Toast.LENGTH_SHORT).show();
            //untuk menset semua edit text menjadi kosong
            ToDo.setText(null);
            describe.setText(null);
            Priority.setText(null);
        }
    }

}
