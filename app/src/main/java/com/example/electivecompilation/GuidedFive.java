package com.example.electivecompilation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.electivecompilation.R;

public class GuidedFive extends Fragment {

    RadioButton red, blue, yellow, green;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guided_five, container, false);

        // Initialize RadioButtons
        red = view.findViewById(R.id.rbRed);
        blue = view.findViewById(R.id.rbBlue);
        yellow = view.findViewById(R.id.rbYellow);
        green = view.findViewById(R.id.rbGreen);

        // Set up listeners for radio buttons
        setRadioButtonListeners();

        return view;
    }

    private void setRadioButtonListeners() {
        // Setting click listeners for radio buttons
        red.setOnClickListener(this::changeBackgroundColor);
        blue.setOnClickListener(this::changeBackgroundColor);
        yellow.setOnClickListener(this::changeBackgroundColor);
        green.setOnClickListener(this::changeBackgroundColor);
    }

    public void showSelectedColor() {
        if (red.isChecked()) {
            Toast.makeText(getActivity(), "Color: RED", Toast.LENGTH_SHORT).show();
        }
        if (blue.isChecked()) {
            Toast.makeText(getActivity(), "Color: BLUE", Toast.LENGTH_SHORT).show();
        }
        if (yellow.isChecked()) {
            Toast.makeText(getActivity(), "Color: YELLOW", Toast.LENGTH_SHORT).show();
        }
        if (green.isChecked()) {
            Toast.makeText(getActivity(), "Color: GREEN", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeBackgroundColor(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        int id = view.getId();

        if (id == R.id.rbRed && checked) {
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.RED);
            showSelectedColor();
        } else if (id == R.id.rbBlue && checked) {
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            showSelectedColor();
        } else if (id == R.id.rbYellow && checked) {
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            showSelectedColor();
        } else if (id == R.id.rbGreen && checked) {
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            showSelectedColor();
        }
    }
}
