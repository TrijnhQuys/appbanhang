package com.heliwr.appbandogiadung.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heliwr.appbandogiadung.MainActivity_Chitietsanpham;
import com.heliwr.appbandogiadung.Model.ALLSANPHAM;
import com.heliwr.appbandogiadung.Model.SERVER;
import com.heliwr.appbandogiadung.R;
import com.squareup.picasso.Picasso;

import java.io.FileFilter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class RVSEARCH_ADAPTER extends RecyclerView.Adapter<SEARCHVIEWHOLDER> implements Filterable {
    Context context;
    ArrayList<ALLSANPHAM> data;
    ArrayList<ALLSANPHAM> filterData;

    public RVSEARCH_ADAPTER(Context context, ArrayList<ALLSANPHAM> data) {
        this.context = context;
        this.data = data;
        this.filterData=new ArrayList<>(data);
    }

    @NonNull
    @Override
    public SEARCHVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_allsanpham, null);
        return new SEARCHVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SEARCHVIEWHOLDER holder, int position) {

        ALLSANPHAM sp = filterData.get(position);
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
        return filterData.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            //được ghi đè để thực hiện quá trình lọc dữ liệu dựa trên ràng buộc
            protected FilterResults performFiltering(CharSequence constraint) {

                String query = constraint.toString().toLowerCase();//chuyen doi thanh chu thuong, thanh chuoi ky tu
                ArrayList<ALLSANPHAM> filteredList = new ArrayList<>();

                if (query.isEmpty()) {
                    filteredList.addAll(data);
                } else {
                    for (ALLSANPHAM room : data) {
                        if (room.tensanpham.toLowerCase().contains(query) || room.dongia.toLowerCase().contains(query)) {
                            filteredList.add(room);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterData.clear();
                filterData.addAll((ArrayList<ALLSANPHAM>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
class SEARCHVIEWHOLDER extends RecyclerView.ViewHolder{
    ImageView imgallsanpham;
    TextView texttenallsanpham,textgiaallsanpham, textxuatxu, textbaohanh;
    public SEARCHVIEWHOLDER(@NonNull View itemView) {
        super(itemView);
        imgallsanpham = itemView.findViewById(R.id.imgallsanpham);
        textgiaallsanpham = itemView.findViewById(R.id.textgiaallsanpham);
        texttenallsanpham = itemView.findViewById(R.id.texttenallsanpham);
        textbaohanh = itemView.findViewById(R.id.textviewbaohanh);
        textxuatxu=itemView.findViewById(R.id.texviewxuatxu);
    }
}
