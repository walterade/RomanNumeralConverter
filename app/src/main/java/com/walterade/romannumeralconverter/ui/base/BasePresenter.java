package com.walterade.romannumeralconverter.ui.base;

/**
 * Created by Walter on 10/10/17.
 */


import android.os.Bundle;

import java.lang.ref.WeakReference;

import androidx.annotation.CallSuper;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getView().
 */
public abstract class BasePresenter<T extends BaseView> implements Presenter<T> {

    Class<? extends BaseView> cls;
    private WeakReference<T> emptyView;
    private T mMvpView;

    @Override @CallSuper
    public void attachView(T mvpView) {
        cls = mvpView.getClass();
        emptyView();
        mMvpView = mvpView;
    }

    @CallSuper
    public void attachView(T mvpView, Bundle savedInstanceState) {
        attachView(mvpView);
        if (savedInstanceState != null) restoreState(savedInstanceState);
    }

    public void restoreState(Bundle savedInstanceState) {}


    WeakReference<T> emptyView() {
        try {
            if (emptyView == null || emptyView.get() == null && cls != null) {
                emptyView = new WeakReference<>((T) cls.newInstance());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return emptyView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getView() {
        if (mMvpView == null) return emptyView().get();
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
