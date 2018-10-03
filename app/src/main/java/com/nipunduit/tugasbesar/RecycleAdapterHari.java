package com.nipunduit.tugasbesar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class RecycleAdapterHari extends Adapter<MyViewHolder> {
    Context context;
    List<PengeluaranDAO> result;

    public class MyViewHolder extends ViewHolder {
        public TextView mJumlah;
        public TextView mKeterangan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mKeterangan = (TextView) itemView.findViewById(C0376R.id.mKeterangan);
            this.mJumlah = (TextView) itemView.findViewById(C0376R.id.mJumlah);
        }
    }

    public RecycleAdapterHari(Context context, List<PengeluaranDAO> result) {
        this.context = context;
        this.result = result;
    }

    public void setPengeluaranlist(List<PengeluaranDAO> pengeluaranlist) {
        this.result = pengeluaranlist;
        notifyDataSetChanged();
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(C0376R.layout.recycle_pengeluaran_harian, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        PengeluaranDAO peng = (PengeluaranDAO) this.result.get(i);
        String duit = new Integer(peng.getJumlah().intValue()).toString();
        TextView textView = myViewHolder.mKeterangan;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Keterangan: ");
        stringBuilder.append(peng.getKeterangan());
        textView.setText(stringBuilder.toString());
        textView = myViewHolder.mJumlah;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Jumlah: ");
        stringBuilder.append(duit);
        textView.setText(stringBuilder.toString());
    }

    public int getItemCount() {
        if (this.result != null) {
            return this.result.size();
        }
        return 0;
    }
}
