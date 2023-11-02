package com.waysoonprogramms.funnysounds;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Level1 extends AppCompatActivity {

    private View container;
    private View imageView1;
    private View imageView2;
    private View imageView7;
    private View imageView4;
    private View imageView5;
    private View imageView6;
    private View imageView8;
    private View imageView;
    private MediaPlayer good;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        container = findViewById(R.id.container);

        setContentView(R.layout.activity_level1);
        container = findViewById(R.id.container);

        imageView4 = findViewById(R.id.imageView4);
        imageView4.setOnTouchListener(touchListener);

        imageView5 = findViewById(R.id.imageView5);
        imageView5.setOnTouchListener(touchListener);

        imageView6 = findViewById(R.id.imageView6);
        imageView6.setOnTouchListener(touchListener);

        imageView8 = findViewById(R.id.imageView8);
        imageView8.setOnTouchListener(touchListener);

        imageView1 = findViewById(R.id.imageView1);
        imageView1.setOnTouchListener(touchListener);

        imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnTouchListener(touchListener);

        imageView7 = findViewById(R.id.imageView7);

        imageView = findViewById(R.id.imageView);
    }

    private int xDelta, yDelta, topA, leftA, rightA, bottomA, topB, leftB, rightB, bottomB;
    private int viewVisible = 6;


    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            final int x = (int) event.getRawX();
            final int y = (int) event.getRawY();

            topA = imageView7.getTop();
            leftA = imageView7.getLeft();
            rightA = imageView7.getRight();
            bottomA = imageView7.getBottom();

            topB = imageView.getTop();
            leftB = imageView.getLeft();
            rightB = imageView.getRight();
            bottomB = imageView.getBottom();

            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                    xDelta = x - lParams.leftMargin;
                    yDelta = y - lParams.topMargin;
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    if (view.getLeft() >= leftA
                            && view.getRight() <= rightA
                            && view.getTop() >= topA
                            && view.getBottom() <= bottomA
                            && (view == imageView2
                            || view == imageView5
                            || view == imageView6)) {
                        good = MediaPlayer.create(Level1.this, R.raw.good_answ);
                        goodSoundPlay();
                        view.setVisibility(View.INVISIBLE);
                        viewVisible--;
                    }

                    if (view.getLeft() >= leftB
                            && view.getRight() <= rightB
                            && view.getTop() >= topB
                            && view.getBottom() <= bottomB
                            && (view == imageView8
                            || view == imageView4
                            || view == imageView1)) {
                        good = MediaPlayer.create(Level1.this, R.raw.good_answ);
                        goodSoundPlay();
                        view.setVisibility(View.INVISIBLE);
                        viewVisible--;
                    }

                    if (viewVisible == 0) {
                        Toast.makeText(Level1.this, "Умница!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    if (x - xDelta + view.getWidth() <= container.getWidth()
                            && y - yDelta + view.getHeight() <= container.getHeight()
                            && x - xDelta >= 0
                            && y - yDelta >= 0) {
                        FrameLayout.LayoutParams layoutParams =
                                (FrameLayout.LayoutParams) view.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                    }

                    break;
                }
            }
            container.invalidate();
            return true;
        }
    };

    private void goodSoundPlay() {
        if (good.isPlaying()) {
            good.stop();
        }
        good.start();
    }
}