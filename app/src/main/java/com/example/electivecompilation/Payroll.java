package com.example.electivecompilation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class Payroll extends AppCompatActivity {
    TextView EmployeeName;
    Button Compute, Clear;
    Spinner ID, Position, Days;
    RadioButton Single, Married, Widowed;
    private HashMap<String, String> employeeNames;

    private static final double RATE_A = 500.0;
    private static final double RATE_B = 400.0;
    private static final double RATE_C = 300.0;
    private static final double TAX_RATE_SINGLE = 0.10;
    private static final double TAX_RATE_MARRIED = 0.05;
    private static final double TAX_RATE_WIDOWED = 0.05;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payroll);


        db = openOrCreateDatabase("PayrollDB", MODE_PRIVATE, null);
        createDatabaseTable();

        EmployeeName = findViewById(R.id.tvEName);
        ID = findViewById(R.id.spinID);
        Position = findViewById(R.id.spinPstcode);
        Days = findViewById(R.id.spinDays);
        Single = findViewById(R.id.rbSingle);
        Married = findViewById(R.id.rbMarried);
        Widowed = findViewById(R.id.rbWidowed);
        Compute = findViewById(R.id.btnCompute);
        Clear = findViewById(R.id.btnClear);

        employeeNames = new HashMap<>();
        employeeNames.put("ID001", "Walter");
        employeeNames.put("ID002", "Jessie");
        employeeNames.put("ID003", "Skyler");
        employeeNames.put("ID004", "Hank");
        employeeNames.put("ID005", "Marie");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.employee_ids, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ID.setAdapter(adapter);
        ID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedID = parent.getItemAtPosition(position).toString();
                String name = employeeNames.get(selectedID);
                EmployeeName.setText(name != null ? name : "Unknown");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ComputeButton();
        ClearButton();
    }

    private void createDatabaseTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS EmployeePayroll (" +
                "EmployeeID TEXT PRIMARY KEY, " +
                "EmployeeName TEXT, " +
                "PositionCode TEXT, " +
                "CivilStatus TEXT, " +
                "DaysWorked INTEGER, " +
                "BasicPay REAL, " +
                "SSSContribution REAL, " +
                "WithholdingTax REAL, " +
                "NetPay REAL);";
        db.execSQL(createTableQuery);
    }

    public void ComputeButton() {
        Compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeAndSavePayroll();
            }
        });
    }

    private void ClearButton() {
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmployeeName.setText("");
                ID.setSelection(0);
                Position.setSelection(0);
                Days.setSelection(0);
                Single.setChecked(false);
                Married.setChecked(false);
                Widowed.setChecked(false);
            }
        });
    }

    private void computeAndSavePayroll() {
        String positionCode = Position.getSelectedItem().toString();
        String id = ID.getSelectedItem().toString();
        String name = employeeNames.get(id);
        String civilStatus = Single.isChecked() ? "Single" : Married.isChecked() ? "Married" : "Widowed";
        double ratePerDay = positionCode.equals("A") ? RATE_A : positionCode.equals("B") ? RATE_B : RATE_C;
        double sssRate = positionCode.equals("A") ? 0.07 : positionCode.equals("B") ? 0.05 : 0.03;

        int daysWorked = Integer.parseInt(Days.getSelectedItem().toString());
        double taxRate = Single.isChecked() ? TAX_RATE_SINGLE : TAX_RATE_MARRIED;

        double basicPay = daysWorked * ratePerDay;
        double sssContribution = basicPay * sssRate;
        double withholdingTax = basicPay * taxRate;
        double netPay = basicPay - (sssContribution + withholdingTax);


        Cursor cursor = db.rawQuery("SELECT * FROM EmployeePayroll WHERE EmployeeID=?", new String[]{id});

        ContentValues values = new ContentValues();
        values.put("EmployeeID", id);
        values.put("EmployeeName", name);
        values.put("PositionCode", positionCode);
        values.put("CivilStatus", civilStatus);
        values.put("DaysWorked", daysWorked);
        values.put("BasicPay", basicPay);
        values.put("SSSContribution", sssContribution);
        values.put("WithholdingTax", withholdingTax);
        values.put("NetPay", netPay);

        if (cursor.moveToFirst()) {

            db.update("EmployeePayroll", values, "EmployeeID=?", new String[]{id});
        } else {

            db.insert("EmployeePayroll", null, values);
        }

        cursor.close();

        // Start PayrollDetails activity and pass the employee ID
        Intent intent = new Intent(Payroll.this, PayrollDetails.class);
        intent.putExtra("EmployeeID", id);
        startActivity(intent);
    }
}
