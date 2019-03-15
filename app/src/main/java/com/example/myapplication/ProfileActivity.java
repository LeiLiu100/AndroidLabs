package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.graphics.Bitmap;
import android.widget.EditText;
import android.provider.MediaStore;


public class ProfileActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ProfileActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageButton mImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // get the intent that got us here
        Intent fromPrevious = getIntent();
        String previousTyped = fromPrevious.getStringExtra("typed");
        //Put the string that was sent from FirstActivity into the edit text:
        EditText enterText = (EditText)findViewById(R.id.editText3);
        enterText.setText(previousTyped);

        mImageButton = (ImageButton) findViewById(R.id.buttonTakePicture);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            final int REQUEST_IMAGE_CAPTURE = 1;
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }

            private void dispatchTakePictureIntent(){
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }

        });

        Button chatBt = (Button) findViewById(R.id.buttonGotoChat);
        chatBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ChatRoomActivitylab5.class);
                startActivity(intent);
            }
        });

        //Code for Lab 6
        Button toolBt = (Button) findViewById(R.id.buttonGotoToolbar);
        toolBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, TestToolbar.class);
                startActivity(intent);
            }
        });

        Log.e(ACTIVITY_NAME, "In onCreate()");

           }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }
        Log.e(ACTIVITY_NAME, "In onActivityResult()");
    }

}
