package com.heliwr.appbandogiadung.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.heliwr.appbandogiadung.MainActivity;
import com.heliwr.appbandogiadung.MainActivity_Login;
import com.heliwr.appbandogiadung.MainActivity_Register;
import com.heliwr.appbandogiadung.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Fragment_Account extends Fragment {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    AppCompatButton btndangxuat;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.account_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.opendrawer, R.string.closerawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean userLoggedIn;
                userLoggedIn = false;
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("userLoggedIn", userLoggedIn);
                startActivity(intent);
            }
        });
    }

    public void anhxa(View view) {
        drawerLayout = view.findViewById(R.id.drawer_layout);
        toolbar = view.findViewById(R.id.toolbar);
        navigationView = view.findViewById(R.id.navigationview_main);
        btndangxuat = view.findViewById(R.id.btndangxuat);
    }
}
