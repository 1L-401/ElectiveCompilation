package com.example.electivecompilation;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PayrollDetails extends AppCompatActivity {
    Button Back;
    TextView EmID, cStatus, employeeNameView, positionCodeView,
            daysWorkedView, basicPayView, sssContributionView,
            withholdingTaxView, netPayView;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payroll_details);


        EmID = findViewById(R.id.tvIDD);
        cStatus = findViewById(R.id.tvCStatusD);
        employeeNameView = findViewById(R.id.tvENameD);
        positionCodeView = findViewById(R.id.tvPstcodeD);
        daysWorkedView = findViewById(R.id.tvDaysD);
        basicPayView = findViewById(R.id.tvBPayD);
        sssContributionView = findViewById(R.id.tvSSSD);
        withholdingTaxView = findViewById(R.id.tvWDetailsD);
        netPayView = findViewById(R.id.tvNPayD);


        db = openOrCreateDatabase("PayrollDB", MODE_PRIVATE, null);


        String employeeID = getIntent().getStringExtra("EmployeeID");


        displayPayrollDetails(employeeID);


        Back = findViewById(R.id.btnBack);
        Back.setOnClickListener(view -> finish());
    }

    @SuppressLint("Range")
    private void displayPayrollDetails(String employeeID) {

        Cursor cursor = db.rawQuery("SELECT * FROM EmployeePayroll WHERE EmployeeID=?", new String[]{employeeID});

        if (cursor.moveToFirst()) {
            // Display the retrieved data
            EmID.setText(cursor.getString(cursor.getColumnIndex("EmployeeID")));
            employeeNameView.setText(cursor.getString(cursor.getColumnIndex("EmployeeName")));
            positionCodeView.setText(cursor.getString(cursor.getColumnIndex("PositionCode")));
            cStatus.setText(cursor.getString(cursor.getColumnIndex("CivilStatus")));
            daysWorkedView.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("DaysWorked"))));
            basicPayView.setText("PHP " + String.format("%.2f", cursor.getDouble(cursor.getColumnIndex("BasicPay"))));
            sssContributionView.setText("PHP " + String.format("%.2f", cursor.getDouble(cursor.getColumnIndex("SSSContribution"))));
            withholdingTaxView.setText("PHP " + String.format("%.2f", cursor.getDouble(cursor.getColumnIndex("WithholdingTax"))));
            netPayView.setText("PHP " + String.format("%.2f", cursor.getDouble(cursor.getColumnIndex("NetPay"))));
        }

        cursor.close();
    }
}
