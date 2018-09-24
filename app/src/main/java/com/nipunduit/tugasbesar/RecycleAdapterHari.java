package com.nipunduit.tugasbesar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapterHari extends RecyclerView.Adapter<RecycleAdapterHari.MyViewHolder> {

    Context context;
    List<PengeluaranDAO> result;

    public RecycleAdapterHari(Context context, List<PengeluaranDAO> result){
        this.context = context;
        this.result=result;
    }

    public void setPengeluaranlist(List<PengeluaranDAO> pengeluaranlist){
        this.result = pengeluaranlist;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup viewGroup,int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_pengeluaran_harian,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i){
        PengeluaranDAO peng= result.get(i);
        String duit= new Integer(peng.getJumlah()).toString();
        myViewHolder.mKeterangan.setText("Keterangan: "+peng.getKeterangan());
        myViewHolder.mJumlah.setText("Jumlah: "+duit);
    }

    @Override
    public int getItemCount() {
        if (result != null) {
            return result.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mKeterangan, mJumlah;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mKeterangan=itemView.findViewById(R.id.mKeterangan);
            mJumlah=itemView.findViewById(R.id.mJumlah);
        }

    }
}
