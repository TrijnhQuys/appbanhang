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

import com.heliwr.appbandogiadung.Interface.IImageClickListenner;
import com.heliwr.appbandogiadung.MainActivity_Chitietsanpham;
import com.heliwr.appbandogiadung.Model.EventBus.TinhTongEvent;
import com.heliwr.appbandogiadung.Model.GIOHANG;
import com.heliwr.appbandogiadung.Model.SERVER;
import com.heliwr.appbandogiadung.R;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
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

        String tenSanPham = sp.tensanpham;
        int MAX_LENGTH = 20;
        if (tenSanPham.length() > MAX_LENGTH) {
            tenSanPham = tenSanPham.substring(0, MAX_LENGTH) + "...";
        }
        holder.texttenallsanpham.setText(tenSanPham);

        holder.textxuatxu.setText(sp.xuatxu);
        holder.textViewSoluong.setText(sp.soluong+" ");
        holder.textbaohanh.setText(sp.baohanh);
        holder.imgallsanpham.setImageResource(R.drawable.banui);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(sp.dongia);
        holder.textgiaallsanpham.setText(formattedPrice);
        long gia = sp.soluong*sp.dongia;
        String formattedPrice2 = decimalFormat.format(gia);
        holder.textgiaallsanpham2.setText(formattedPrice2);
        Picasso.get().load(SERVER.urllayimagesallsanpham+sp.imgsanpham).into(holder.imgallsanpham);
        holder.imgallsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_Chitietsanpham.class);
                intent.putExtra("sanpham", sp);
                context.startActivity(intent);
            }
        });
        holder.setClickListenner(new IImageClickListenner() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                if (giatri==1){
                    if (giohangdata.get(pos).getSoluong()>1){
                        int soluongmoi=giohangdata.get(pos).getSoluong()-1;
                        giohangdata.get(pos).setSoluong(soluongmoi);
                    }
                } else if (giatri==2) {
                    if (giohangdata.get(pos).getSoluong()<11){
                        int soluongmoi=giohangdata.get(pos).getSoluong()+1;
                        giohangdata.get(pos).setSoluong(soluongmoi);
                    }
                }
                holder.textViewSoluong.setText(giohangdata.get(pos).getSoluong()+" ");
                long gia = giohangdata.get(pos).getSoluong()*giohangdata.get(pos).getDongia();
                String formattedPrice2 = decimalFormat.format(gia);
                holder.textgiaallsanpham2.setText(formattedPrice2);
                EventBus.getDefault().postSticky(new TinhTongEvent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return giohangdata.size();
    }
}
class GIOHANGVIEWHOLDER extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView imgallsanpham, imgtang, imggiam;
    TextView texttenallsanpham,textgiaallsanpham, textxuatxu, textbaohanh, textViewSoluong, textgiaallsanpham2;
    IImageClickListenner clickListenner;
    public GIOHANGVIEWHOLDER(@NonNull View itemView) {
        super(itemView);
        imgallsanpham = itemView.findViewById(R.id.imgallsanpham);
        textgiaallsanpham = itemView.findViewById(R.id.textgiaallsanpham);
        texttenallsanpham = itemView.findViewById(R.id.texttenallsanpham);
        textViewSoluong=itemView.findViewById(R.id.textviewsoluong);
        textbaohanh = itemView.findViewById(R.id.textviewbaohanh);
        textxuatxu=itemView.findViewById(R.id.texviewxuatxu);
        textgiaallsanpham2=itemView.findViewById(R.id.textviewgia2);
        imggiam=itemView.findViewById(R.id.giam);
        imgtang=itemView.findViewById(R.id.tang);

        //EVENT CLICK
        imgtang.setOnClickListener(this);
        imggiam.setOnClickListener(this);
    }

    public void setClickListenner(IImageClickListenner clickListenner) {
        this.clickListenner = clickListenner;
    }

    @Override
    public void onClick(View v) {
        if (v == imggiam){
            clickListenner.onImageClick(v, getAdapterPosition(), 1);
            //1 TRU
        }else if (v ==imgtang){
            clickListenner.onImageClick(v, getAdapterPosition(), 2);
        }
    }
}
