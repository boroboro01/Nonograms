package com.example.nonograms;

import android.widget.TextView;

public class Life {

    private int life; // 현재 생명력
    private final TextView lifeTextView; // 생명력 표시를 위한 TextView

    // Constructor
    public Life(int initialLife, TextView lifeTextView) {
        this.life = initialLife;
        this.lifeTextView = lifeTextView;
        updateLifeUI(); // 초기값 업데이트
    }

    public void decreaseLife() {
        if (life > 0) {
            life--; // 생명력 감소
            updateLifeUI();
        }
    }

    public void increaseLife() {
        life++;
        updateLifeUI();
    }

    public int getLife() {
        return life;
    }

    private void updateLifeUI() {
        lifeTextView.setText("Life: " + life);
    }

    public boolean isGameOver() {
        return life == 0;
    }
}