package com.heliwr.appbandogiadung.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.heliwr.appbandogiadung.Adapter.RVINFOMATION_ADAPTER;
import com.heliwr.appbandogiadung.MainActivity_Login;
import com.heliwr.appbandogiadung.MainActivity_Register;
import com.heliwr.appbandogiadung.Model.INFOMATION;
import com.heliwr.appbandogiadung.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_Message extends Fragment {
    EditText edtguiShopA;
    AppCompatButton btnguiShopA;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         View view =inflater.inflate(R.layout.message_fragment_layout, container,false);
            return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);

        btnguiShopA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guiShopAwithVolley();
            }
        });
    }

    private void guiShopAwithVolley() {
        String url = "http://192.168.47.193:3000/gui-tin";
        //dữ liệu gửi lên service
        JSONObject jsonBody = new JSONObject();
        //đầu tiên mình sẽ đặt giá trị khi nhập vào đối tượng trước
        try {
            //đặt giá trị của các thuộc tính trong một đối tượng JSON
            jsonBody.put("noidungShopA", edtguiShopA.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        sử dụng để gửi yêu cầu HTTP và nhận phản hồi dưới dạng đối tượng JSON từ máy chủ.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean insert = response.getBoolean("insert");
                            Toast.makeText(getContext(), "Gửi thành công", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Gửi không thành công", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void anhxa(View view){

        edtguiShopA = view.findViewById(R.id.edtShopA);

        btnguiShopA=view.findViewById(R.id.btnguiShopA);
    }
}
