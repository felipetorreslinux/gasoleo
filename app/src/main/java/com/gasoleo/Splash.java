package com.gasoleo;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gasoleo.Permissoes.Permissoes;
import com.gasoleo.Views.Central;

public class Splash extends AppCompatActivity {

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(activity, Central.class));
                finish();
            }
        }, 3000);
    }

    @Override
    protected void onResume() {
        new Permissoes(this).checar();
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                new Permissoes(this).checar();
            break;
        }
    }
}
