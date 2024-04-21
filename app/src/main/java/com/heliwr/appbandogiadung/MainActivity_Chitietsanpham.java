package com.heliwr.appbandogiadung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.heliwr.appbandogiadung.Model.ALLSANPHAM;
import com.heliwr.appbandogiadung.Model.GIOHANG;
import com.heliwr.appbandogiadung.Model.SERVER;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity_Chitietsanpham extends AppCompatActivity {
    ImageView imgspchitiet;
    TextView tvtenspchitiet, tvdongia, tvmota, tvcuahangcon, tvxuatxu, tvbaohanh;
    AppCompatButton  btnthemgiohang;
    ALLSANPHAM allsanpham;
    NotificationBadge badge;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chitietsanpham);
        imgspchitiet = findViewById(R.id.imgspchitiet);
        tvtenspchitiet = findViewById(R.id.tvtenspchitiet);
        tvdongia = findViewById(R.id.tvgiaspchitiet);
        tvmota=findViewById(R.id.tvmotachitiet);
        tvcuahangcon = findViewById(R.id.tvcuahangcon);
        tvbaohanh = findViewById(R.id.baohanh);
        tvxuatxu = findViewById(R.id.xuatxu);
        btnthemgiohang=findViewById(R.id.btnthemsp);
        badge = findViewById(R.id.menu_sl);
        spinner=findViewById(R.id.spinner);


        Integer []so=new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayAdapter<Integer>integerArrayAdapter=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, so);
        spinner.setAdapter(integerArrayAdapter);
        Intent intent =getIntent();
        allsanpham =(ALLSANPHAM) intent.getSerializableExtra("sanpham");

        Picasso.get().load(SERVER.urllayimagesallsanpham+allsanpham.imgsanpham).into(imgspchitiet);
        tvtenspchitiet.setText(allsanpham.tensanpham);
        tvdongia.setText(allsanpham.dongia);
        tvmota.setText(allsanpham.mota);
        tvcuahangcon.setText(allsanpham.cuahangcon);
        tvbaohanh.setText(allsanpham.baohanh);
        tvxuatxu.setText(allsanpham.xuatxu);


        FrameLayout frameLayout = findViewById(R.id.frameGiohang);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivityGioHang.class);
                startActivity(intent);
            }
        });

        btnthemgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themgiohang();
            }
        });
        if (SERVER.manggiohang!=null){
            int totalItem = 0;
            for (int i =0;i<SERVER.manggiohang.size();i++){
                totalItem=totalItem+SERVER.manggiohang.get(i).soluong;
            }
            badge.setText(String.valueOf(totalItem));
        }
    }


    private void themgiohang() {
        if (SERVER.manggiohang.size() > 0) {
            boolean flag = false;
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            for (int i = 0; i < SERVER.manggiohang.size(); i++) {
                if (SERVER.manggiohang.get(i).getMasanpham()==allsanpham.getMasanpham()) {
                    SERVER.manggiohang.get(i).setSoluong(soluong + SERVER.manggiohang.get(i).getSoluong());
                    long gia = Long.parseLong(allsanpham.getDongia()) * SERVER.manggiohang.get(i).getSoluong();
                    SERVER.manggiohang.get(i).setDongia(gia);
                    flag = true;
                }
            }
            if (!flag) {
                long gia = Long.parseLong(allsanpham.getDongia()) * soluong;
                GIOHANG giohang = new GIOHANG();
                giohang.setTensanpham(allsanpham.tensanpham);
                giohang.setDongia(gia);
                giohang.setSoluong(soluong);
                giohang.setMasanpham(allsanpham.getMasanpham());
                giohang.setImgsanpham(allsanpham.getImgsanpham());
                giohang.setBaohanh(allsanpham.getBaohanh());
                giohang.setXuatxu(allsanpham.getXuatxu());
                giohang.setMota(allsanpham.getMota());
                SERVER.manggiohang.add(giohang);
            }
        } else {
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            long gia = Long.parseLong(allsanpham.getDongia() )* soluong;
            GIOHANG giohang = new GIOHANG();
            giohang.setDongia(gia);
            giohang.setTensanpham(allsanpham.tensanpham);
            giohang.setSoluong(soluong);
            giohang.setMasanpham(allsanpham.getMasanpham());
            giohang.setImgsanpham(allsanpham.getImgsanpham());
            giohang.setBaohanh(allsanpham.getBaohanh());
            giohang.setXuatxu(allsanpham.getXuatxu());
            giohang.setMota(allsanpham.getMota());
            SERVER.manggiohang.add(giohang);
        }
        int totalItem = 0;
        for (int i =0;i<SERVER.manggiohang.size();i++){
            totalItem=totalItem+SERVER.manggiohang.get(i).soluong;
        }
        badge.setText(String.valueOf(totalItem));
    }

}