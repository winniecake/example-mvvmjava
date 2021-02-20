package com.winniecake.mvvmjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.winniecake.mvvmjava.model.MyData;
import com.winniecake.mvvmjava.viewmodel.MainViewModel;
import com.winniecake.mvvmjava.R;
import com.winniecake.mvvmjava.databinding.ActivityMainBinding;

import java.util.ArrayList;

/**
 *
 * View -> ViewModel -> Repository
 *                   <-
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // 取得ViewModel,生命週期會持續到 MainActivity destroy, MainActivity還在活動ViewModel不會被清除
        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setMainViewModel(mainViewModel);
        binding.setLifecycleOwner(this);

        DataListAdapter adapter = new DataListAdapter(this, new ArrayList<>());
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapter);

        // 觀察者模式
        // 非同步資料取得/處理mData後, 會callback來完成UI處理
        mainViewModel.mData.observe(this, s -> {
            adapter.updateList((ArrayList<MyData>) s);
            binding.list.setVisibility(View.VISIBLE);
        });

        // 非同步資料取得/處理mIsLoading後, 會callback來完成UI處理
        mainViewModel.mIsLoading.observe(this, isLoading -> {
            if (isLoading) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.list.setVisibility(View.GONE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }
        });

    }
}