package com.qingheyang.testjr;


import android.text.TextUtils;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * project: TestJR
 * package: com.qingheyang.testjr
 * creater: qingheyang
 * date: 2018/5/17
 * describe:
 */
public class MainPresenter {
    MainActivity activity;

    public MainPresenter() {
    }

    public MainPresenter(MainActivity activity) {
        this.activity = activity;
    }

    /**
     * 获取user信息
     */
    public void getUser(final String path) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                String json = HttpUtils.httpGet(path);//请求网络
                if (json!=null) {//判断服务器返回数据是否为空
                    e.onNext(json);
                }else {
                    e.onError(new Exception("获取user失败"));
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);//因为junit输出日志不能用log，所以改用sys。
                        //Gson gson = new Gson();
                       // User user = gson.fromJson(s, User.class);
                        //处理数据成功的话，交给activity的回调
                        //activity.getDateSuccess(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //失败交给activity给用户返回失败信息
                        //activity.getDateFailed(0x001);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
