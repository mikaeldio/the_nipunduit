package com.nipunduit.tugasbesar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class RecycleAdapterBulan extends Adapter<MyViewHolder> {
    private Context context;
    private Button mDetail;
    private List<PengeluaranBulananDAO> result;

    public class MyViewHolder extends ViewHolder {
        public TextView mJumlah;
        public TextView mTanggal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTanggal = (TextView) itemView.findViewById(C0376R.id.mTanggal);
            this.mJumlah = (TextView) itemView.findViewById(C0376R.id.mJumlah);
        }
    }

    public RecycleAdapterBulan(Context context, List<PengeluaranBulananDAO> result) {
        this.context = context;
        this.result = result;
    }

    public void setPengeluaranBulanlist(List<PengeluaranBulananDAO> pengeluaranBulanlist) {
        this.result = pengeluaranBulanlist;
        notifyDataSetChanged();
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(C0376R.layout.recycle_pengeluaran_harian, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final String nTanggal = ((PengeluaranBulananDAO) this.result.get(position)).Tanggal;
        this.mDetail.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(RecycleAdapterBulan.this.context, ShowPengeluaranBulanActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("tanggal", nTanggal);
                i.putExtra("bTanggal", mBundle);
                RecycleAdapterBulan.this.context.startActivity(i);
            }
        });
    }

    public int getItemCount() {
        if (this.result != null) {
            return this.result.size();
        }
        return 0;
    }
}
