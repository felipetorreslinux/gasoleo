package com.gasoleo.Views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gasoleo.R;

public class Central extends AppCompatActivity {


    Activity activity;
    ProgressDialog progressDialog;
    WebView webview;
    String URL = "https://gasplus.neconsult.myscriptcase.com";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_webview);

        activity = this;

        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(""+R.string.label_progress_webview);

        webview = findViewById(R.id.webview);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setSupportZoom(false);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(URL);

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        webview.goBack();
    }
}
