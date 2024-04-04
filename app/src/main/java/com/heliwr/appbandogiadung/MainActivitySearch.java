package com.heliwr.appbandogiadung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.heliwr.appbandogiadung.Adapter.RVALLSANPHAM_ADAPTER;
import com.heliwr.appbandogiadung.Adapter.RVSEARCH_ADAPTER;
import com.heliwr.appbandogiadung.Model.ALLSANPHAM;
import com.heliwr.appbandogiadung.Model.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivitySearch extends AppCompatActivity {
    RecyclerView rvsearch;
    SearchView searchView;
    ArrayList<ALLSANPHAM> searchdata = new ArrayList<>();
    RVSEARCH_ADAPTER  rvsearch_adapter;
    MaterialToolbar toolbarsanphamSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        toolbarsanphamSearch = findViewById(R.id.toolbarsanphamSearch);
        rvsearch = findViewById(R.id.rvSearch);
        searchView = findViewById(R.id.search_mainsanphamSearch);
        setSupportActionBar(toolbarsanphamSearch);
        toolbarsanphamSearch.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        rvsearch_adapter = new RVSEARCH_ADAPTER(MainActivitySearch.this, searchdata);
        rvsearch.setAdapter(rvsearch_adapter);
        GridLayoutManager allsanphamLayoutM=new GridLayoutManager(MainActivitySearch.this, 2 );
        rvsearch.setLayoutManager(allsanphamLayoutM);
        loadallsanpham();
        toolbarsanphamSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                rvsearch_adapter.getFilter().filter(newText);
                return true;
            }
        });
    }
    private void loadallsanpham() {
        Response.Listener<JSONArray>thanhcong= new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i =0;i<response.length();i++){
                    try {
                        JSONObject spoject = (JSONObject) response.get(i);
                        ALLSANPHAM allsanpham = new ALLSANPHAM(spoject.getInt("masanpham"),spoject.getString("tensanpham"),
                                spoject.getString("dongia"),spoject.getString("mota"), spoject.getString("imgsanpham"),
                                spoject.getString("macdsanpham"), spoject.getString("cuahangcon"), spoject.getString("xuatxu"),
                                spoject.getString("baohanh"));
                        searchdata.add(allsanpham);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivitySearch.this, "loi: "+e.getMessage(), Toast.LENGTH_LONG).show();
                        throw new RuntimeException(e);
                    }
                }
                rvsearch_adapter.notifyDataSetChanged();
            }
        };Response.ErrorListener thatbai=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (MainActivitySearch.this != null) {
                    Toast.makeText(MainActivitySearch.this, "Loi that bai:" + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        JsonArrayRequest jsonArrayRequest
                = new JsonArrayRequest(SERVER.urllayallsanpham,thanhcong, thatbai);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivitySearch.this);
        // Thêm đoạn mã dưới đây để khởi tạo rvchudeAdapter nếu nó chưa được khởi tạo
        requestQueue.add(jsonArrayRequest);
    }
}