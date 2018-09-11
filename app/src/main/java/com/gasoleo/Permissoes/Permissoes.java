package com.gasoleo.Permissoes;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class Permissoes {

    Activity activity;

    public Permissoes(Activity activity){
        this.activity = activity;
    }

    public void checar (){
        String[] PERMISSOES = {
                Manifest.permission.INTERNET,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        if(!hasPermissions(activity, PERMISSOES)){
            ActivityCompat.requestPermissions(activity, PERMISSOES, 1);
        }
    }

    public boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}
