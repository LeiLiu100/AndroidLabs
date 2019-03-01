package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ChatRoomActivity extends AppCompatActivity {
    protected EditText chatEditText;
    private ArrayList<Message> chatMessage = new ArrayList<>();
    protected static final String ACTIVITY_NAME = "ChatRoomActivity";
    protected static final int SEND_MESSAGE = 1;
    protected static final int RECEIVE_MESSAGE = 2;

    ChatAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        ListView listView = (ListView) findViewById(R.id.listView);
        chatEditText = (EditText) findViewById(R.id.editChat);

        messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

        Button btSend = (Button) findViewById(R.id.buttonSend);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chatMessage.add(new Message(chatEditText.getText().toString(),SEND_MESSAGE));
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/getView()

                ContentValues newValues = new ContentValues();

                chatEditText.setText("");

            }
        });

        Button btReceive = (Button) findViewById(R.id.buttonReceive);
        btReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chatMessage.add(new Message(chatEditText.getText().toString(), RECEIVE_MESSAGE));
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/getView()

                ContentValues newValues = new ContentValues();

                chatEditText.setText("");

            }
        });
  }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        Log.i(ACTIVITY_NAME,"In onDestroy()");
    }

    //inner class ChatAdapter
    public class ChatAdapter extends ArrayAdapter<Message> {

        public ChatAdapter(Context ctx) {

            super(ctx, 0);
        }

        public int getCount() {

            return chatMessage.size();
        }

        public long getItemId(int position) {
            return position;

        }

        public Message getItem(int position) {

            return chatMessage.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatRoomActivity.this.getLayoutInflater();
            View result = null;

            if (getItem(position).getMessage_type() == RECEIVE_MESSAGE) {
                result = inflater.inflate(R.layout.activity_receiving, null);
            } else {
                result = inflater.inflate(R.layout.activity_sending, null);
            }

            TextView message = (TextView) result.findViewById(R.id.message_text);

            message.setText(getItem(position).getMessage());

            return result;
        }

    }

    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        if ((requestCode == 5)&&(resultCode == Activity.RESULT_OK)){
            Log.i(ACTIVITY_NAME, "Returned to ChatWindow.onActivityResult");
        }


    }

}
