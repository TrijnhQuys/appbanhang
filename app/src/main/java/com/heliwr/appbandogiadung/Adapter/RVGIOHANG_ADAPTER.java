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

import com.heliwr.appbandogiadung.MainActivity_Chitietsanpham;
import com.heliwr.appbandogiadung.Model.GIOHANG;
import com.heliwr.appbandogiadung.Model.SERVER;
import com.heliwr.appbandogiadung.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RVGIOHANG_ADAPTER extends RecyclerView.Adapter<GIOHANGVIEWHOLDER> {
    Context context;
    List<GIOHANG>giohangdata;

    public RVGIOHANG_ADAPTER(Context context, List<GIOHANG> giohangdata) {
        this.context = context;
        this.giohangdata = giohangdata;
    }

    @NonNull
    @Override
    public GIOHANGVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_giohang, null);
        return new GIOHANGVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GIOHANGVIEWHOLDER holder, int position) {
        GIOHANG sp = giohangdata.get(position);

        holder.texttenallsanpham.setText(sp.tensanpham);

        holder.textgiaallsanpham.setText(String.valueOf(sp.getDongia()));
        holder.textxuatxu.setText(sp.xuatxu);
        holder.textbaohanh.setText(sp.baohanh);
        holder.imgallsanpham.setImageResource(R.drawable.banui);
        Picasso.get().load(SERVER.urllayimagesallsanpham+sp.imgsanpham).into(holder.imgallsanpham);
        holder.imgallsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_Chitietsanpham.class);
                intent.putExtra("sanpham", sp);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return giohangdata.size();
    }
}
class GIOHANGVIEWHOLDER extends RecyclerView.ViewHolder{
    ImageView imgallsanpham;
    TextView texttenallsanpham,textgiaallsanpham, textxuatxu, textbaohanh;
    public GIOHANGVIEWHOLDER(@NonNull View itemView) {
        super(itemView);
        imgallsanpham = itemView.findViewById(R.id.imgallsanpham);
        textgiaallsanpham = itemView.findViewById(R.id.textgiaallsanpham);
        texttenallsanpham = itemView.findViewById(R.id.texttenallsanpham);
        textbaohanh = itemView.findViewById(R.id.textviewbaohanh);
        textxuatxu=itemView.findViewById(R.id.texviewxuatxu);
    }
}
