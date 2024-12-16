package com.example.electivecompilation;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class SemestralGrades extends Fragment {

    EditText Student, Prelim, Midterm, Finals;
    TextView STame, Grade, Points, Remarks;
    Button Compute, NEntry;
    double pre, mid, finals, semestralGrade;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_semestral_grades, container, false);

        // Initialize the views
        Student = view.findViewById(R.id.etstudentName);
        Prelim = view.findViewById(R.id.etPrelim);
        Midterm = view.findViewById(R.id.etMidterm);
        Finals = view.findViewById(R.id.etFinals);
        STame = view.findViewById(R.id.tvStudentName);
        Grade = view.findViewById(R.id.tvSemgrade);
        Points = view.findViewById(R.id.tvPoints);
        Remarks = view.findViewById(R.id.tvRemarks);
        Compute = view.findViewById(R.id.btnCompute);
        NEntry = view.findViewById(R.id.btnEntry);

        ComputeButton();
        NewEntryButton();

        return view;
    }

    public void ComputeButton() {
        Compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("WARNING MESSAGE")
                        .setMessage("All Entries Correct?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                computeGrades();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
            }
        });
    }

    private void computeGrades() {
        if (Student.getText().toString().isEmpty() || Prelim.getText().toString().isEmpty() ||
                Midterm.getText().toString().isEmpty() || Finals.getText().toString().isEmpty()) {
            displayErrorMessage();
        } else {
            String studentName = Student.getText().toString();
            pre = Double.parseDouble(Prelim.getText().toString());
            mid = Double.parseDouble(Midterm.getText().toString());
            finals = Double.parseDouble(Finals.getText().toString());

            semestralGrade = (pre * 0.25) + (mid * 0.25) + (finals * 0.5);
            Grade.setText(String.format("%.2f", semestralGrade));

            STame.setText(studentName);

            String remarks = semestralGrade >= 75 ? "PASSED" : "FAILED";
            Remarks.setText(remarks);
            Remarks.setTextColor(remarks.equals("PASSED") ? Color.BLUE : Color.RED);

            double pointEquivalent = getPointEquivalent(semestralGrade);
            Points.setText(String.format("%.2f", pointEquivalent));
        }
    }

    public void NewEntryButton() {
        NEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("WARNING MESSAGE")
                        .setMessage("Are you sure?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                clearEntries();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
            }
        });
    }

    // Method to clear all fields
    private void clearEntries() {
        Student.setText("");
        Prelim.setText("");
        Midterm.setText("");
        Finals.setText("");

        STame.setText("");
        Grade.setText("");
        Points.setText("");
        Remarks.setText("");
    }

    private double getPointEquivalent(double grade) {
        if (grade == 100) {
            return 1.00;
        } else if (grade >= 95 && grade <= 99) {
            return 1.50;
        } else if (grade >= 90 && grade <= 94) {
            return 2.00;
        } else if (grade >= 85 && grade <= 89) {
            return 2.50;
        } else if (grade >= 80 && grade <= 84) {
            return 3.00;
        } else if (grade >= 75 && grade <= 79) {
            return 3.50;
        } else {
            return 5.00;  // 74 below
        }
    }

    private void displayErrorMessage() {
        Toast.makeText(getActivity(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
    }
}
