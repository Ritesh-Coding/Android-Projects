package com.example.navigationdrawerneatroots.ui.News24;

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
import com.example.navigationdrawerneatroots.databinding.FragmentGalleryBinding;
import com.example.navigationdrawerneatroots.ui.ABP_news1.WebViewController;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        WebView webview = root.findViewById(R.id.webViewgallery);
        webview.loadUrl("https://news24online.com/");



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