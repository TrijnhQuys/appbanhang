package com.heliwr.appbandogiadung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.heliwr.appbandogiadung.Adapter.RVALLSANPHAM_ADAPTER;
import com.heliwr.appbandogiadung.Model.ALLSANPHAM;
import com.heliwr.appbandogiadung.Model.SERVER;
import com.heliwr.appbandogiadung.Model.SPTHIETBI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity_Sanphamtheothietbi extends AppCompatActivity {
    MaterialToolbar toolbarsanphamtheothietbi;
    SearchView searchViewsanphamtheothietbi;
    RecyclerView recyclerViewsanphamtheothietbi;

    ArrayList<ALLSANPHAM> spthietbi_data=new ArrayList<>();
    RVALLSANPHAM_ADAPTER rvsp_adapter;
    SPTHIETBI cd;
    String macd="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sanphamtheo_thietbi);
        toolbarsanphamtheothietbi = findViewById(R.id.toolbarsanphamtheothietbi);
        searchViewsanphamtheothietbi = findViewById(R.id.search_mainsanphamtheothietbi);
        recyclerViewsanphamtheothietbi = findViewById(R.id.rvsanphamtheothietbi);

        setSupportActionBar(toolbarsanphamtheothietbi);
        Intent intent = getIntent();
        cd =(SPTHIETBI)intent.getSerializableExtra("spthietbiObject");
        rvsp_adapter = new RVALLSANPHAM_ADAPTER(this, spthietbi_data);
        recyclerViewsanphamtheothietbi.setAdapter(rvsp_adapter);
        recyclerViewsanphamtheothietbi.setLayoutManager(new GridLayoutManager(this, 2));

        toolbarsanphamtheothietbi.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        toolbarsanphamtheothietbi.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            onBackPressed();
            }
        });
        loadsanpham();
    }
    private void loadsanpham() {
        Response.Listener<String>thanhcong = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;

                try {
                    jsonArray = new JSONArray(response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                for (int i =0;i<jsonArray.length();i++){

                    JSONObject spoject = null;
                    try {
                        spoject = jsonArray.getJSONObject(i);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    ALLSANPHAM sp = null;
                    try {
                        sp = new ALLSANPHAM(spoject.getInt("masanpham"), spoject.getString("tensanpham"),
                                spoject.getString("dongia"),spoject.getString("mota"),
                                spoject.getString("imgsanpham"),spoject.getString("macdsanpham"),
                                spoject.getString("cuahangcon"),spoject.getString("xuatxu"),spoject.getString("baohanh"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    spthietbi_data.add(sp);
                }
                //báo adapter load dữ liệu lên
                rvsp_adapter.notifyDataSetChanged();
            }
        };

        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_Sanphamtheothietbi.this, "loi that bai: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest stringRequest
                = new StringRequest(Request.Method.POST,SERVER.urllayallsanphamtheosp,thanhcong, thatbai)
        {
            protected Map<String, String> getParams()throws AuthFailureError{
                HashMap params = new HashMap();
                params.put("macdsanpham", cd.macdsanpham);
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        // Thêm đoạn mã dưới đây để khởi tạo rvchudeAdapter nếu nó chưa được khởi tạo
        requestQueue.add(stringRequest);

    }
}