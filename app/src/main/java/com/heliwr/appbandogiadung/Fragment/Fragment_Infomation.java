package com.heliwr.appbandogiadung.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.heliwr.appbandogiadung.Adapter.RVINFOMATION_ADAPTER;
import com.heliwr.appbandogiadung.Adapter.RVSPTHIETBI_ADAPTER;
import com.heliwr.appbandogiadung.Model.INFOMATION;
import com.heliwr.appbandogiadung.Model.SERVER;
import com.heliwr.appbandogiadung.Model.SPTHIETBI;
import com.heliwr.appbandogiadung.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_Infomation extends Fragment {
    RecyclerView rvInfomation;
    ArrayList<INFOMATION> infomationsdata= new ArrayList<>();
    RVINFOMATION_ADAPTER rvinfomation_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.infomation_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        rvinfomation_adapter = new RVINFOMATION_ADAPTER(getContext(), infomationsdata);
        rvInfomation.setAdapter(rvinfomation_adapter);
        LinearLayoutManager infomationLayoutM = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvInfomation.setLayoutManager(infomationLayoutM);
        rvInfomation.setHasFixedSize(true);
        loadspthietbi();
    }
    private void loadspthietbi() {
        infomationsdata.clear();
        Response.Listener<JSONArray>thanhcong= new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i =0;i<response.length();i++){
                    try {
                        JSONObject ifoject = (JSONObject) response.get(i);
                        INFOMATION im = new INFOMATION(ifoject.getString("maif"),ifoject.getString("noidungif"),
                                ifoject.getString("quaif"), ifoject.getString("motaif"));
                        infomationsdata.add(im);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "loi: "+e.getMessage(), Toast.LENGTH_LONG).show();
                        throw new RuntimeException(e);
                    }
                }
                rvinfomation_adapter.notifyDataSetChanged();
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
                = new JsonArrayRequest(SERVER.urllayinfomation,thanhcong, thatbai);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        // Thêm đoạn mã dưới đây để khởi tạo rvchudeAdapter nếu nó chưa được khởi tạo
        requestQueue.add(jsonArrayRequest);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void anhxa(View view){
        rvInfomation = view.findViewById(R.id.rvInfomation);
    }
}
