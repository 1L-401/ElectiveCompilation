package com.example.electivecompilation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

public class GuidedFourteen extends Fragment {

    Button send, capturePic;
    EditText receiver, subject, message;
    ImageView pic;
    Intent intent, chooser;
    public static final int RequestPermissionCode = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guided_fourteen, container, false);

        init(view);  // Initialize the views
        sendEmail();
        enableRuntimePermission();
        capturePic();

        return view;
    }

    // Initialize the views
    public void init(View view){
        receiver = view.findViewById(R.id.etReceiver);
        subject = view.findViewById(R.id.etSubject);
        message = view.findViewById(R.id.etMessage);
        pic = view.findViewById(R.id.ivPic);
        send = view.findViewById(R.id.btnSend);
        capturePic = view.findViewById(R.id.btnCapturePic);
    }

    // Send email logic
    public void sendEmail(){
        send.setOnClickListener(v -> {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to = {receiver.getText().toString()};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
            intent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
            intent.setType("message/rfc822");
            chooser = intent.createChooser(intent,"Send Email");

            if(receiver.getText().toString().isEmpty()){
                receiver.setError("Email required!");
            } else {
                startActivity(chooser);
            }
        });
    }

    // Handling the result from capturing an image
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 7 && resultCode == getActivity().RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            pic.setImageBitmap(bitmap);
        }
    }

    // Request permission for camera if not already granted
    public void enableRuntimePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)){
            Toast.makeText(getActivity(), "CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }

    // Capture image from camera
    public void capturePic(){
        capturePic.setOnClickListener(v -> {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 7);
        });
    }
}
