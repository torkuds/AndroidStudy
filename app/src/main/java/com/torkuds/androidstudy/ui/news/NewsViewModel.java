package com.torkuds.androidstudy.ui.news;

import com.torkuds.androidstudy.entity.News;
import com.torkuds.androidstudy.net.Api;
import com.torkuds.module_common.net.HttpResult;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {
    private MutableLiveData<List<News>> newsList = new MutableLiveData<>();

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

    public MutableLiveData<List<News>> getNewsList() {
        return newsList;
    }

    public void setNewsList(MutableLiveData<List<News>> newsList) {
        this.newsList = newsList;
    }
}
