package com.example.navigationdrawerneatroots.ui.News18;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.navigationdrawerneatroots.R;
import com.example.navigationdrawerneatroots.databinding.FragmentSlideshowBinding;
import com.example.navigationdrawerneatroots.ui.ABP_news1.WebViewController;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        WebView webview = root.findViewById(R.id.webViewslideshow);
        webview.loadUrl("https://www.news18.com/");



        //for viweing the website link in our app only we use this   //for this we create also WebviewClient.java
        webview.setWebViewClient(new WebViewController());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}