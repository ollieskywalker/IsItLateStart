package com.example.csaper6.isitlatestart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class Confirm extends AppCompatActivity {
    private boolean answer;
    private TextView yesNoText;
    private InterstitialAd transitionAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        transitionAd = new InterstitialAd(this);
        transitionAd.setAdUnitId("ca-app-pub-5843218701862538/4810645002");
        transitionAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });

        requestNewInterstitial();

        Intent i = getIntent();
        answer = i.getBooleanExtra(MainActivity.EXTRA_VERDICT,true);

        yesNoText = (TextView)findViewById(R.id.yes_no);

        if(answer){
            yesNoText.setText("YES");
        }
        else{
            yesNoText.setText("NO");
        }

    }

    private void requestNewInterstitial() {
        AdRequest.Builder adRequest = new AdRequest.Builder();
        AdRequest adReq = adRequest.build();

        transitionAd.loadAd(adReq);
    }
}
