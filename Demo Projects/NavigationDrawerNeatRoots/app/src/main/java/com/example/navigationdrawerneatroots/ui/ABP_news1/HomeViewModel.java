package com.example.navigationdrawerneatroots.ui.ABP_news1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Abp news fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}