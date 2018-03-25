package com.example.zahid.mzahidsyafnel_1202154196_studycase5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.holder> {
    //variable yang telah dideklarasi dan akan digunakan
    private Context conteks;
    private List<AddData> list;
    int colors;

    //Method untuk konstruktor
    public adapter(Context cntx, List<AddData> list, int color){
        this.conteks=cntx;
        this.list=list;
        this.colors=color;
    }

    //Method untuk menentukan viewholder untuk recyclerview
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru
        View view = LayoutInflater.from(conteks).inflate(R.layout.cardviewlist, parent, false);
        holder hldr = new holder(view);
        return hldr;
    }

    //Method untuk mensetting nilai yang didapatkan pada viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        AddData data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Description.setText(data.getDesc());
        holder.Priority.setText(data.getPrior());
        holder.cardv.setCardBackgroundColor(conteks.getResources().getColor(this.colors));
    }

    //Method untuk mendapatkan jumlah list
    @Override
    public int getItemCount() {
        return list.size();
    }

    //Method untuk mendapatkan list dari adapter
    public AddData getData(int position){
        return list.get(position);
    }

    //Method untuk menghapus list pada todolist
    public void deleteData(int i){
        //meremove item yang terpilih
        list.remove(i);
        //memberi notifikasi item yang di remove
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size());
    }

    class holder extends RecyclerView.ViewHolder{
        //Mendeklarasi variable yang akan digunakan
        public TextView ToDo, Description, Priority;
        public CardView cardv;
        public holder(View itemView){
            super(itemView);

            //Untuk mengakses id text view pada layout dan juga cardview
            ToDo = itemView.findViewById(R.id.headline);
            Description = itemView.findViewById(R.id.explanation);
            Priority = itemView.findViewById(R.id.number);
            cardv = itemView.findViewById(R.id.cardlist);
        }
    }
}
