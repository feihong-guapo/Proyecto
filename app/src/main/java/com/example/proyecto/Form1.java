package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.proyecto.model.DataFormManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Form1 extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private List<Integer> mLayouts = new ArrayList<>();
    private Button next;

    private int imageIndex = 0;
    private int[] images = new int[] {R.drawable.group_1113, R.drawable.group_1134, R.drawable.group_1135, R.drawable.group_1114, R.drawable.group_1139, R.drawable.group_1141, R.drawable.group_1142};
    private ImageButton buttonNext;
    private ImageButton buttonPrevious;
    private ImageView imageView;
    private String[] imageDescriptions = new String[] {
            "Berlina",
            "SUV 4x4",
            "Ranchera",
            "Urbano",
            "Deportivo",
            "Comercial",
            "Monovolumen"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);
        next = findViewById(R.id.button16);
        buttonNext = findViewById(R.id.imageButton2);
        buttonPrevious = findViewById(R.id.imageButton4);
        imageView = findViewById(R.id.imageView5);
        next.setEnabled(false);
        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                next();
            }
        });
        imageView.setImageResource(images[imageIndex]);

        buttonPrevious.setOnClickListener(v -> {
            if (imageIndex > 0) {
                imageIndex--;
                imageView.setImageResource(images[imageIndex]);
            }
        });

        buttonNext.setOnClickListener(v -> {
            if (imageIndex < images.length - 1) {
                imageIndex++;
                imageView.setImageResource(images[imageIndex]);
                next.setEnabled(true);
            }
        });
    }
    public void next() {
        Intent intent = new Intent(this, Form2.class);
        if (imageIndex >= 0 && imageIndex < imageDescriptions.length) {
            DataFormManager.getInstance().saveData("modelo", imageDescriptions[imageIndex]);
        }
        startActivity(intent);
    }
}



