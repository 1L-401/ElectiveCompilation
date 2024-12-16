package com.example.electivecompilation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class GuidedOne extends Fragment {
    private EditText name, age;
    private TextView result;
    private Button click;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guided_one, container, false);

        name = view.findViewById(R.id.NameGE1);
        age = view.findViewById(R.id.etAgeGE1);
        result = view.findViewById(R.id.tvResultsGE1);
        click = view.findViewById(R.id.btnClickGE1);

        showResults();

        return view;
    }

    private void showResults() {
        click.setOnClickListener(v -> {
            result.setText("Name: " + name.getText().toString() +
                    "\nAge: " + age.getText().toString());
            name.setText("");
            age.setText("");
            name.requestFocus();
        });
    }

    public void showResultsAnotherWay(View view) {
        result.setText("Name: " + name.getText().toString() +
                "\nAge: " + age.getText().toString());
        name.setText("");
        age.setText("");
        name.requestFocus();
    }
}