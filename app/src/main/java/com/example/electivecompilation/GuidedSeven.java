package com.example.electivecompilation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.electivecompilation.R;

public class GuidedSeven extends Fragment {

    RatingBar ratingBar;
    TextView rate;
    Button click, close;
    AlertDialog.Builder alertDialogBuilder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guided_seven, container, false);

        // Initialize the views
        alertDialogBuilder = new AlertDialog.Builder(getActivity());
        ratingBar = view.findViewById(R.id.ratingBar);
        rate = view.findViewById(R.id.tvResultGE7);
        click = view.findViewById(R.id.btnClickGE7);
        close = view.findViewById(R.id.btnCloseGE7);

        // Set up listeners
        showRating();
        closeApplication();

        return view;
    }

    // Show rating change and update UI based on rating
    public void showRating() {
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating >= 3) {
                    rate.setTextColor(Color.GREEN);
                } else {
                    rate.setTextColor(Color.RED);
                }
                rate.setText("Rate: " + rating);
            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Rate: " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Close the application (activity) when the button is clicked
    public void closeApplication() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }
}
