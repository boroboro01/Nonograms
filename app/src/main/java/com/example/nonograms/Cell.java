package com.example.nonograms;
import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

import java.util.Random;

public class Cell extends androidx.appcompat.widget.AppCompatButton {

    private boolean blackSquare;
    private boolean checked;
    private static int numBlackSquares = 0;

    Random random = new Random();

    public Cell(Context context) {
        super(context);
        blackSquare = random.nextBoolean();

        if (blackSquare) {
            this.setText("B");
            numBlackSquares++;
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
