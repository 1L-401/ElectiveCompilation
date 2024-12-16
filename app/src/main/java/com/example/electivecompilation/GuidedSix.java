package com.example.electivecompilation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.electivecompilation.R;

public class GuidedSix extends Fragment {

    CheckBox one, two, three, four;
    TextView result;
    Button click;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guided_six, container, false);

        // Initialize the views
        one = view.findViewById(R.id.chkOne);
        two = view.findViewById(R.id.chkTwo);
        three = view.findViewById(R.id.chkThree);
        four = view.findViewById(R.id.chkFour);
        result = view.findViewById(R.id.tvResultGE6);
        click = view.findViewById(R.id.btnClickGE6);

        // Set up the click listener for the button
        showResult(view);

        return view;
    }

    public void showResult(View view) {
        click.setOnClickListener(v -> {
            boolean isOneThreeChecked = one.isChecked() && three.isChecked();
            boolean isTwoFourChecked = two.isChecked() && four.isChecked();
            boolean isAlternateChecked = (one.isChecked() || three.isChecked()) &&
                    (two.isChecked() || four.isChecked());

            if (isTwoFourChecked) {
                result.setVisibility(View.VISIBLE);
                result.setTextColor(Color.RED);
                result.setText("this Color is RED");
            } else if (isOneThreeChecked) {
                result.setVisibility(View.VISIBLE);
                result.setTextColor(Color.BLUE);
                result.setText("this Color is BLUE");
            } else if (isAlternateChecked) {
                result.setVisibility(View.VISIBLE);
                result.setTextColor(Color.GREEN);
                result.setText("this Color is GREEN");
            } else {
                result.setVisibility(View.INVISIBLE);
            }
        });
    }
    }

