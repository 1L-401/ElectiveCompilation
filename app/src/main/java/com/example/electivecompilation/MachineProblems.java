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

public class MachineProblems extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machine_problems, container, false);

        setupButtonClick(view, R.id.SemGrades, new SemestralGrades());
        setupButtonClickForPayroll(view, R.id.PayrollComputation, Payroll.class);
        setupButtonCalculator(view, R.id.Calculator, new CalculatorFragment());
        return view;
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

    // Separate method for GuidedTwelve to open it specifically
    private void setupButtonClickForPayroll(View view, int buttonId, Class<?> activityClass) {
        Button button = view.findViewById(buttonId);
        button.setOnClickListener(v -> {
            // Start the GuidedTwelve Activity when the button is clicked
            Intent intent = new Intent(getActivity(), Payroll.class);
            startActivity(intent);
        });
    }


    private void setupButtonCalculator(View view, int buttonId, Fragment destinationFragment) {
        Button button = view.findViewById(buttonId);
        button.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, destinationFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }
}
