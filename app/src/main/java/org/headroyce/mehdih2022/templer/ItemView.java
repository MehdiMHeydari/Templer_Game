package org.headroyce.mehdih2022.templer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public abstract class ItemView extends View {

    private int hitpoints;
    private int maxhitpoints;

    public int x,y;
    public int velX, velY;
    public int color;

    private int width;
    private int height;

public ItemView (Context context, AttributeSet attrs){
    super(context, attrs);

}

public ItemView(Context context){
    super(context);
}

private void hit(ItemView i){



}

    public int getMaxhitpoints() {
        return maxhitpoints;
    }

    public void setMaxhitpoints(int maxhitpoints) {
        this.maxhitpoints = maxhitpoints;
    }

    public void heal (float add){
    if (this.hitpoints+ add <= this.maxhitpoints){
        this.hitpoints += add;
    }
    }


    public int getItemWidth() {
        return width;
    }

    public void setItemWidth(int width) {
        if (width >=0) {
            this.width = width;
        }
    }


    public int getItemHeight() {
        return height;
    }

    public void setItemHeight(int height) {
        if (height >= 0) {
            this.height = height;
        }
    }

    public boolean reactTo (ItemView item){

    // FIX WIDTH AND HEIFHT ARE CANVAS NOT ITEMS
    //left side
    if(this.x > item.x + item.getItemWidth()){
        System.out.println("1");
        return false;
    }
    //right side
     if(this.x + this.getItemWidth() < item.x){
         System.out.println("2");
         return false;
     }

     //bottom
     if(this.y + this.getItemHeight() < item.y){
         System.out.println("3");
            return false;
      }

      //top
      if(this.y > item.y + item.getItemHeight()){
          System.out.println("4");
            return false;
      }

        return true;
    }

}
