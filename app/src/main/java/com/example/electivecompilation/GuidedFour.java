package com.example.electivecompilation;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.electivecompilation.R;

public class GuidedFour extends Fragment {

    EditText username, password;
    TextView result;
    Button Login;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guided_four, container, false);

        // Initialize the views
        username = view.findViewById(R.id.etUsernameGE4);
        password = view.findViewById(R.id.etPasswordGE4);
        result = view.findViewById(R.id.tvResultGE4);
        Login = view.findViewById(R.id.btnLoginGE4);

        // Set up the login action
        showResults(view);

        return view;
    }

    public void showResults(View view) {
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("papsi") &&
                        password.getText().toString().equals("android")) {
                    result.setText("Welcome " + username.getText().toString() + "!");
                    result.setTextColor(GREEN);
                    clearEntry();
                } else {
                    result.setText("Username and Password does not Exist!");
                    result.setTextColor(RED);
                    clearEntry();
                }
            }
        });
    }

    public void clearEntry() {
        result.setVisibility(View.VISIBLE);
        username.setText("");
        password.setText("");
        username.requestFocus();
    }
}
