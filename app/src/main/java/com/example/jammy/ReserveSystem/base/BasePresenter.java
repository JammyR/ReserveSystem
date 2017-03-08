package com.example.jammy.ReserveSystem.base;

/**
 * Created by Jammy on 2017/1/13.
 */

public abstract class BasePresenter<V, M> {

    public M mModel;
    public V mView;

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public abstract void onStart();

    public String getErrorCode(Throwable throwable){
        String[] str = throwable.toString().split(",");
        String[] temp = str[0].split(":");
        String errorCode = temp[1];
        return errorCode;
    }
}
