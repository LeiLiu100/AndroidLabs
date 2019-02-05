package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPre;
    EditText userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//read
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_linear);
        //setContentView(R.layout.activity_main_grid);
        //setContentView(R.layout.activity_main_relative);
        setContentView(R.layout.activity_main_lab3);

        //this.getDelegate().
        userEmail = (EditText) findViewById(R.id.email_Text);
        sharedPre = getSharedPreferences("FileUserName", Context.MODE_PRIVATE);
        String savedString = sharedPre.getString("UserEmail", "");
        userEmail.setText(savedString);

        Button loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener( c -> {
            Intent profilePage = new Intent(MainActivity.this, ProfileActivity.class);
            //Give directions to go from this page, to ProfileActivity
            EditText et = (EditText)findViewById(R.id.email_Text);
            profilePage.putExtra("ItemOne", "Some text");
            profilePage.putExtra("ItemTwo", 500);
            profilePage.putExtra("typed", et.getText().toString());
            //Now make the transition:
            startActivityForResult( profilePage, 345);
        });
    }

    @Override
    protected void onPause() {// save
        super.onPause();
        //get an editor object
        //sharedPre = getSharedPreferences("FileUserName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        //save what was typed under the name "ReserveName"
        String userTypedEmail = userEmail.getText().toString();
        editor.putString("UserEmail", userTypedEmail);

        //write it to disk:
        editor.commit();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int i = 0;
        i++;
        //if request code is 345, then we are coming back from ThirdActivity, as written on line 38
        if(requestCode == 345)
        {
            // resultCode will only be 60 if the user clicks on the back button on page 3 (ThirdActivity.java line 39)
            if(resultCode == 60)
            {
                EditText et = (EditText)findViewById(R.id.email_Text);
                String fromPageThree = data.getStringExtra("NextPageTyped");
                et.setText(fromPageThree);
                Log.i("Back", "Message");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
