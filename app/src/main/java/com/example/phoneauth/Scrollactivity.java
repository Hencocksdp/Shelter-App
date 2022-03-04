package com.example.phoneauth;

import androidx.appcompat.app.AppCompatActivity ;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Scrollactivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton button8, button1, button2, button3, button4, button5, button6, button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollactivity);
        ImageButton button8 = (ImageButton) findViewById(R.id.imageButton);
        ImageButton button1 = (ImageButton) findViewById(R.id.imageButton1);
        ImageButton button2= (ImageButton) findViewById(R.id.imageButton2);
        ImageButton button3= (ImageButton) findViewById(R.id.imageButton3);
        ImageButton button4= (ImageButton) findViewById(R.id.imageButton4);
        ImageButton button5 = (ImageButton) findViewById(R.id.imageButton5);
        ImageButton button6 = (ImageButton) findViewById(R.id.imageButton6);
        ImageButton button7 = (ImageButton) findViewById(R.id.imageButton7);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton1:

                openMainactivity();
                break;
            case R.id.imageButton2:

                openMainactivity();
                break;
            case R.id.imageButton3:

                openMainactivity();
                break;
            case R.id.imageButton4:

                openMainactivity();
                break;
            case R.id.imageButton5:

                openMainactivity();

                break;
            case R.id.imageButton6:

                openMainactivity();
                break;
            case R.id.imageButton7:

                openMainactivity();
                break;
        }

    }

    private void openMainactivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
