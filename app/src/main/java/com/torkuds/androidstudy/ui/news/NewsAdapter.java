package com.torkuds.androidstudy.ui.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.torkuds.androidstudy.R;
import com.torkuds.androidstudy.databinding.ItemNewsListBinding;
import com.torkuds.androidstudy.entity.News;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private List<News> mData;

    private LayoutInflater inflater;

//    private Context context;

    public NewsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        mData = new ArrayList<>();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsListBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_news_list, parent, false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = mData.get(position);
        holder.binding.setNews(news);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<News> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        private ItemNewsListBinding binding;

        public NewsViewHolder(@NonNull ItemNewsListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ItemNewsListBinding getBinding() {
            return binding;
        }
    }

    @BindingAdapter("url")
    public static void setImage(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
