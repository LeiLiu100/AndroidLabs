package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;
import android.widget.EditText;
import android.widget.ImageButton;


public class ProfileActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";
    ImageButton mImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        // get the intent that got us here
        //Intent fromPrevious = getIntent();
        //String previousTyped = fromPrevious.getStringExtra("typed");
        //String someText = fromPrevious.getStringExtra("ItemOne");
        //int extraInt = fromPrevious.getIntExtra("ItemTwo", 0);
        //Put the string that was sent from FirstActivity into the edit text:
        //EditText enterText = (EditText)findViewById(R.id.email_Text);
        //enterText.setText(previousTyped);

        mImageButton = (ImageButton)findViewById(R.id.picButton);
        //mImageButton.setOnClickListener(new View.OnClickListener();
        mImageButton.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });

        Log.e(ACTIVITY_NAME, "In function:" + "onCreate");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(ACTIVITY_NAME, "In function:" + "onActivityResult");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }

    }


    @Override
    protected void onPause() {// save
        super.onPause();
        Log.e(ACTIVITY_NAME, "In function:" + "onPause");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(ACTIVITY_NAME, "In function:" + "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(ACTIVITY_NAME, "In function:" + "onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME, "In function:" + "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(ACTIVITY_NAME, "In function:" + "onStart");
    }
}
