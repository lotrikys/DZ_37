package com.example.lotrik.custom_view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lotrik on 18.01.16.
 */
public class MyCustomView extends View {

    Paint paint = new Paint();
    float maxX;

    int x1;
    int x2;
    int y1;
    int y2;

    int moveRoad = 0;

    float roadX = 20;

    float carX = 30;

    int carY1 = 150;
    int carYY1 = 200;
    int carY2 = 120;
    int carYY2 = 150;
    int carY3 = 200;

    int move;

    public MyCustomView(Context context) {
        super(context);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onDraw (Canvas canvas){

        super.onDraw(canvas);

        canvas.drawARGB(0, 255, 0, 0);

        drawRoad(canvas);

        drawCar(canvas);

        drawTouchpad(canvas);

        invalidate();

    }

    public void drawRoad (Canvas canvas) {

        maxX = canvas.getWidth();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);

        canvas.drawRect(0, 50, maxX, 350, paint);

        paint.setColor(Color.WHITE);
        for (;;){

            canvas.drawRect(roadX, 175, roadX + 150, 225, paint);

            roadX += 225;

            if (roadX >= canvas.getWidth()){

                roadX = moveRoad;
                if (moveRoad <= -200){
                    moveRoad = 20;
                } else {
                    moveRoad -= 5;
                }
                break;
            }
        }

    }

    public void drawCar (Canvas canvas){

        paint.setColor(Color.GREEN);

        canvas.drawRect(carX, carY1, carX + 190, carYY1, paint);

        canvas.drawRect(carX + 50, carY2, carX + 140, carYY2, paint);

        paint.setColor(Color.BLACK);

        canvas.drawCircle(carX + 50, carY3, 20, paint);

        canvas.drawCircle(carX + 140, carY3, 20, paint);

    }

    public void drawTouchpad (Canvas canvas) {

        paint.setColor(Color.BLACK);

        x1 = canvas.getWidth()/2-300;
        x2 = canvas.getWidth()/2+300;
        y1 = canvas.getHeight()/2-300;
        y2 = canvas.getHeight()/2+300;

        canvas.drawRect(x1, y1, x2, y2, paint);
    }

    @Override
    public boolean onTouchEvent (MotionEvent event){

        int xEvent = (int) event.getX();
        int yEvent = (int) event.getY();

        if (xEvent>x1 && xEvent<x2 && yEvent>y1 && yEvent<y2){

            switch (event.getAction()){

                case MotionEvent.ACTION_MOVE:

                    if (yEvent > move && carY3 < 330) {
                        carY1 += 5;
                        carY2 += 5;
                        carY3 += 5;
                        carYY1 += 5;
                        carYY2 += 5;
                    }

                    if (yEvent < move && carY2 > 50){
                        carY1 -= 5;
                        carY2 -= 5;
                        carY3 -= 5;
                        carYY1 -= 5;
                        carYY2 -= 5;
                    }

                    move = yEvent;
                    break;
            }
        }
        return true;
    }
}
