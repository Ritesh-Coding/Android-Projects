package com.example.navigationdrawerneatroots.ui.News24;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is news24 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}