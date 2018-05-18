package com.qingheyang.testjr;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * 项目名称:
 * 类创建者:QHY.
 * 时间:2017/12/21
 * 类说明:
 */

public class RxTools {
    public static void asyncToSync() {

        /**
         * 因为RxJava2已经没有了immediate线程，所以要自己做一个线
         */

        final Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(new ScheduledThreadPoolExecutor(1) {
                    @Override
                    public void execute(@NonNull Runnable runnable) {
                        runnable.run();
                    }
                });
            }
        };

        /**
         * 将自己写的线程转换成为一个调度线程
         */
        Function<Callable<Scheduler>, Scheduler> schedulerFunc = new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return immediate;
            }


        };

        /**
         * 将单元测试的所有调度线程都统一一个线程
         */
        RxJavaPlugins.reset();
        RxJavaPlugins.setInitIoSchedulerHandler(schedulerFunc);
        RxJavaPlugins.setInitComputationSchedulerHandler(schedulerFunc);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerFunc);
        RxAndroidPlugins.reset();
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerFunc);
    }

    public static void resetPlugins(){
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();
    }

}
