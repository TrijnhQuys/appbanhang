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

import com.heliwr.appbandogiadung.MainActivity_Sanphamtheosuckhoe;
import com.heliwr.appbandogiadung.MainActivity_Sanphamtheothietbi;
import com.heliwr.appbandogiadung.Model.SERVER;
import com.heliwr.appbandogiadung.Model.SPSUCKHOE;
import com.heliwr.appbandogiadung.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVSPSUCKHOE_ADAPTER extends RecyclerView.Adapter<SPSUCKHOEVIEWHOLDER> {
    Context context;
    ArrayList<SPSUCKHOE> data;

    public RVSPSUCKHOE_ADAPTER(Context context, ArrayList<SPSUCKHOE> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SPSUCKHOEVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_spsuckhoe, null);
        return new SPSUCKHOEVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SPSUCKHOEVIEWHOLDER holder, int position) {
        SPSUCKHOE sp = data.get(position);
        holder.tenspsuckhoe.setText(sp.tensanpham);
        holder.imgspsuckhoe.setImageResource(R.drawable.ic_launcher_background);
        Picasso.get().load(SERVER.urllayimagesspsuckhoe+sp.imgsanpham).into(holder.imgspsuckhoe);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_Sanphamtheosuckhoe.class);
                intent.putExtra("spsuckhoeObject", sp);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class SPSUCKHOEVIEWHOLDER extends RecyclerView.ViewHolder{
    ImageView imgspsuckhoe;
    TextView tenspsuckhoe;
    public SPSUCKHOEVIEWHOLDER(@NonNull View itemView) {
        super(itemView);
        imgspsuckhoe = itemView.findViewById(R.id.imgspsuckhoe);
        tenspsuckhoe = itemView.findViewById(R.id.texttenspsuckhoe);
    }
}
