package com.nipunduit.tugasbesar;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecycleAdapterHari extends RecyclerView.Adapter<RecycleAdapterHari.MyViewHolder> {

    private List<PengeluaranDAO> mList;
    public RecycleAdapterHari(List<PengeluaranDAO> mList)
    {
        this.mList=mList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mKeterangan, mJumlah;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mKeterangan=itemView.findViewById(R.id.mDay);
            mJumlah=itemView.findViewById(R.id.mJumlah);
        }
    }



    @NonNull
    @Override
    public RecycleAdapterHari.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_pengeluaran_harian,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterHari.MyViewHolder holder, int position){
        PengeluaranDAO pengeluaranDAO= mList.get(position);
        holder.mKeterangan.setText("Keterangan\t:"+pengeluaranDAO.Keterangan);
        holder.mJumlah.setText("Jumlah\t\t: Rp. "+pengeluaranDAO.Jumlah);

    }

    @Override
    public int getItemCount(){return mList.size();}

}
