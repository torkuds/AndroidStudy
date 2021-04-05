package com.torkuds.androidstudy.viewmodel;

import android.app.Application;

import com.torkuds.androidstudy.database.NewsDao;
import com.torkuds.androidstudy.entity.News;
import com.torkuds.androidstudy.net.Api;
import com.torkuds.module_common.net.HttpResult;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MViewModel extends AndroidViewModel {

    private MutableLiveData<List<News>> newsList = new MutableLiveData<>();

    private NewsDao newsDao;

    public MViewModel(@NonNull Application application) {
        super(application);
    }


    public void loadData(){
        Observable<HttpResult<List<News>>> observable = Api.getService().getAllNews(1);
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HttpResult<List<News>>>() {
                    @Override
                    public void accept(HttpResult<List<News>> listHttpResult) throws Exception {
                        if (listHttpResult.isSuccess()){
                            newsList.setValue(listHttpResult.getData());

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
