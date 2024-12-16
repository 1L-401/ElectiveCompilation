package com.example.electivecompilation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class GuidedTen extends Fragment {

    private WebView webView;
    private EditText urlEditText;
    private Button loadButton;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guided_ten, container, false);

        webView = view.findViewById(R.id.webView);
        urlEditText = view.findViewById(R.id.actvURLGE10);
        loadButton = view.findViewById(R.id.btnOpenURLGE10);

        // Configure WebView settings
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        loadButton.setOnClickListener(v -> {
            String url = urlEditText.getText().toString().trim();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://" + url;
            }
            loadWebPage(url);
        });

        return view;
    }

    private void loadWebPage(String url) {
        try {
            webView.loadUrl(url);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Failed to load webpage", Toast.LENGTH_SHORT).show();
        }
    }
}