package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.proyecto.model.User;

public class BuyActivity extends AppCompatActivity {
    User user;
    private TextView info;

    private Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        info = findViewById(R.id.textView20);
        boton = findViewById(R.id.button7);
        boton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });
        Intent intent = getIntent();
        if (intent != null){
            user = (User) intent.getSerializableExtra("usuario");
            if (user != null ){
                prueba();
            }
        }
    }
    public void prueba(){

        info.setText(user.toString());
    }
    }
