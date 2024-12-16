package com.example.electivecompilation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private View mainContent;
    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContent = findViewById(R.id.main_content);
        fragmentContainer = findViewById(R.id.fragment_container);


        Button btnGuided = findViewById(R.id.idGuided);
        btnGuided.setOnClickListener(v -> navigateToGuidedExercisesFragment());

        Button btnMachine = findViewById(R.id.idMachine);
        btnMachine.setOnClickListener(v -> navigateToMachineProblemsFragment());
    }

    private void navigateToGuidedExercisesFragment() {
        mainContent.setVisibility(View.GONE);
        fragmentContainer.setVisibility(View.VISIBLE);

        GuidedExercises fragment = new GuidedExercises();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
    private void navigateToMachineProblemsFragment() {
        mainContent.setVisibility(View.GONE);
        fragmentContainer.setVisibility(View.VISIBLE);

        MachineProblems fragment = new MachineProblems();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            mainContent.setVisibility(View.VISIBLE);
            fragmentContainer.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}