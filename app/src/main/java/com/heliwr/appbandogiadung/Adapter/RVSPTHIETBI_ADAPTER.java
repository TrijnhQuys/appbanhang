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

import com.heliwr.appbandogiadung.MainActivity_Sanphamtheothietbi;
import com.heliwr.appbandogiadung.Model.SPTHIETBI;
import com.heliwr.appbandogiadung.Model.SERVER;
import com.heliwr.appbandogiadung.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVSPTHIETBI_ADAPTER extends RecyclerView.Adapter<SPTHIETBIVIEWHOLDER> {
    Context context;
    ArrayList<SPTHIETBI>data;
    public RVSPTHIETBI_ADAPTER( Context context, ArrayList<SPTHIETBI>data) {this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public SPTHIETBIVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_spthietbi, null);

        return new SPTHIETBIVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SPTHIETBIVIEWHOLDER holder, int position) {
    SPTHIETBI sp = data.get(position);
    holder.imgspthietbi.setImageResource(R.drawable.ic_launcher_background);
        Picasso.get().load(SERVER.urllayimagesspthietbi+sp.imgsanpham).into(holder.imgspthietbi);
        holder.tenspthietbi.setText(sp.tensanpham);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_Sanphamtheothietbi.class);
                intent.putExtra("spthietbiObject", sp);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class SPTHIETBIVIEWHOLDER extends RecyclerView.ViewHolder {
    ImageView imgspthietbi;
    TextView tenspthietbi;

    public SPTHIETBIVIEWHOLDER(@NonNull View itemView) {
        super(itemView);
        imgspthietbi = itemView.findViewById(R.id.imgspthietbi);
        tenspthietbi = itemView.findViewById(R.id.texttenspthietbi);
    }
}