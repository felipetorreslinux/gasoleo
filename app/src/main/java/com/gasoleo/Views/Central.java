package com.gasoleo.Views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.gasoleo.Models.Arquivo;
import com.gasoleo.R;

import java.io.File;

public class Central extends AppCompatActivity {

    static final int REQUEST_FILE_INPUT = 72;

    Activity activity;
    ProgressDialog progressDialog;
    WebView webview;
    String URL = "https://gasplus.neconsult.myscriptcase.com";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_webview);
        activity = this;

        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Carregando...");

        webview = findViewById(R.id.webview);
        webview.addJavascriptInterface(new Arquivo(this), "Android");
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.loadUrl(URL);

        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                request.grant(request.getResources());
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Escolha"), REQUEST_FILE_INPUT);
                return false;
            }
        });

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_FILE_INPUT && resultCode == RESULT_OK && data != null && data.getData() != null ) {
            File arquivo = new File(data.getData().getPath());
        }
    }

    @Override
    public void onBackPressed() {
        webview.goBack();
    }
}
