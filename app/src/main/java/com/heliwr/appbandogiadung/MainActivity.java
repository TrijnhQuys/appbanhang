package com.heliwr.appbandogiadung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.heliwr.appbandogiadung.Fragment.Fragment_Account;
import com.heliwr.appbandogiadung.Fragment.Fragment_Home;
import com.heliwr.appbandogiadung.Fragment.Fragment_Infomation;
import com.heliwr.appbandogiadung.Fragment.Fragment_Message;
import com.heliwr.appbandogiadung.Model.SERVER;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    LinearLayout containerDialofLayoutAccoount, containerDialogLayoutInfomation, containerDialogLayoutMassage;
    Toolbar toolbar;
    private boolean userLoggedIn;
    AppCompatButton btnsearch;
    BadgeDrawable badgeHome, badgeMessage, badgeInfomation, badgeAccount;
    Fragment_Message fragment_message =new Fragment_Message();
    Fragment_Infomation fragment_infomation = new Fragment_Infomation();
    Fragment_Account fragment_account= new Fragment_Account();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fragment_Home fragment_home;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        bottomNavigationView.setBackground(null);
        fragment_home=new Fragment_Home();
        loadFragment(new Fragment());
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        //Đối số thứ nhất là tên khóa mà chúng ta muốn lấy giá trị từ intent.
        //Đối số thứ hai là giá trị mặc định sẽ được trả về nếu không tìm thấy khóa trong intent.
        userLoggedIn = intent.getBooleanExtra("userLoggedIn", false);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.mnhome:
                        loadFragment(fragment_home);
                        if (badgeHome != null) {
                            badgeHome.setVisible(false);
                        };
                        toolbar.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mnmessage:
                        if (userLoggedIn){
                            loadFragment(fragment_message);
                            if (badgeMessage != null) {
                                badgeMessage.setVisible(true);
                            }
                            toolbar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "Message", Toast.LENGTH_SHORT).show();
                        }else {
                            showLoginDialogMessage();
                        }

                        break;
                    case R.id.mninfomation:
                        if (userLoggedIn){
                            loadFragment(fragment_infomation);
                            if (badgeInfomation != null) {
                                badgeInfomation.setVisible(true);
                            }
                            toolbar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "Notification", Toast.LENGTH_SHORT).show();
                        }else {
                            showLoginDialogInfomation();
                        }
                        break;
                    case R.id.mnaccount:
                        if (userLoggedIn) {
                            loadFragment(fragment_account);
                            toolbar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "Account", Toast.LENGTH_SHORT).show();
                        } else {
                            showLoginDialogAccount();
                        }
                        break;
                }
                // Hiển thị lại thanh Toolbar khi quay lại trang chủ
                if (id != R.id.mnhome) {
                    toolbar.setVisibility(View.GONE);
                }
                return true;
            }




        });
        loadFragment(fragment_home);
        LoadBagedeDrawable();
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivitySearch.class);
                startActivity(intent);
            }
        });
    }
    private void showLoginDialogMessage() {
        containerDialogLayoutMassage =findViewById(R.id.containerDialogLayoutMessage);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_message, containerDialogLayoutMassage);
        builder.setView(view);
        TextView  tvTitle1=view.findViewById(R.id.tvTitle1);
        TextView tvTitle2=view.findViewById(R.id.tvTitle2);
        AppCompatButton btnYES=view.findViewById(R.id.btnYES);
        AppCompatButton btnNO=view.findViewById(R.id.btnNO);
        AlertDialog alertDialog =builder.create();
        tvTitle1.setText("BẠN CHƯA ĐĂNG NHẬP");
        tvTitle2.setText("Đăng nhập để liên hệ đến shop");
        btnYES.setText("YES");
        btnNO.setText("NO");
        if (alertDialog.getWindow()!=null)
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity_Login.class);
                startActivity(intent);
            }
        });
        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private void showLoginDialogInfomation() {
        containerDialogLayoutInfomation =findViewById(R.id.containerDialogLayoutInfomation);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_infomation, containerDialogLayoutInfomation);
        builder.setView(view);
        TextView  tvTitle1=view.findViewById(R.id.tvTitle1);
        TextView tvTitle2=view.findViewById(R.id.tvTitle2);
        AppCompatButton btnYES=view.findViewById(R.id.btnYES);
        AppCompatButton btnNO=view.findViewById(R.id.btnNO);
        AlertDialog alertDialog =builder.create();
        tvTitle1.setText("BẠN CHƯA ĐĂNG NHẬP");
        tvTitle2.setText("Đăng nhập để nhận các thông báo của bạn");
        btnYES.setText("YES");
        btnNO.setText("NO");
        if (alertDialog.getWindow()!=null)
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity_Login.class);
                startActivity(intent);
            }
        });
        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private void showLoginDialogAccount() {
        containerDialofLayoutAccoount =findViewById(R.id.containerDialogLayoutAccount);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_login, containerDialofLayoutAccoount);
        builder.setView(view);
        TextView  tvTitle1=view.findViewById(R.id.tvTitle1);
        TextView tvTitle2=view.findViewById(R.id.tvTitle2);
        AppCompatButton btnYES=view.findViewById(R.id.btnYES);
        AppCompatButton btnNO=view.findViewById(R.id.btnNO);
        AlertDialog alertDialog =builder.create();
        tvTitle1.setText("BẠN CHƯA ĐĂNG NHẬP");
        tvTitle2.setText("Đăng nhập để sử dụng các chức năng tốt hơn không?");
        btnYES.setText("YES");
        btnNO.setText("NO");
        if (alertDialog.getWindow()!=null)
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity_Login.class);
                startActivity(intent);
            }
        });
        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public void anhxa(){
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottomnavigationview_main);
        toolbar = findViewById(R.id.toolbar);
        btnsearch = findViewById(R.id.search_main);
        if (SERVER.manggiohang==null){
            SERVER.manggiohang=new ArrayList<>();
        }

    }
    private void LoadBagedeDrawable() {
//        badgeHome = bottomNavigationView.getOrCreateBadge(R.id.mnhome);
////        badgeHome.setNumber(2);
//
//        badgeMessage = bottomNavigationView.getOrCreateBadge(R.id.mnmessage);
////        badgeMessage.setNumber(3);
//
//        badgeInfomation = bottomNavigationView.getOrCreateBadge(R.id.mninfomation);
////        badgeInfomation.setNumber(2);
//        badgeAccount = bottomNavigationView.getOrCreateBadge(R.id.mnaccount);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void loadFragment(Fragment f){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main,f);
        transaction.commit();
    }
}