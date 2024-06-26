package com.heliwr.appbandogiadung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.heliwr.appbandogiadung.Adapter.RVGIOHANG_ADAPTER;
import com.heliwr.appbandogiadung.Model.EventBus.TinhTongEvent;
import com.heliwr.appbandogiadung.Model.GIOHANG;
import com.heliwr.appbandogiadung.Model.SERVER;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivityGioHang extends AppCompatActivity {
    TextView textViewgiohangtrong, texttongtien;
    MaterialToolbar toolbargiohang;
    RecyclerView recyclerView;
    RVGIOHANG_ADAPTER rvgiohang_adapter;
    Button btnmua;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gio_hang);
        anhxa();
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        if (SERVER.manggiohang.size()==0){
            textViewgiohangtrong.setVisibility(View.VISIBLE);
        }else {
            rvgiohang_adapter= new RVGIOHANG_ADAPTER(getApplicationContext(), SERVER.manggiohang);
            recyclerView.setAdapter(rvgiohang_adapter);
        }
        tinhTongTien();

    }

    private void tinhTongTien() {
        long tongtiensp=0;
        for (int i =0;i<SERVER.manggiohang.size();i++){
            tongtiensp+=(SERVER.manggiohang.get(i).getDongia()*SERVER.manggiohang.get(i).getSoluong());
        };
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(tongtiensp);
        texttongtien.setText(formattedPrice);
    }


    public void anhxa(){
        textViewgiohangtrong = findViewById(R.id.tvgiohangtrong);
        toolbargiohang=findViewById(R.id.toolbargiohang);
        recyclerView = findViewById(R.id.rvgiohang);
        btnmua=findViewById(R.id.btnmuahang);
        texttongtien = findViewById(R.id.tvtongtien);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventTinhtong(TinhTongEvent event){
        if (event!=null){
            tinhTongTien();
        }
    }
}