package com.example.electivecompilation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.Manifest;

import androidx.fragment.app.Fragment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.telephony.SmsManager;

public class GuidedThirteen extends Fragment {

    Button sendSMS, sendBSMS, call;
    EditText phoneNo, message;
    ProgressDialog progressDialog;
    Intent smsIntent, callIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guided_thirteen, container, false);

        init(view);  // Initialize the UI components
        sendMessage();  // Send message using SmsManager
        sendMessageBuiltIn();  // Send message using built-in SMS app
        phoneCall();  // Make a phone call

        return view;
    }

    public void init(View view) {
        progressDialog = new ProgressDialog(getActivity());
        sendSMS = view.findViewById(R.id.btnSMS);
        sendBSMS = view.findViewById(R.id.btnBSMS);
        call = view.findViewById(R.id.btnPhoneCall);
        phoneNo = view.findViewById(R.id.etPhoneNo);
        message = view.findViewById(R.id.etSMS);
    }

    public void sendMessage() {
        sendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo.getText().toString(),
                            null,
                            message.getText().toString(),
                            null,
                            null);

                    progressDialog.setTitle("Sending...");
                    progressDialog.setMessage("Message Sent!");
                    progressDialog.show();

                    Runnable progressRunnable = new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.cancel();
                        }
                    };

                    Handler pdCanceller = new Handler();
                    pdCanceller.postDelayed(progressRunnable, 3000);
                } catch (Exception e) {
                    progressDialog.setTitle("Sending...");
                    progressDialog.setMessage("Message was not delivered!");
                    progressDialog.show();

                    Runnable progressRunnable = new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.cancel();
                        }
                    };

                    Handler pdCanceller = new Handler();
                    pdCanceller.postDelayed(progressRunnable, 3000);
                }
            }
        });
    }

    public void sendMessageBuiltIn() {
        sendBSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo.getText().toString()));
                smsIntent.putExtra("sms_body", message.getText().toString());
                startActivity(smsIntent);
            }
        });
    }

    public void phoneCall() {
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNo.getText().toString()));
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    try {
                        startActivity(callIntent);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
