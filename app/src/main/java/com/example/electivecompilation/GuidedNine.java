package com.example.electivecompilation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.electivecompilation.R;

public class GuidedNine extends Fragment {

    // Declare ListView and Adapter
    ListView lvNames;
    ArrayAdapter<String> adapter;

    // Data arrays for names and corresponding semester grades
    String[] listOfNames = {"Papsi", "Majoy", "Pol", "Che", "Tin", "Lou", "Renz", "Pet", "Roven", "Chan", "Jher"};
    double[] listOfSemGrades = {1.00, 1.50, 2.00, 1.25, 3.00, 5.00, 1.75, 2.25, 3.00, 1.00, 2.25};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guided_nine, container, false);

        // Initialize ListView by ID
        lvNames = view.findViewById(R.id.lvNameGE9);

        // Create ArrayAdapter to bind data to the ListView
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listOfNames);
        lvNames.setAdapter(adapter); // Set adapter to the ListView

        // Call the method to handle item clicks
        showSemGrade();

        return view;
    }

    // Method to handle ListView item clicks
    public void showSemGrade() {
        lvNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Display the selected name and semester grade in a Toast message
                Toast.makeText(
                        getContext(),
                        "Name: " + listOfNames[position] + "\nSem Grade: " + listOfSemGrades[position],
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
