package com.torkuds.androidstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

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

    private void initView(){
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(this);
        binding.recycler.setAdapter(adapter);

        viewModel.getNewsList().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> newsList) {
                if (newsList != null){
                    adapter.setData(newsList);
                }
            }
        });

        viewModel.loadData();
    }
}