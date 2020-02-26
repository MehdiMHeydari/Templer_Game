package org.headroyce.mehdih2022.templer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Obstacle extends ItemView {





    public Obstacle (Context context, AttributeSet attrs){
        super (context, attrs);
        setItemHeight(50);
        setItemWidth(50);
        color = Color.BLUE;
        setMaxhitpoints(1);


    }


    public Obstacle (Context context){
        super (context);
        setItemHeight(50);
        setItemWidth(50);
        color = Color.BLUE;
        setMaxhitpoints(1);
        heal(getMaxhitpoints());


    }

    public void onDraw (Canvas c){
        Paint paint = new Paint();

        paint.setColor(color);

        c.drawRect(x,y, x+getItemWidth(), y+getItemHeight(), paint);

    }



    public void move(){

        this.x += this.velX;
        this.y += this.velY;
    }

   public void hit (){
        setHitpoints(-1);
   }

    
    

    public boolean reactTo (ItemView item){
        if (super.reactTo(item)) {
            hit();
        }
       return super.reactTo(item);
    }


}
