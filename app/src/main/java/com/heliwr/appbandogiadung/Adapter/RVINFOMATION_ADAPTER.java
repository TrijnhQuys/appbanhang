package com.heliwr.appbandogiadung.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heliwr.appbandogiadung.Model.INFOMATION;
import com.heliwr.appbandogiadung.R;

import java.util.ArrayList;

public class RVINFOMATION_ADAPTER extends RecyclerView.Adapter<RVINFOMATIONVIEWHOLDER> {
    Context context;
    ArrayList<INFOMATION> data;

    public RVINFOMATION_ADAPTER(Context context, ArrayList<INFOMATION> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RVINFOMATIONVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_infomation, null);
        return new RVINFOMATIONVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVINFOMATIONVIEWHOLDER holder, int position) {
    INFOMATION infomation = data.get(position);
    holder.tvnoidung.setText(infomation.noidungif);
    holder.tvqua.setText(infomation.quaif);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class RVINFOMATIONVIEWHOLDER extends RecyclerView.ViewHolder{
    TextView tvnoidung;
    TextView tvqua;
    public RVINFOMATIONVIEWHOLDER(@NonNull View itemView) {
        super(itemView);
      tvnoidung = itemView.findViewById(R.id.tvnoidungqua);
       tvqua = itemView.findViewById(R.id.tvqua);
    }
}
