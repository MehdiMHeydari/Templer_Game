package org.headroyce.mehdih2022.templer;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import static android.hardware.camera2.params.RggbChannelVector.RED;

// the whole screen aka game area
public class TempleView extends View {

    private PlayerView player;
    private boolean firstDraw;
    private ArrayList<Obstacle> obstacles;
    private Random random;


    private Handler animTimer;
    private boolean animRunning;

    private boolean gameRunning;
    private Handler gameTimer;



    public TempleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        player = new PlayerView(context, attrs);
        animTimer = new Handler();
        gameTimer = new Handler();
        obstacles = new ArrayList<Obstacle>();
        random = new Random();


    }


    public void onResume() {
        animRunning = true;
        gameRunning = true;
        player.velX = 10;
        player.velY = 10;

        animTimer.post(new Templeranim());
        gameTimer.post(new gamerunner());


    }

    public void onPause() {
        gameRunning = false;
        animRunning = false;
    }


    public void onDraw(Canvas c) {
        //sets the player in the center
        if (firstDraw == false) {
            player.x = getWidth() / 2 - player.getWidth() / 2;
            player.y = getHeight() / 2 - player.getHeight() / 2;
            firstDraw = true;

        }
        player.draw(c);

        //draws each obstacle
        for (Obstacle ob : obstacles) {
            ob.draw(c);
        }
    }


    private class Templeranim implements Runnable {
        public void run() {

            //checks if cavas has been created before starting timer
            if (getWidth() == 0 || getHeight() == 0) {
                if (animRunning) {
                    animTimer.postDelayed(this, 17);
                }

                return;
            }
            //GAME LOOP
            animTimer.postDelayed(this, 17);

            //draws objects
            invalidate();


        }

    }

    //game timer
    private class gamerunner implements Runnable {
        public void run() {
            if (getWidth() == 0 || getHeight() == 0) {
                if (gameRunning) {
                    gameTimer.postDelayed(this, 100);
                }

                return;
            }
            int rand = random.nextInt(100);

            //percentages for spawn rates of obastcales then creates obstacles
            if (rand < 4 && rand > 2) {


                Obstacle rightnow = new Obstacle(getContext());

                rightnow.x = random.nextInt(getWidth() - rightnow.getItemWidth());
                rightnow.y = 0;
                rightnow.velX = 0;
                rightnow.velY = 10;

                obstacles.add(rightnow);

            }

            if (rand < 1) {
                int rand2 = random.nextInt(100);
                System.out.println("hi");
                Enemy it = new Enemy(getContext());

                it.x = random.nextInt(getWidth());
                it.y = -it.getItemHeight();


                //sets random x velocity
                it.velX = random.nextInt(5);

                //using pythagreon theroem to determin the xyvelocity so that the velocity of the ball is 5
                it.velY = (int) Math.sqrt((5 * 5) - (it.velX * it.velX));


                obstacles.add(it);
            }

            player.move();


            // moves each obsticle, checks collisons and removes them when they go off the screen
            for (int i = 0; i < obstacles.size(); i++) {

                Obstacle rightnow = obstacles.get(i);
                rightnow.move();

                if (rightnow.y > getHeight()) {
                    obstacles.remove(rightnow);
                }
                if (rightnow.x < 0) {
                    rightnow.x = 0;
                    rightnow.velX *= -1;
                }


                if (rightnow.x + rightnow.getItemWidth() >= getWidth()) {
                    rightnow.x = getWidth() - rightnow.getItemWidth();
                    rightnow.velX *= -1;
                }

                for (int q = i+1; q < obstacles.size(); q++) {
                    if (rightnow.reactTo(obstacles.get(q))) {
                        System.out.println("here");
                        obstacles.get(q).velX *= -1;
                        obstacles.get(q).velY *= -1;
                        rightnow.velY *= -1;
                        rightnow.velX *= -1;
                        /*rightnow.y += rightnow.getItemHeight();
                        rightnow.x += rightnow.getItemWidth();
                        obstacles.get(q).x  -=  obstacles.get(q).getItemWidth();
                        obstacles.get(q).y -=  obstacles.get(q).getItemHeight();*/
                    }
                }

            }

            //player collisions

            if (player.x + player.getItemWidth() > getWidth()) {
                player.x = getWidth() - player.getItemWidth();
                player.velX *= -1;
            }

            if (player.x <= 0) {
                player.x = 0;
                player.velX *= -1;
            }

            if (player.y + player.getItemHeight() > getHeight() || player.y + player.getItemHeight() < 0) {
                player.y = getHeight() - player.getItemHeight();
                player.velY *= -1;
            }

            if (player.y <= 0) {
                player.y = 0;
                player.velY *= -1;
            }



           for (Obstacle ob : obstacles) {
                if (player.reactTo(ob) == true) {
                    ob.velX *= -1;
                    ob.velY *= -1;
                    player.velY *= -1;
                    player.velX *= -1;
                    /*player.y += player.getItemHeight();
                    player.x += player.getItemWidth();
                    ob.x  -=  ob.getItemWidth();
                    ob.y -=  ob.getItemHeight();*/
                    if (ob.getHitpoints() == 0){
                        obstacles.remove(ob);
                    }
                }
            }

            //for (Obstacle ob: obstacles)

            if (gameRunning == true) {
                gameTimer.postDelayed(this, 100);

            }
        }


        //public void forceOnPlayer (float x, float y){
       // player.velx(-x*10)
       //


    }


}

