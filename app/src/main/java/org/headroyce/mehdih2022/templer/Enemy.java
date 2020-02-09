package org.headroyce.mehdih2022.templer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;


public class Enemy extends Obstacle {

    public Enemy (Context context, AttributeSet attrs){
        super(context, attrs);
        setobstacleHeight(50);
        setobtsacleWidth(50);
        color = Color.RED;
    }

    public Enemy(Context context){
        super (context);
        setobstacleHeight(50);
        setobtsacleWidth(50);
        color = Color.RED;

    }

    public void onDraw (Canvas c){
        Paint paint = new Paint();

        paint.setColor(color);

        int radius = (getobstacleWidth()+1)/2;

        c.drawCircle(x + radius,y + radius, radius, paint);

    }

    public void move(){
        this.x += this.velX;
        this.y += this.velY;
    }

    //signum
}
