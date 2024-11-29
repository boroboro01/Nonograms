package com.example.nonograms;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Life life;

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
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        TextView lifeTextView = findViewById(R.id.lifeTextView);
        life = new Life(3, lifeTextView);

        for (int i = 0; i < 8; i++) {
            TableRow tableRow = new TableRow(this);

            int bCount = 0;

            for (int j = 0; j < 8; j++) {
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(100, 100);
                layoutParams.setMargins(4, 4, 4, 4);

                if (i > 2 && j > 2) {
                    Cell cell = new Cell(this);

                    if (cell.isBlackSquare()) {
                        bCount++;
                    }

                    cell.setGravity(Gravity.CENTER);
                    cell.setPadding(0, 0, 0, 0);
                    cell.setBackgroundColor(Color.LTGRAY);

                    cell.setOnClickListener(v -> {
                        if (toggleButton.isChecked()) {
                            cell.toggleX();
                        } else {
                            boolean success = cell.markBlackSquare();
                            if (success) {
                                if (Cell.getNumBlackSquares() == 0) {
                                    winGame();
                                }
                            } else {
                                life.decreaseLife();
                                if (life.isGameOver()) {
                                    endGame();
                                }
                            }
                        }
                    });

                    cell.setLayoutParams(layoutParams);
                    tableRow.addView(cell);
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
        for (int row = 3; row < 8; row++) {
            int[] rowCounts = countConsecutiveBs(tableLayout, row, true);
            TableRow tableRow = (TableRow) tableLayout.getChildAt(row);

            TextView textView1 = (TextView) tableRow.getChildAt(0);
            TextView textView2 = (TextView) tableRow.getChildAt(1);
            TextView textView3 = (TextView) tableRow.getChildAt(2);

            textView1.setText(String.valueOf(rowCounts[0]));
            textView2.setText(String.valueOf(rowCounts[1]));
            textView3.setText(String.valueOf(rowCounts[2]));
        }

        for (int col = 3; col < 8; col++) {
            int[] colCounts = countConsecutiveBs(tableLayout, col, false);

            for (int resultRow = 0; resultRow < 3; resultRow++) {
                TableRow headerRow = (TableRow) tableLayout.getChildAt(resultRow);
                TextView resultView = (TextView) headerRow.getChildAt(col);
                resultView.setText(String.valueOf(colCounts[resultRow]));
            }
        }
    } // on Create

    private int[] countConsecutiveBs(TableLayout tableLayout, int fixedIndex, boolean isRow) {
        int[] counts = new int[3];
        int consecutiveCount = 0;
        int countIndex = 0;

        for (int variableIndex = 3; variableIndex < 8; variableIndex++) {
            TableRow currentRow = isRow
                    ? (TableRow) tableLayout.getChildAt(fixedIndex)
                    : (TableRow) tableLayout.getChildAt(variableIndex);
            Button button = (Button) (isRow
                    ? currentRow.getChildAt(variableIndex)
                    : currentRow.getChildAt(fixedIndex));

            if ("B".equals(button.getText().toString())) {
                consecutiveCount++;
            } else if (consecutiveCount > 0) {
                if (countIndex < 3) {
                    counts[countIndex] = consecutiveCount;
                    countIndex++;
                }
                consecutiveCount = 0;
            }
        }

        if (consecutiveCount > 0 && countIndex < 3) {
            counts[countIndex] = consecutiveCount;
        }

        return counts;
    } // count consecutive Bs

    private void winGame() {
        Toast.makeText(this, "Congratulations! You Win!", Toast.LENGTH_LONG).show();
        unableEvent();
    }

    private void endGame() {
        Toast.makeText(this, "Game Over, You lose!", Toast.LENGTH_LONG).show();
        unableEvent();
    }

    private void unableEvent() {
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        for (int i = 3; i < 8; i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            for (int j = 3; j < 8; j++) {
                Cell cell = (Cell) row.getChildAt(j);
                cell.setClickable(false);
            }
        }
    }
}