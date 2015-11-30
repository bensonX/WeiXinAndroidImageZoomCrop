package io.togoto.imagezoomcrop.demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2015/11/16.
 */
public class RxJavaDemo {


    //观察者
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {

        }
    };


    //继承observer的观察者多几个方法接口
    Subscriber<String> sbusriber = new Subscriber<String>() {


        @Override
        public void onStart() {
            super.onStart();
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {

        }
    };


    //被观察者
    Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {

            subscriber.onNext("onNext->");
            subscriber.onNext("onNext->");
            subscriber.onNext("onNext->");
            subscriber.onNext("onNext->");
            subscriber.onNext("onNext->");
            subscriber.onCompleted();

        }
    });

    public void justObservable() {


        observable.just("a", "b", "c");

        String[] words = {"a", "b", "c"};

        Observable.from(words);


    }

    public void setSubscribe() {


        observable.subscribe(observer);

        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {

                subscriber.onNext(null);
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onNext(Drawable drawable) {

            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }
        });


        Observable.just("images/logo.png") // 输入类型 String
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String filePath) { // 参数类型 String
                        return null; // 返回类型 Bitmap
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) { // 参数类型 Bitmap

                    }
                });


    }

    //打印1,2,3,4,5
    public static void setScheduler(final Context context) {

        Observable.just(1, 2, 3, 4, 5, 6)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return 1;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {




                    }
                });
    }

    //图片 id 取得图片并显示的例子
    public static void setImageView(final ImageView iamgeView,final int drawableId,final Activity activity) {

        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {

                Drawable drawable = activity.getResources().getDrawable(drawableId);
                //.getTheme().getDrawable(drawableId);//getDrawable(drawableId);
                subscriber.onNext(drawable);
                subscriber.onCompleted();

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        iamgeView.setImageDrawable(drawable);
                    }
                });

    }
    public static  void filter(Context context
    ){


    Integer[] integers={1,2,3,4,5,6,7,8,34534,9,0,0,435,56,6,5,4,3,2,34,54,5,4545,4,2,124,13,3123,21,4};


      List<Integer> funactionList=
              Observable.from(integers)
              .map(new Func1<Integer, Integer>() {
                  @Override
                  public Integer call(Integer integer) {
                      return integer*2;
                  }
              }).filter(new Func1<Integer, Boolean>() {
                  @Override
                  public Boolean call(Integer integer) {
                      return integer<10;
                  }
              }).toList().toBlocking().first();

        Toast.makeText(context, funactionList.toString()+ "", Toast.LENGTH_LONG).show();


    }


}
