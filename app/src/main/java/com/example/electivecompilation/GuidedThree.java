package com.example.electivecompilation;

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

public class GuidedThree extends Fragment {
    EditText num1, num2;
    Button sum, ave;
    Toast toast;
    View view;
    double firstNum = 0, secondNum = 0, total = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guided_three, container, false);

        // Initialize the views
        num1 = view.findViewById(R.id.etNum1GE3);
        num2 = view.findViewById(R.id.etNum2GE);
        sum = view.findViewById(R.id.btnSumGE3);
        ave = view.findViewById(R.id.btnAveGE3);

        // Call computeTotal method
        computeTotal(view);

        return view;
    }

    public void computeTotal(View view) {
        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num1.getText().toString().isEmpty() || num2.getText().toString().isEmpty()) {
                    displayErrorMessage();
                } else {
                    firstNum = Double.parseDouble(num1.getText().toString());
                    secondNum = Double.parseDouble(num2.getText().toString());
                    total = firstNum + secondNum;
                    Toast.makeText(getActivity(), "SUM: " + total, Toast.LENGTH_SHORT).show();
                }
            }
        });

        ave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num1.getText().length() <= 0 || num2.getText().length() <= 0) {
                    displayErrorMessage();
                } else {
                    firstNum = Double.parseDouble(num1.getText().toString());
                    secondNum = Double.parseDouble(num2.getText().toString());
                    total = (firstNum + secondNum) / 2;
                    Toast.makeText(getActivity(), "AVE: " + total, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void displayErrorMessage() {
        toast = Toast.makeText(getActivity(), "Please Enter a Number", Toast.LENGTH_SHORT);
        view = toast.getView();
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
