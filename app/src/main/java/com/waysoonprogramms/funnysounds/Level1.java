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
    private MediaPlayer good;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        container = findViewById(R.id.container);

        imageView1 = findViewById(R.id.imageView1);
        imageView1.setOnTouchListener(touchListener);

        imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnTouchListener(touchListener);

        imageView7 = findViewById(R.id.imageView7);
    }

    public void back(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private int xDelta, yDelta, topY, leftX, rightX, bottomY, eX, eY;
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            final int x = (int) event.getRawX();
            final int y = (int) event.getRawY();
            topY = imageView7.getTop();
            leftX = imageView7.getLeft();
            rightX = imageView7.getRight();
            bottomY = imageView7.getBottom();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                    xDelta = x - lParams.leftMargin;
                    yDelta = y - lParams.topMargin;
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    if (view.getLeft() >= leftX
                            && view.getRight() <= rightX
                            && view.getTop() >= topY
                            && view.getBottom() <= bottomY) {
                        good = MediaPlayer.create(Level1.this, R.raw.good_answ);
                        goodSoundPlay();
                        Toast.makeText(Level1.this, "Молодец!", Toast.LENGTH_SHORT).show();
                        view.setVisibility(View.INVISIBLE);
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