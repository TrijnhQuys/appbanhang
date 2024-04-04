package com.heliwr.appbandogiadung.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heliwr.appbandogiadung.MainActivity_Sanphamtheogiadung;
import com.heliwr.appbandogiadung.MainActivity_Sanphamtheosuckhoe;
import com.heliwr.appbandogiadung.Model.SERVER;
import com.heliwr.appbandogiadung.Model.SPGIADUNGBEP;
import com.heliwr.appbandogiadung.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVSPGIADUNGBEP_ADAPTER extends RecyclerView.Adapter<SPGIADUNGBEPVIEWHOLDER> {
    Context context;
    ArrayList<SPGIADUNGBEP>data;

    public RVSPGIADUNGBEP_ADAPTER(Context context, ArrayList<SPGIADUNGBEP> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SPGIADUNGBEPVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_spgiadungbep, null);
        return new SPGIADUNGBEPVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SPGIADUNGBEPVIEWHOLDER holder, int position) {
        SPGIADUNGBEP sp = data.get(position);
        holder.tenspgiadungbep.setText(sp.tensanpham);
        holder.imgspgiadungbep.setImageResource(R.drawable.ic_launcher_background);
        Picasso.get().load(SERVER.urllayimagesspgiadungbep+sp.imgsanpham).into(holder.imgspgiadungbep);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_Sanphamtheogiadung.class);
                intent.putExtra("spgiadungObject", sp);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class SPGIADUNGBEPVIEWHOLDER extends RecyclerView.ViewHolder{
    ImageView imgspgiadungbep;
    TextView tenspgiadungbep;
    public SPGIADUNGBEPVIEWHOLDER(@NonNull View itemView) {
        super(itemView);
        imgspgiadungbep = itemView.findViewById(R.id.imgspgiadungbep);
        tenspgiadungbep = itemView.findViewById(R.id.texttenspgiadungbep);
    }
}
