package com.thundersoft.finalpro;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private PMview pMview;
    private EditText editText;
    private Button button;
    private ImageView iv;
    private int PM=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pMview = (PMview) findViewById(R.id.my_view);
        editText = (EditText) findViewById(R.id.edit);
        button = (Button) findViewById(R.id.btn);
        iv=(ImageView)findViewById(R.id.imageView);
        button.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
        case R.id.btn:
            starttime();
            break;
        default:
            break;

        }
    }
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    editText.setText(String.valueOf(PM));
                    int a =Integer.valueOf(editText.getText().toString());
                    pMview.setCurrentNumAnim(a);
                    break;
                case 2:
                    editText.setText(String.valueOf(PM));
                    int a1 =Integer.valueOf(editText.getText().toString());
                    pMview.setCurrentNumAnim(a1);
                    iv.setImageResource(R.drawable.pm);
                    break;
                default:
                    break;
            }
        }
    };
    void starttime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int min=1;
                int max=500;
                Random random = new Random();
                PM = random.nextInt(max)%(max-min+1) + min;
                handler.sendEmptyMessage(1);
                handler.postDelayed(this, 3000);
                }
            }
        ).start();
    }

}
