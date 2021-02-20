package com.winniecake.mvvmjava.model;

import android.os.Handler;

import java.util.ArrayList;

/**
 * 取得來源資料
 */
public class MainRepository {

    public void requestData(DataCallback callback) {
        new Handler().postDelayed(() -> {
            ArrayList<MyData> dataList = new ArrayList<>();
            dataList.add(new MyData("1", "apple"));
            dataList.add(new MyData("2", "orange"));
            dataList.add(new MyData("3", "strawberry"));

            callback.onCallback(dataList);
        }, 2000);
    }

    public interface DataCallback {
        void onCallback(ArrayList<MyData> l);
    }
}
