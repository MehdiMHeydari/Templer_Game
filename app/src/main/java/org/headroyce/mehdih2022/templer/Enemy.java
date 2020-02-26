package org.headroyce.mehdih2022.templer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;


public class Enemy extends Obstacle {

    public Enemy (Context context, AttributeSet attrs){
        super(context, attrs);
        setItemHeight(50);
        setItemWidth(50);
        color = Color.RED;
        setMaxhitpoints(2);
        heal(getMaxhitpoints());

    }

    public Enemy(Context context){
        super (context);
        setItemHeight(50);
        setItemWidth(50);
        color = Color.RED;
        setMaxhitpoints(2);
        heal(getMaxhitpoints());


    }

    public void onDraw (Canvas c){
        Paint paint = new Paint();

        paint.setColor(color);

        int radius = (getItemWidth()+1)/2;

        c.drawCircle(x + radius,y + radius, radius, paint);

    }

    public void hit(){
        super.hit();
        color = Color.MAGENTA;
    }
    public void move(){
        this.x += this.velX;
        this.y += this.velY;
    }

    public boolean reactTo (ItemView item){
        if (super.reactTo(item)) {
            hit();
        }
        return super.reactTo(item);
    }
    //signum
}
