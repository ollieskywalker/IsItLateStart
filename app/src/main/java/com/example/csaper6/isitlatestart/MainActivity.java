package com.example.csaper6.isitlatestart;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private TextView daytext;
    private TextView monthtext;
    private TextView latestart;

    private String dateString;
    private String monthString;

    private Button checkButton;
    private int currentIndex;
    private TextView commandTextView;
    private String facts[] = {
            "Is it late start?",
            "hit me baby one more time",
            "Are you sure?",
            "What is harder to catch the faster you run",
            "Answer: your breath",
            "Fun fact: The stickers on fruit are made of edible paper",
            "Last one I promise!",
            "Lmao you thought"
    };
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar c = Calendar.getInstance();
        int date = c.get(Calendar.DATE);
        int seconds = c.get(Calendar.SECOND);
        int month = c. get(Calendar.MONTH);
        dateString = "Date: " + date;
        monthString = "Month: " + (month + 1);

        daytext = (TextView) findViewById(R.id.dayText);
        monthtext = (TextView) findViewById(R.id.monthText);

        daytext.setText(dateString);
        monthtext.setText(monthString);

        //wire
        checkButton = (Button) findViewById(R.id.button_check);
        commandTextView = (TextView) findViewById(R.id.textView_command);
        
        //on clicks
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                updateQ();
                checkButton.setX(r.getNextint(700)+1);
                checkButton.setY(r.getNextint(700)+1);
            }
        });
    }

    private void updateQ() {
        currentIndex++;
        if(currentIndex == facts.length){
            //Open new activity for last question
            //Toast.makeText(MainActivity.this, "IIIII", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Confirm.class);
            startActivity(intent);
        }
        currentIndex = currentIndex % facts.length;
        commandTextView.setText(facts[currentIndex]);
        checkButton.setText(buttons[currentIndex]);
    }
}
