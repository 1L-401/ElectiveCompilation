package com.example.electivecompilation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class CalculatorFragment extends Fragment {

    TextView firstnum, secondnum, result;
    Button add, diff, prod, quo;
    Double FirstNum;
    Double SecondNum;
    Double Result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        result = view.findViewById(R.id.tvResult);
        firstnum = view.findViewById(R.id.etFirstNumber);
        secondnum = view.findViewById(R.id.etSecondNumber);
        add = view.findViewById(R.id.btnAdd);
        diff = view.findViewById(R.id.btnDiff);
        prod = view.findViewById(R.id.btnProd);
        quo = view.findViewById(R.id.btnQuo);

        computeTotal();

        return view;
    }

    public void computeTotal() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstNum = Double.parseDouble(firstnum.getText().toString());
                SecondNum = Double.parseDouble(secondnum.getText().toString());
                Result = FirstNum + SecondNum;
                result.setText("The total sum is " + Result);
                if (((double) Result) % 2 != 0) {
                    result.setTextColor(Color.RED);
                } else {
                    result.setTextColor(Color.BLUE);
                }
            }
        });

        diff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstNum = Double.parseDouble(firstnum.getText().toString());
                SecondNum = Double.parseDouble(secondnum.getText().toString());
                Result = FirstNum - SecondNum;
                result.setText("The difference is " + Result);
                if (Result % 2 != 0) {
                    result.setTextColor(Color.RED);
                } else {
                    result.setTextColor(Color.BLUE);
                }
            }
        });

        prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstNum = Double.parseDouble(firstnum.getText().toString());
                SecondNum = Double.parseDouble(secondnum.getText().toString());
                Result = FirstNum * SecondNum;
                result.setText("The product is " + Result);
                if (Result % 2 != 0) {
                    result.setTextColor(Color.RED);
                } else {
                    result.setTextColor(Color.BLUE);
                }
            }
        });

        quo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstNum = Double.parseDouble(firstnum.getText().toString());
                SecondNum = Double.parseDouble(secondnum.getText().toString());
                Result = FirstNum / SecondNum;
                result.setText("The total quotient is " + Result);
                if (Result % 2 != 0) {
                    result.setTextColor(Color.RED);
                } else {
                    result.setTextColor(Color.BLUE);
                }
            }
        });
    }
}
