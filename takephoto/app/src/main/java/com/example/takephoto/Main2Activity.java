package com.example.takephoto;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.RenderScript;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
public class Main2Activity extends AppCompatActivity {
    Handler mHandler;
    TextView tv;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        data=intent.getStringExtra("extra_data");
        TextView textView = (TextView) findViewById(R.id.text_view2);
        textView.setText(data);
        new sentMessageThread().start();
        tv = (TextView) findViewById(R.id.tv);
        mHandler = new MessageHandler();
    }
    class MessageHandler extends Handler{
        public void handleMessage(Message msg){
            tv.setText(msg.obj.toString());
        }
    }
    class sentMessageThread extends Thread{
        public void run(){
            try {
                Socket socket=new Socket("192.168.137.1",8080);
                DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(data);
               // DataInputStream dis=new DataInputStream(socket.getInputStream());
               // Message msg=mHandler.obtainMessage();
              //  msg.obj=dis.readUTF();
               // mHandler.sendMessage(msg);
                dos.close();
              //  dis.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
