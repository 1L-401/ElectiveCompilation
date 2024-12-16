package com.example.electivecompilation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class GuidedExercises extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guided_exercises, container, false);

        setupButtonClick(view, R.id.guided1, new GuidedOne());
        setupButtonClick(view, R.id.idguided2, new GuidedTwo());
        setupButtonClick(view, R.id.idguided3, new GuidedThree());
        setupButtonClick(view, R.id.idguided4, new GuidedFour());
        setupButtonClick(view, R.id.idguided5, new GuidedFive());
        setupButtonClick(view, R.id.idguided6, new GuidedSix());
        setupButtonClick(view, R.id.idguided7, new GuidedSeven());
        setupButtonClick(view, R.id.idguided8, new GuidedEight());
        setupButtonClick(view, R.id.idguided9, new GuidedNine());
        setupButtonClick(view, R.id.idguided10, new GuidedTen());
        setupButtonClickforGuidedEleven(view, R.id.idguided11, GuidedEleven.class);
        setupButtonClickForGuidedTwelve(view, R.id.idguided12, GuidedTwelve.class);
        setupButtonClick(view, R.id.idguided13, new GuidedThirteen());
        setupButtonClick(view, R.id.idguided14, new GuidedFourteen());
        setupButtonClick(view, R.id.idsplashscreen, new SplashScreen());
        setupButtonClick(view, R.id.draganddrop, new DragandDrop());
        setupButtonClick(view, R.id.sqlite, new SqliteDatabase());




        return view;
    }
    // Modified method to open Activity for each specific button
    private void setupButtonClickforGuidedEleven(View view, int buttonId, Class<?> activityClass) {
        Button button = view.findViewById(buttonId);
        button.setOnClickListener(v -> {
            // Start the respective Activity when the button is clicked
            Intent intent = new Intent(getActivity(), GuidedEleven.class);
            startActivity(intent);
        });
    }


    // Separate method for GuidedTwelve to open it specifically
    private void setupButtonClickForGuidedTwelve(View view, int buttonId, Class<?> activityClass) {
        Button button = view.findViewById(buttonId);
        button.setOnClickListener(v -> {
            // Start the GuidedTwelve Activity when the button is clicked
            Intent intent = new Intent(getActivity(), GuidedTwelve.class);
            startActivity(intent);
        });
    }
    private void setupButtonClick(View view, int buttonId, Fragment destinationFragment) {
        Button button = view.findViewById(buttonId);
        button.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, destinationFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }
}