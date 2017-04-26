package main.sophie.tetrisgame;

import android.widget.TableLayout;

import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.AbstractShape;
import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.ShapeCreator;

/**
 * Created by Laptop on 23.4.2017 г..
 */

public class GameThread implements Runnable {

    private MainActivity activity;
    TableLayout[][] table;
    private ShapeCreator creator;
    static AbstractShape shape;
    private boolean falling = true;
    static boolean isPaused = false;
    private boolean isFinished = false;
    private Thread gameThread;
    private Runnable UICreateShape = new Runnable() {
        @Override
        public void run() {
            shape = creator.createShape();

        }
    };
    private Runnable UIShapeDown = new Runnable() {
        @Override
        public void run() {
            if (!shape.down()) {
                falling = false;
                int score = shape.updateTable();
                if (score > 0) {
                    activity.score.setText(String.valueOf(score));
                }
            }
        }
    };

    public GameThread(MainActivity activity, TableLayout[][] table) {
        this.activity = activity;
        this.table = table;
        this.creator = new ShapeCreator(table);
    }

    @Override
    public void run() {
        generateGameThread();
        gameThread.start();
    }

    private void generateGameThread() {
        gameThread = new Thread() {

            @Override
            public void run() {
                while (!isFinished) {
                    activity.runOnUiThread(UICreateShape);
                    Thread figureThread = generateFigureThread();
                    figureThread.start();
                    try {
                        figureThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    private Thread generateFigureThread() {
        Thread figureThread = new Thread() {

            @Override
            public synchronized void run() {
                try {
                    Thread.sleep(1000);
                    falling = true;
                    while (falling) {
                        while (isPaused) {
                            //being paused
                        }
                        Thread.sleep(1000);
                        activity.runOnUiThread(UIShapeDown);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();//preserve the message
                    return;//
                }
            }
        };
        return figureThread;
    }
}
