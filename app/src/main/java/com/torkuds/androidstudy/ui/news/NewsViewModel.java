package com.torkuds.androidstudy.ui.news;

import android.app.Application;

import com.torkuds.androidstudy.database.NewsDatabase;
import com.torkuds.androidstudy.database.NewsDao;
import com.torkuds.androidstudy.entity.News;
import com.torkuds.androidstudy.net.Api;
import com.torkuds.module_common.net.HttpResult;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NewsViewModel extends AndroidViewModel {
    private final MutableLiveData<List<News>> newsList = new MutableLiveData<>();

    private final NewsDao newsDao;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public NewsViewModel(@NonNull Application application) {
        super(application);
        NewsDatabase db = NewsDatabase.getInstance(application);
        newsDao = db.newsDao();
        mDisposable.add(newsDao.getAllNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsList::setValue, throwable -> {

                }));

    }


    public void loadData() {
        Observable<HttpResult<List<News>>> observable = Api.getService().getAllNews(1);
        mDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listHttpResult -> {
                    if (listHttpResult.isSuccess()) {
                        newsList.setValue(listHttpResult.getData());
                        Completable completable = newsDao.addAll(listHttpResult.getData());
                        completable
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new CompletableObserver() {
                                    @Override
                                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }

                                    @Override
                                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                                    }
                                });
                    }
                }, throwable -> {

                }));
    }

    public MutableLiveData<List<News>> getNewsList() {
        return newsList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }
}
