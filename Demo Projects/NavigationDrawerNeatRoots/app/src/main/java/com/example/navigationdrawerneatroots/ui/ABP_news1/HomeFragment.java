package com.example.navigationdrawerneatroots.ui.ABP_news1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.navigationdrawerneatroots.R;
import com.example.navigationdrawerneatroots.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        WebView webview = root.findViewById(R.id.webViewABPNews);
        webview.loadUrl("https://www.abplive.com/");



        //for viweing the website link in our app only we use this   //for this we create also WebviewClient.java
        webview.setWebViewClient(new WebViewController());

        Button btn= root.findViewById(R.id.buttonhome);  //here we get the move the intent so mow all the contents are visible to
                                                //the new content that we have made
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here in place of internt we write this code
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_gallery);  //note that id should be same as we use in the mobilw fragment

            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}