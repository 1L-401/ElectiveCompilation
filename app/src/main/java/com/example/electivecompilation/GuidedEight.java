package com.example.electivecompilation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.electivecompilation.R;

public class GuidedEight extends Fragment {

    Spinner names;
    TextView result;
    ArrayAdapter<String> adapter;
    String[] listOfNames = {"Name Here", "Papsi", "Pol", "Che", "Tin",
            "Renz", "Lou", "Chan", "Ven", "Jher"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guided_eight, container, false);

        // Initialize the views
        names = view.findViewById(R.id.spnrNamesGE8);
        result = view.findViewById(R.id.tvResultGE8);

        // Set up the adapter and listener
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listOfNames);
        names.setAdapter(adapter);
        showSelectedName();

        return view;
    }

    public void showSelectedName() {
        names.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    result.setText("Name: " + listOfNames[i]);
                    Toast.makeText(getContext(), "Name: " + listOfNames[i], Toast.LENGTH_SHORT).show();
                } else {
                    result.setText("Name: ");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Optional: Handle when no item is selected (if needed)
            }
        });
    }
}
