package com.example.electivecompilation;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.electivecompilation.R;

public class GuidedTwo extends Fragment {
    private EditText name;
    private Button click;
    private Toast toast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guided_two, container, false);

        // Initialize UI components
        name = view.findViewById(R.id.etNameGE2);
        click = view.findViewById(R.id.btnClickGE2);

        // Set the click listener for the button
        showResult(view);

        return view;
    }

    public void showResult(View view) {
        click.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Ensure context is from getActivity() for Toast
                toast = Toast.makeText(getActivity(), "Hello " + name.getText().toString() + "!\nWelcome to Android Development", Toast.LENGTH_LONG);
                View toastView = toast.getView();
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }
}
