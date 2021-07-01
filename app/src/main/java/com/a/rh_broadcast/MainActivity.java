package com.a.rh_broadcast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonSend ;
        final EditText editText ;
         final MyBroadcastReceiver myBroadcastReceiver= new MyBroadcastReceiver() ;
        buttonSend=(Button)findViewById(R.id.btSend);
        editText=(EditText) findViewById(R.id.text);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().length()>=0)

                {
                    sendBroadcastNotification(getApplicationContext(),"setTextAction", "setText", editText.getText().toString()+"Text;");
                }
            }
        });


       BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String receiveData = intent.getStringExtra("setText");

                if (receiveData.contains("Text;")&&receiveData.length()>5) {
                  receiveData =receiveData.replace("Text;","");
                  try {
                      Dialog(MainActivity.this,receiveData);

                  }catch (Exception e){

                  }


                }
else Toast.makeText(MainActivity.this, "please insert your message!",
                        Toast.LENGTH_LONG).show();
            }
        };registerReceiver(broadcastReceiver,new IntentFilter("setTextAction"));



            }






    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        return this.getApplicationContext().registerReceiver(receiver, filter);
    }



    public static void sendBroadcastNotification(Context context, String action, String cmdName, String cmdValue) {
        Intent intent = new Intent(action);
        intent.putExtra(cmdName, cmdValue);
        context.sendBroadcast(intent);

    }
    public void Dialog(Context context,String Message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Receiving broadcasts : ");
        alertDialog.setMessage(Message);
        alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();

            }
        });

        AlertDialog dialogalert = alertDialog.create();
       dialogalert.show();
    }}