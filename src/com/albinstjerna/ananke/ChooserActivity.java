package com.albinstjerna.ananke;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.content.Intent;
import android.app.ActionBar;
import android.app.ActionBar.Tab;

public class ChooserActivity extends Activity
    implements BinaryChoiceFragment.RecieveDecisionData, MultipleChoiceFragment.RecieveDecisionData {

    public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;

        /** Constructor used each time a new tab is created.
         * @param activity  The host Activity, used to instantiate the fragment
         * @param tag  The identifier tag for the fragment
         * @param clz  The fragment's Class, used to instantiate the fragment
         */
        public TabListener(Activity activity, String tag, Class<T> clz) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
        }

        /* The following are each of the ActionBar.TabListener callbacks */

        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            // Check if the fragment is already initialized
            if (mFragment == null) {
                // If not, instantiate and add it to the activity
                mFragment = Fragment.instantiate(mActivity, mClass.getName());
                ft.add(android.R.id.content, mFragment, mTag);
            } else {
                // If it exists, simply attach it in order to show it
                ft.attach(mFragment);
            }
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                // Detach the fragment, because another one is being attached
                ft.detach(mFragment);
            }
        }

        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // User selected the already selected tab. Usually do nothing.
        }
    }

    
    // This message is either the string "0" or the string "1".
    // Also the fact that I'm typing this manually SUCKS.
    //    public final static String BOOLEAN_MESSAGE = "com.albinstjerna.ananke.BOOLEAN_MESSAGE";
    public final static String STRING_MESSAGE = "com.albinstjerna.ananke.STRING_MESSAGE";

    //    BinaryChoiceFragment binaryFragment;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Use the root view.
        //setContentView(R.layout.chooser);

        // Setup action bar for tab navigation.
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        // Add tabs:
        Tab binary_choice_tab = actionBar.newTab()
            .setText("Binary Choice")
            .setTabListener(new TabListener<BinaryChoiceFragment> (
                                this, "binary choice", BinaryChoiceFragment.class));
        actionBar.addTab(binary_choice_tab);

        Tab multiple_choice_tab = actionBar.newTab()
            .setText("Multiple Choice")
            .setTabListener(new TabListener<MultipleChoiceFragment> (
                                this, "multiple choice", MultipleChoiceFragment.class));

        actionBar.addTab(multiple_choice_tab);

    }

    /** Send the decision to the displayer view. */
    
    // public void sendStringMessage(View view) {
    //     Intent intent = new Intent(this, DisplayerActivity.class);
  
    //     intent.putExtra(STRING_MESSAGE, "This is a test");
    //     startActivity(intent);
        
    // }

    public void handleDecision(String decision) {
        Intent intent = new Intent(this, DisplayerActivity.class);
        intent.putExtra(STRING_MESSAGE, decision);
        startActivity(intent);
    }
    
}
