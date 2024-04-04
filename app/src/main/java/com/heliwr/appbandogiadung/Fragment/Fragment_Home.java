package com.heliwr.appbandogiadung.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.heliwr.appbandogiadung.Adapter.RVALLSANPHAM_ADAPTER;
import com.heliwr.appbandogiadung.Adapter.RVSPGIADUNGBEP_ADAPTER;
import com.heliwr.appbandogiadung.Adapter.RVSPSUCKHOE_ADAPTER;
import com.heliwr.appbandogiadung.Adapter.RVSPTHIETBI_ADAPTER;
import com.heliwr.appbandogiadung.Model.ALLSANPHAM;
import com.heliwr.appbandogiadung.Model.SPGIADUNGBEP;
import com.heliwr.appbandogiadung.Model.SPSUCKHOE;
import com.heliwr.appbandogiadung.Model.SPTHIETBI;
import com.heliwr.appbandogiadung.Model.SERVER;
import com.heliwr.appbandogiadung.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_Home extends Fragment {
    ViewFlipper viewFlipper;
    RecyclerView rvspthietbi, rvspsuckhoe, rvspgiadungbep, rvallsanpham;
    ArrayList<SPTHIETBI> spthietbidata = new ArrayList<>();
    ArrayList<SPSUCKHOE> spsuckhoedata=new ArrayList<>();
    ArrayList<SPGIADUNGBEP> spgiadungbepdata=new ArrayList<>();

    ArrayList<ALLSANPHAM> allsanphamdata = new ArrayList<>();

    RVSPTHIETBI_ADAPTER rvspthietbiAdapter;
    RVSPSUCKHOE_ADAPTER rvspsuckhoeAdapter;
    RVSPGIADUNGBEP_ADAPTER rvspgiadungbepAdapter;
    RVALLSANPHAM_ADAPTER rvallsanphamAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        rvspthietbiAdapter = new RVSPTHIETBI_ADAPTER(getContext(), spthietbidata);
        rvspthietbi.setAdapter(rvspthietbiAdapter);
        LinearLayoutManager spthietbiLayoutM = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        rvspthietbi.setLayoutManager(spthietbiLayoutM);
        rvspthietbi.setHasFixedSize(true);

        rvspsuckhoeAdapter = new RVSPSUCKHOE_ADAPTER(getContext(), spsuckhoedata);
        rvspsuckhoe.setAdapter(rvspsuckhoeAdapter);
        LinearLayoutManager spsuckhoeLayoutM = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvspsuckhoe.setLayoutManager(spsuckhoeLayoutM);
        rvspsuckhoe.setHasFixedSize(true);

        rvspgiadungbepAdapter = new RVSPGIADUNGBEP_ADAPTER(getContext(), spgiadungbepdata);
        rvspgiadungbep.setAdapter(rvspgiadungbepAdapter);
        LinearLayoutManager spgiadungbepLayoutM = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvspgiadungbep.setLayoutManager(spgiadungbepLayoutM);
        rvspgiadungbep.setHasFixedSize(true);

        rvallsanphamAdapter = new RVALLSANPHAM_ADAPTER(getContext(), allsanphamdata);
        rvallsanpham.setAdapter(rvallsanphamAdapter);
        GridLayoutManager allsanphamLayoutM=new GridLayoutManager(getContext(), 2 );
        rvallsanpham.setLayoutManager(allsanphamLayoutM);

        loadspthietbi();
        loadspsuckhoe();
        loadspgiadungbep();
        loadallsanpham();
        loadviewFlipper();
    }

    private void loadspthietbi() {
        Response.Listener<JSONArray>thanhcong= new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i =0;i<response.length();i++){
                    try {
                        JSONObject spoject = (JSONObject) response.get(i);
                        SPTHIETBI spthietbi = new SPTHIETBI(spoject.getString("macdthietbi"),spoject.getString("tensanpham"),
                                spoject.getString("imgsanpham"));
                        spthietbidata.add(spthietbi);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "loi: "+e.getMessage(), Toast.LENGTH_LONG).show();
                        throw new RuntimeException(e);
                    }
                }
                rvspthietbiAdapter.notifyDataSetChanged();
            }
        };Response.ErrorListener thatbai=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Loi that bai:" + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        JsonArrayRequest jsonArrayRequest
                = new JsonArrayRequest(SERVER.urllayspthietbi,thanhcong, thatbai);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        // Thêm đoạn mã dưới đây để khởi tạo rvchudeAdapter nếu nó chưa được khởi tạo
        requestQueue.add(jsonArrayRequest);
    }
    private void loadspsuckhoe() {
        Response.Listener<JSONArray>thanhcong= new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i =0;i<response.length();i++){
                    try {
                        JSONObject spoject = (JSONObject) response.get(i);
                        SPSUCKHOE spsuckhoe = new SPSUCKHOE(spoject.getString("macdsuckhoe"),spoject.getString("tensanpham"),
                                spoject.getString("imgsanpham"));
                        spsuckhoedata.add(spsuckhoe);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "loi: "+e.getMessage(), Toast.LENGTH_LONG).show();
                        throw new RuntimeException(e);
                    }
                }
                rvspsuckhoeAdapter.notifyDataSetChanged();
            }
        };Response.ErrorListener thatbai=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Loi that bai:" + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        JsonArrayRequest jsonArrayRequest
                = new JsonArrayRequest(SERVER.urllayspsuckhoe,thanhcong, thatbai);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        // Thêm đoạn mã dưới đây để khởi tạo rvchudeAdapter nếu nó chưa được khởi tạo
        requestQueue.add(jsonArrayRequest);
    }
    private void loadspgiadungbep() {
        Response.Listener<JSONArray>thanhcong= new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i =0;i<response.length();i++){
                    try {
                        JSONObject spoject = (JSONObject) response.get(i);
                        SPGIADUNGBEP spgiadungbep = new SPGIADUNGBEP(spoject.getString("macdgiadung"),spoject.getString("tensanpham"),
                                spoject.getString("imgsanpham"));
                        spgiadungbepdata.add(spgiadungbep);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "loi: "+e.getMessage(), Toast.LENGTH_LONG).show();
                        throw new RuntimeException(e);
                    }
                }
                rvspgiadungbepAdapter.notifyDataSetChanged();
            }
        };Response.ErrorListener thatbai=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Loi that bai:" + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        JsonArrayRequest jsonArrayRequest
                = new JsonArrayRequest(SERVER.urllayspgiadungbep,thanhcong, thatbai);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        // Thêm đoạn mã dưới đây để khởi tạo rvchudeAdapter nếu nó chưa được khởi tạo
        requestQueue.add(jsonArrayRequest);
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
                        allsanphamdata.add(allsanpham);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "loi: "+e.getMessage(), Toast.LENGTH_LONG).show();
                        throw new RuntimeException(e);
                    }
                }
                rvallsanphamAdapter.notifyDataSetChanged();
            }
        };Response.ErrorListener thatbai=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Loi that bai:" + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        JsonArrayRequest jsonArrayRequest
                = new JsonArrayRequest(SERVER.urllayallsanpham,thanhcong, thatbai);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        // Thêm đoạn mã dưới đây để khởi tạo rvchudeAdapter nếu nó chưa được khởi tạo
        requestQueue.add(jsonArrayRequest);
    }
    public void loadviewFlipper() {
        ArrayList<String> slide = new ArrayList<>();
        slide.add(SERVER.urllayimagesslide + "slide1.jpg");
        slide.add(SERVER.urllayimagesslide + "slide2.jpg");
        slide.add(SERVER.urllayimagesslide + "slide3.jpg");
        // Thêm mã để hiển thị slide trên viewFlipper ở đây
        for (int i = 0;i< slide.size();i++){
            ImageView img = new ImageView(getContext());
            Picasso.get().load(slide.get(i)).into(img);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(img);
        }
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
    }

    public void anhxa(View view) {
        viewFlipper = view.findViewById(R.id.viewFlipper);
        rvspthietbi = view.findViewById(R.id.rvthietbigiadinh);
        rvspsuckhoe = view.findViewById(R.id.rvsuckhoegiadinh);
        rvspgiadungbep=view.findViewById(R.id.rvgiadungnhabep);
        rvallsanpham = view.findViewById(R.id.rvallsanpham);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spthietbidata.clear();
        spgiadungbepdata.clear();
        spsuckhoedata.clear();
    }
}