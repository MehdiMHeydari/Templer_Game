package org.headroyce.mehdih2022.templer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PlayerView extends View {
    public int x,y;
    public int velX, velY;

    private int width, height; // has to be private because width cannot be negative
    private int color; // has to be private because cant be null


    public PlayerView (Context context, AttributeSet attrs){
        super (context, attrs);

        width = 50;
        height = 50;
        color = Color.BLACK;
    }

    public void onDraw (Canvas c){
        Paint paint = new Paint();

        paint.setColor(color);

        int radius = (width+1)/2;

        c.drawCircle(x + radius,y + radius, radius, paint);


    }

    public int getPlayerWidth() {
        return width;
    }

    public void setPlayerWidth(int width) {
        if ( width >= 0) {
            this.width = width;
        }
    }

    public int getPlayerHeight() {
        return height;
    }

    public void setPlayerHeight(int height) {
        if (height >= 0) {
            this.height = height;
        }
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getwidth(){ return width;}

    public void move(){

        this.x += this.velX;
        this.y += this.velY;
    }


}
