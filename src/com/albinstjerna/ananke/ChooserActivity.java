package com.albinstjerna.ananke;

//import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import java.util.Random;
import android.content.Intent;

public class ChooserActivity extends FragmentActivity
{
    public final static String EXTRA_MESSAGE = "com.albinstjerna.ananke.MESSAGE";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooser);
    }

    /** Send the decision to the displayer view. */
    public void sendMessage(View view) {
        Random randgen = new Random();
        int random_number = randgen.nextInt(2);
        Intent intent = new Intent(this, DisplayerActivity.class);

        // The funky concatenation below is to force the number to a string.
        intent.putExtra(EXTRA_MESSAGE, "" + random_number);
        startActivity(intent);
    }
}
