package com.winniecake.mvvmjava.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.winniecake.mvvmjava.model.MainRepository;
import com.winniecake.mvvmjava.model.MyData;

import java.util.List;

/**
 * 負責接收view傳來的任何需求, 跟Repository要求資料
 * ViewModel不會被View事件影響, ex:Activity被回收或螢幕旋轉等不會造成ViewModel改變
 * ViewModel不能有views的reference, 否則會memory leak
 * 用context要拿ApplicationContext避免memory leak
 */
public class MainViewModel extends ViewModel {

    public final MutableLiveData<List<MyData>> mData = new MutableLiveData<>();
    public final MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();

    private final MainRepository mRepository;

    public MainViewModel() {
        mRepository = new MainRepository();
        mIsLoading.setValue(false);
    }

    public void requestData() {
        mIsLoading.setValue(true);
        mRepository.requestData(dataList -> {
            mData.setValue(dataList);
            mIsLoading.setValue(false);
        });
    }
}