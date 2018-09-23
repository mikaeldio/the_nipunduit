package com.nipunduit.tugasbesar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecycleAdapterHari extends RecyclerView.Adapter<RecycleAdapterHari.MyViewHolder> {

    private Context context;
    private List<PengeluaranDAO> result;
    public RecycleAdapterHari(Context context, List<PengeluaranDAO> result){
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_pengeluaran_harian,viewGroup,false);
        final MyViewHolder holder=new MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i){
        PengeluaranDAO mhs= result.get(i);
        myViewHolder.mKeterangan.setText(mhs.getKeterangan());
        myViewHolder.mJumlah.setText(mhs.getJumlah());
    }

    @Override
    public int getItemCount(){return result.size();}


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mKeterangan, mJumlah;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mKeterangan=itemView.findViewById(R.id.mKeterangan);
            mJumlah=itemView.findViewById(R.id.mJumlah);
        }

        @Override
        public void onClick(View view){
            Toast.makeText(context,"Hey You Clicked on Me", Toast.LENGTH_SHORT).show();
        }

    }
}
