package org.headroyce.mehdih2022.templer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PlayerView extends ItemView {

    public PlayerView (Context context, AttributeSet attrs){
        super (context, attrs);

        setItemWidth(50);
        setItemHeight(50);
        color = Color.BLACK;
        setMaxhitpoints(5);

    }

    public void onDraw (Canvas c){
        Paint paint = new Paint();

        paint.setColor(color);

        int radius = (getItemWidth()+1)/2;

        c.drawCircle(x + radius,y + radius, radius, paint);


    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public void move(){

        this.x += this.velX;
        this.y += this.velY;
    }

    public boolean reactTo (ItemView item){
        super.reactTo(item);
        return true;
    }



}
