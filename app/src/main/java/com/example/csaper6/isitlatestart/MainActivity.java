package com.example.csaper6.isitlatestart;

//region Imports
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Random;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
//endregion


public class MainActivity extends AppCompatActivity {
    //region Instance Variables
    private TextView daytext;
    private TextView monthtext;
    private TextView latestart;
    private String dateString;
    private String monthString;
    private Button checkButton;
    private int currentIndex;
    private TextView commandTextView;
    private int date;
    private int month;
    private static final String TAG = "MyActivity";
    private int[][] datearray = new int[13][];
    private boolean verdict;
    public static final String EXTRA_VERDICT = "message";
    private AdView mAdView;
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    //endregion

    //region Question Names
    private String facts[] = {
            "Is it late start?",
            "hit me baby one more time",
            "Are you sure?",
            "What is harder to catch the faster you run",
            "Answer: your breath",
            "Fun fact: The stickers on fruit are made of edible paper",
            "Last one I promise!",
            "Lol you thought"
    };
    //endregion

    //region Button Names
    private String buttons[] = {
            "check",
            "ok",
            "yes",
            "Late Start?",
            "click me",
            "last click!",
            "confirm",
            "lol"
    };
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ads
        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest.Builder adRequest = new AdRequest.Builder();
        AdRequest adReq = adRequest.build();
        //mAdView.setAdSize(AdSize.BANNER);
        Log.d(TAG, "onCreate: " + mAdView.getAdSize());
        //mAdView.setAdUnitId(getString(R.string.firstAd));
        mAdView.loadAd(adReq);


        //Ads

        Calendar c = Calendar.getInstance();
        date = c.get(Calendar.DATE);
        int seconds = c.get(Calendar.SECOND);
        month = c.get(Calendar.MONTH);
        month = month + 1;
        dateString = "Date: " + date;
        monthString = "Month: " + (month);

        //daytext = (TextView)findViewById(R.id.dayText);
        //monthtext = (TextView)findViewById(R.id.monthText);
        //daytext.setText(dateString);
        //monthtext.setText(monthString);
        //region LateStart Dates
        datearray[0] = new int[]{100,0};
        datearray[1] = new int[]{10,11};
        datearray[2] = new int[]{7,8,21,22};
        datearray[3] = new int[]{7,8,28,29};
        datearray[4] = new int[]{18,19};
        datearray[5] = new int[]{2,3,16,17,30,31};
        datearray[6] = new int[]{100,0};
        datearray[7] = new int[]{100,0};
        datearray[8] = new int[]{100,0};
        datearray[9] = new int[]{100,0};
        datearray[10] = new int[]{18, 19};
        datearray[11] = new int[]{1,2,15,16};
        datearray[12] = new int[]{6,7};
        //endregion
        //wire
        checkButton = (Button) findViewById(R.id.button_check);
        commandTextView = (TextView) findViewById(R.id.textView_command);

        //on clicks
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQ();
            }
        });

        checkLatestart();
    }
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    //region Update Method
    private void updateQ() {
        currentIndex++;
        if(currentIndex == facts.length){
            //Open new activity for last question
            //Toast.makeText(MainActivity.this, "IIIII", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Confirm.class);
            intent.putExtra(EXTRA_VERDICT, verdict);
            startActivity(intent);

        }
        currentIndex = currentIndex % facts.length;
        commandTextView.setText(facts[currentIndex]);
        checkButton.setText(buttons[currentIndex]);
        Random r = new Random();
        checkButton.setX(r.nextInt(1000));
        checkButton.setY(r.nextInt(1000));
    }
    //endregion



    private void checkLatestart() {
        for(int i = 0; i <= datearray[month].length-1;i++) {
            if (date == datearray[month][i]) {
                //latestart.setText("YES");
                verdict = true;
                Log.d(TAG, "Month: " + month);
                Log.d(TAG, "Date: " + date);
                Log.d(TAG, "checkLatestart: " + verdict);
                //sendNotification();
                break;
            }
                //latestart.setText("NO");
        }
    }

    private void sendNotification() {
        /*
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_whatshot_black_24dp)
                        .setContentTitle("Is is late start?")
                        .setContentText("Click here to find out.");

        int mNotificationId = 001;
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
        */
    }



}
