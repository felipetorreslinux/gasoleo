package com.gasoleo.Models;

import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;

public class Arquivo {

    String name;
    String path;
    Activity activity;

    public Arquivo(Activity activity) {
        this.name = name;
        this.path = path;
        this.activity = activity;
    }

    @JavascriptInterface
    public String getName() {
        return name;
    }

    @JavascriptInterface
    public void setName(String name) {
        this.name = name;
    }

    @JavascriptInterface
    public String getPath() {
        return path;
    }

    @JavascriptInterface
    public void setPath(String path) {
        this.path = path;
    }

    @JavascriptInterface
    public void openFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Escolha"), 72);
    }

}
