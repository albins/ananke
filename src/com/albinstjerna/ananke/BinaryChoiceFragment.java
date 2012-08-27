package com.albinstjerna.ananke;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import java.util.Random;
import android.app.Activity;
import android.widget.Button;

public class BinaryChoiceFragment extends Fragment {
    RecieveDecisionData mCallback;

    // Container Activity must implement this interface
    public interface RecieveDecisionData {
        public void handleDecision(String decision);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.binarychoice, container, false);

        Button myButton = (Button) view.findViewById(R.id.binary_choice_button);
        myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    // put some code here that handles the click event.
                    Random randgen = new Random();
                    int random_number = randgen.nextInt(2);
                    
                    mCallback.handleDecision(parseRandomInt(random_number));
                }
            });

        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (RecieveDecisionData) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement RecieveDecisionData");
        }
    }

    private String parseRandomInt(int number) {
        // FIXME don't use hard-coded strings here.
        if (number == 1) {
            return "Yes";
        } else if (number == 0) {
            return "No";
        }
        return "Sentinel error in parseRandomInt";
    }

}