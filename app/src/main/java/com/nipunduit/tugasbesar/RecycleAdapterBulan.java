package com.nipunduit.tugasbesar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class RecycleAdapterBulan extends RecyclerView.Adapter<RecycleAdapterBulan.MyViewHolder> {

    private Context context;
    private Button mDetail;
    private List<PengeluaranBulananDAO> result;

    public RecycleAdapterBulan(Context context, List<PengeluaranBulananDAO> result){
        this.context = context;
        this.result=result;
    }

    public void setPengeluaranBulanlist(List<PengeluaranBulananDAO> pengeluaranBulanlist){
        this.result = pengeluaranBulanlist;
        notifyDataSetChanged();
    }

    @Override
    public RecycleAdapterBulan.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_pengeluaran_harian,viewGroup,false);
        return new RecycleAdapterBulan.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterBulan.MyViewHolder holder, int position){
        PengeluaranBulananDAO pengeluaranBulananDAO= result.get(position);
        final String nTanggal = pengeluaranBulananDAO.Tanggal;
        //holder.mTanggal.setText("Tanggal\t:"+pengeluaranBulananDAO.Tanggal);
       // holder.mJumlah.setText("Jumlah\t\t: Rp. "+(pengeluaranBulananDAO.Jumlah).toString());
        mDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ShowPengeluaranBulanActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("tanggal", nTanggal);
                i.putExtra("bTanggal",mBundle);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (result != null) {
            return result.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mTanggal, mJumlah;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mTanggal=itemView.findViewById(R.id.mTanggal);
            mJumlah=itemView.findViewById(R.id.mJumlah);
        }

    }

}
