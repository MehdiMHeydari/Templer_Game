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

                rightnow.x = random.nextInt(getWidth() - rightnow.getobstacleWidth());
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
                it.y = -it.getobstacleHeight();


                //sets random x velocity
                it.velX = random.nextInt(5);

                //using pythagreon theroem to determin the xyvelocity so that the velocity of the ball is 5
                it.velY = (int) Math.sqrt((5 * 5) - (it.velX * it.velX ));




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


                if (rightnow.x + rightnow.getobstacleWidth() >= getWidth()) {
                    rightnow.x = getWidth() - rightnow.getobstacleWidth();
                    rightnow.velX *= -1;
                }

            }

            //player collisions

            if (player.x + player.getPlayerWidth() > getWidth()) {
                player.x = getWidth() - player.getPlayerWidth();
                player.velX *= -1;
            }

            if (player.x <= 0) {
                player.x = 0;
                player.velX *= -1;
            }

            if (player.y + player.getPlayerHeight() > getHeight() || player.y + player.getPlayerHeight() < 0) {
                player.y = getHeight() - player.getPlayerHeight();
                player.velY *= -1;
            }

            if (player.y <= 0) {
                player.y = 0;
                player.velY *= -1;
            }

            if (gameRunning == true) {
                gameTimer.postDelayed(this, 100);

            }

        }
    }

}

