package com.android.qzs.entertainmenttop.retrofit;

/**
 * Created by QZS
 */

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
    void onError(Throwable e);

}
