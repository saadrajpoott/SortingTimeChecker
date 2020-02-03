package com.example.sortingtimechecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DisplayArray extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_array);

        String receivedArray = getIntent().getStringExtra("string_array");
        TextView displayArray = findViewById(R.id.display_array);
        displayArray.setMovementMethod(new ScrollingMovementMethod());
        displayArray.setText(receivedArray);


    }
}
