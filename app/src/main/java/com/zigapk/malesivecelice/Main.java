package com.zigapk.malesivecelice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

        findViewById(R.id.n2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("N2");
                tv.setTextColor(getResources().getColor(R.color.red));
            }
        });
        findViewById(R.id.n1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("N1");
                tv.setTextColor(getResources().getColor(R.color.red));
            }
        });
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
                tv.setTextColor(getResources().getColor(R.color.black));
            }
        });
        findViewById(R.id.d1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("D1");
                tv.setTextColor(getResources().getColor(R.color.black));
            }
        });
        findViewById(R.id.d2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("D2");
                tv.setTextColor(getResources().getColor(R.color.black));
            }
        });
    }
}
