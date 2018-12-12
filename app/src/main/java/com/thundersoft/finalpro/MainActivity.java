package com.thundersoft.finalpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private PMview pMview;
    private EditText editText;
    private Button button;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pMview = (PMview) findViewById(R.id.my_view);
        editText = (EditText) findViewById(R.id.edit);
        button = (Button) findViewById(R.id.btn);
        iv=(ImageView)findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a =Integer.valueOf(editText.getText().toString());
                pMview.setCurrentNumAnim(a);
                if (a<=100){
                    iv.setImageResource(R.drawable.sunny);
                }else{
                    iv.setImageResource(R.drawable.pm);
                }
            }
        });
    }
}
