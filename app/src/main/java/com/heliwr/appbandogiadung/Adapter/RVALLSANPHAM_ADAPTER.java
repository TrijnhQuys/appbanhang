package com.heliwr.appbandogiadung.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heliwr.appbandogiadung.MainActivity_Chitietsanpham;
import com.heliwr.appbandogiadung.Model.ALLSANPHAM;
import com.heliwr.appbandogiadung.Model.SERVER;
import com.heliwr.appbandogiadung.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RVALLSANPHAM_ADAPTER extends RecyclerView.Adapter<ALLSANPHAMVIEWHOLDER> {

    Context context;
    ArrayList<ALLSANPHAM>data;

    public RVALLSANPHAM_ADAPTER(Context context, ArrayList<ALLSANPHAM> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ALLSANPHAMVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_allsanpham, null);
        return new ALLSANPHAMVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ALLSANPHAMVIEWHOLDER holder, int position) {
        ALLSANPHAM sp = data.get(position);
        String tenSanPham = sp.tensanpham;
        int MAX_LENGTH = 20;
        if (tenSanPham.length() > MAX_LENGTH) {
            tenSanPham = tenSanPham.substring(0, MAX_LENGTH) + "...";
        }
        holder.texttenallsanpham.setText(tenSanPham);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(Double.parseDouble(sp.dongia));
        holder.textgiaallsanpham.setText(formattedPrice);
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
        return data.size();
    }


}
class ALLSANPHAMVIEWHOLDER extends RecyclerView.ViewHolder{
    ImageView imgallsanpham;
    TextView texttenallsanpham,textgiaallsanpham, textxuatxu, textbaohanh;
    public ALLSANPHAMVIEWHOLDER(@NonNull View itemView) {
        super(itemView);
        imgallsanpham = itemView.findViewById(R.id.imgallsanpham);
        textgiaallsanpham = itemView.findViewById(R.id.textgiaallsanpham);
        texttenallsanpham = itemView.findViewById(R.id.texttenallsanpham);
        textbaohanh = itemView.findViewById(R.id.textviewbaohanh);
        textxuatxu=itemView.findViewById(R.id.texviewxuatxu);
    }
}