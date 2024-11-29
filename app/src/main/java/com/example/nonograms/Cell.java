package com.example.nonograms;
import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

import java.util.Random;

public class Cell extends androidx.appcompat.widget.AppCompatButton {

    // Fields
    private boolean blackSquare; // 검정 사각형 여부
    private boolean checked; // X 표시 여부
    private static int numBlackSquares = 0; // 찾지 못한 검정 사각형 수

    Random random = new Random();

    // Constructor
    public Cell(Context context) {
        super(context);
        blackSquare = random.nextBoolean();

        if (blackSquare) {
            this.setText("B");
        } else {
            this.setText("");
        }
    }

    public boolean isBlackSquare() {
        return blackSquare;
    }

    public static int getNumBlackSquares() {
        return numBlackSquares;
    }

    public boolean markBlackSquare() {
        if (checked) {
            return true;
        }

        if (blackSquare) {
            setBackgroundColor(Color.BLACK);
            setClickable(false);
            numBlackSquares--;
            return true;
        } else {
            toggleX();
            return false;
        }
    }

    public boolean toggleX() {
        if (checked) {
            setText("");
        } else {
            setText("X");
            setTextColor(Color.RED);
        }
        checked = !checked;
        return checked;
    }
}
