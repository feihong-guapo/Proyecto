package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.model.DataFormManager;
import com.example.proyecto.model.User;

public class MainActivity7 extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView textView;
    private ImageButton next;
    private ImageButton before;
    private String selectedPriceType;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        next = findViewById(R.id.imageButton14);
        before = findViewById(R.id.imageButton20);
        Intent intent = getIntent();
        if (intent != null){
            user = (User) intent.getSerializableExtra("usuario");
            if (user != null ){
                Toast.makeText(this, user.toString(), Toast.LENGTH_LONG);
            }
        }
        final TextView textView = findViewById(R.id.textViewDinero);
        SeekBar seekBar = findViewById(R.id.seekBar);

        next.setEnabled(false);
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
                selectedPriceType = String.valueOf(progress);
                next.setEnabled(true);
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
        DataFormManager.getInstance().saveData("selectedPriceType", selectedPriceType);
        intent.putExtra("usuario", user);
        startActivity(intent);
    }
    public void before() {
        Intent intent = new Intent(this, Form8.class);
        intent.putExtra("usuario", user);
        startActivity(intent);
    }

}