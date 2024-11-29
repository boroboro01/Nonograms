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



        for (int i = 0; i < 8; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < 8; j++) {
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(8, 8, 8, 8);

                // 셀 크기 고정
                textView.setWidth(100);  // 고정된 너비 (픽셀 단위)
                textView.setHeight(100); // 고정된 높이 (픽셀 단위)

                // 텍스트 설정
                if (i < 3 && j > 2) {
                    textView.setText("0");
                } else if (i > 3 && j < 3) {
                    textView.setText("0");
                } else {
                    textView.setText("");
                }

                // 배경색 설정 (체스판 스타일)
                if ((i + j) % 2 == 0) {
                    textView.setBackgroundColor(Color.LTGRAY);
                } else {
                    textView.setBackgroundColor(Color.WHITE);
                }

                tableRow.addView(textView);
            }
            tableLayout.addView(tableRow);
        }




    }
}