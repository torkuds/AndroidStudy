package com.torkuds.androidstudy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.torkuds.androidstudy.databinding.ActivityMainBinding;
import com.torkuds.androidstudy.entity.News;
import com.torkuds.androidstudy.ui.news.NewsAdapter;
import com.torkuds.androidstudy.ui.news.NewsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private NewsViewModel viewModel;

    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        viewModel = new NewsViewModel(getApplication());

        initView();

    }

    private void initView() {
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(this);
        binding.recycler.setAdapter(adapter);

        viewModel.getNewsList().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> newsList) {
                if (newsList != null) {
                    adapter.setData(newsList);
                }
            }
        });

        viewModel.loadData();
    }

    public void onClick(View view) {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA}, 60);
        } else {
            //权限已经被授予，在这里直接写要执行的相应方法即可
//                    ScannerActivity.gotoActivity(MainActivity.this,
//                            checkBox.isChecked(), laserMode, scanMode, !toggleButton.isChecked()
//                            , toggleButton1.isChecked(), toggleButton2.isChecked());
            startActivityForResult(new Intent(this, ScannerActivity.class), 10010);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10) {
            String result = data.getStringExtra("result");
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }
}