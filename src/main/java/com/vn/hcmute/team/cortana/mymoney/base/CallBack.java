package com.vn.hcmute.team.cortana.mymoney.base;

public interface CallBack<T> {
    
    void onSuccess(T result);
    
    void onFailure(Throwable e);
}
