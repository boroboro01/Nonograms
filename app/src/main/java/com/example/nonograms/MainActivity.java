package com.example.nonograms;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        Random random = new Random();


        for (int i = 0; i < 8; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < 8; j++) {
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(100, 100);
                layoutParams.setMargins(4, 4, 4, 4);

                if (i > 2 && j > 2) {
                    Button button = new Button(this);

                    if (random.nextBoolean()) {
                        button.setText("B");
                    } else {
                        button.setText("");
                    }

                    button.setGravity(Gravity.CENTER);
                    button.setPadding(0, 0, 0, 0);
                    button.setBackgroundColor(Color.LTGRAY);


                    button.setOnClickListener(v -> button.setBackgroundColor(Color.BLACK));

                    button.setLayoutParams(layoutParams);
                    tableRow.addView(button);
                } else {
                    TextView textView = new TextView(this);
                    textView.setGravity(Gravity.CENTER);
                    textView.setLayoutParams(layoutParams);

                    if (i <= 2 && j > 2) {
                        textView.setText("0");
                    } else if (i > 2 && j < 3) {
                        textView.setText("0");
                    } else {
                        textView.setText("");
                    }

                    textView.setBackgroundColor(Color.WHITE);


                    tableRow.addView(textView);
                }
            }
            tableLayout.addView(tableRow);
        }



    }
}