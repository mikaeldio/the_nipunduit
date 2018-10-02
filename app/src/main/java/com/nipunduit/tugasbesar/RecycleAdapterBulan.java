package com.nipunduit.tugasbesar;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecycleAdapterBulan extends RecyclerView.Adapter<RecycleAdapterBulan.MyViewHolder> {

    private List<PengeluaranBulananDAO> mList;
    public RecycleAdapterBulan(List<PengeluaranBulananDAO> mList)
    {
        this.mList=mList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mKeterangan, mJumlah;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mKeterangan=itemView.findViewById(R.id.mKeterangan);
            mJumlah=itemView.findViewById(R.id.mJumlah);
        }
    }


    @NonNull
    @Override
    public RecycleAdapterBulan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_pengeluaran_bulan,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterBulan.MyViewHolder holder, int position){
        PengeluaranBulananDAO pengeluaranBulananDAO= mList.get(position);
        holder.mKeterangan.setText("Tanggal\t:"+pengeluaranBulananDAO.Tanggal);
        holder.mJumlah.setText("Jumlah\t\t: Rp. "+pengeluaranBulananDAO.Jumlah);

    }

    @Override
    public int getItemCount(){return mList.size();}

}
