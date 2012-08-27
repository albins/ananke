package com.albinstjerna.ananke;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.view.Gravity;

public class DisplayerActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get message from Chooser activity.
        Intent intent = getIntent();
        String message = intent.getStringExtra(ChooserActivity.STRING_MESSAGE);

        // Create a text view to display it.
         TextView textView = new TextView(this);
         textView.setGravity(Gravity.CENTER);
         textView.setTextSize(40);
         textView.setText(message);
         
         setContentView(textView);
    }
}