package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity7 extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView textView;
    private Button next;
    private Button before;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        next = findViewById(R.id.button28);
        before = findViewById(R.id.button27);

        final TextView textView = findViewById(R.id.textViewDinero);
        SeekBar seekBar = findViewById(R.id.seekBar);


        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                next();
            }
        });
        before.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                before();
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Actualiza el TextView directamente sin formato
                textView.setText(String.valueOf(progress) + "€");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });
    }
    public void next() {
        Intent intent = new Intent(this, Form10.class);
        startActivity(intent);
    }
    public void before() {
        Intent intent = new Intent(this, Form8.class);
        startActivity(intent);
    }

}