package org.headroyce.mehdih2022.templer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Obstacle extends View {

    public int x,y;
    public int velX, velY;
    public int color;

    private int width;
    private int height;

    public Obstacle (Context context, AttributeSet attrs){
        super (context, attrs);
        
        
        height = 50;
        width = 50;
        color = Color.BLUE;

    }

    public Obstacle (Context context){
        super (context);
       height = 50;
        width = 50;
        color = Color.BLUE;


    }

    public void onDraw (Canvas c){
        Paint paint = new Paint();

        paint.setColor(color);

        c.drawRect(x,y, x+width, y+height, paint);

    }

    public void move(){

        this.x += this.velX;
        this.y += this.velY;
    }

    public int getobstacleWidth() {
        return width;
    }

    public void setobtsacleWidth(int width) {
        if ( width >= 0) {
            this.width = width;
        }
    }

    public int getobstacleHeight() {
        return height;
    }

    public void setobstacleHeight(int height) {
        if (height >= 0) {
            this.height = height;
        }
    }


}
