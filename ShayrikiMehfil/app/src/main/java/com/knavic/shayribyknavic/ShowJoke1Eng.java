package com.knavic.shayribyknavic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ShowJoke1Eng extends AppCompatActivity {
    private TextView joke;
    private FloatingActionButton floatingActionButton;
    private DBManager dbManager;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.showjoke1);
        Uri uri=getIntent().getData();
        if(uri != null){
            List<String> params = uri.getPathSegments();
            String id =params.get(params.size()-1);




            joke = (TextView) findViewById(R.id.textjoke5);
            dbManager = new DBManager(this);
            dbManager.open();
            final String desc1 = dbManager.eng_getjoke(id);
            joke.setText(desc1);

            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });

            mAdView = findViewById(R.id.adView_show1);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);


        }

    }
}