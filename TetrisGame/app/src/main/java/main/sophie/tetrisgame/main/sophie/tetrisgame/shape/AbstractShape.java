package main.sophie.tetrisgame.main.sophie.tetrisgame.shape;

import android.graphics.Color;

import main.sophie.tetrisgame.MainActivity;

/**
 * Created by Laptop on 11.4.2017 г..
 */

public abstract class AbstractShape {
    //4 X points of the figure
    protected int[] coordinatesX;
    //4 Y points of the figure
    protected int[] coordinatesY;
    protected boolean isRotated = false;

    protected int[][] table;
    protected MainActivity activity;

    public AbstractShape(int[][] table, MainActivity activity) {
        this.table = table;
        this.activity = activity;
        this.coordinatesX = new int[4];
        this.coordinatesY = new int[4];
    }

    public abstract void rotate();

    public void left() {
        int[] helper = new int[4];
        //check if you can add -1 to X - else return
        for (int i = 0; i < coordinatesX.length; i++) {
            helper[i] = coordinatesX[i] - 1;
            if (helper[i] < 0) {
                return;
            }
        }
        // update view properties
        updateX(helper);
    }

    public void right() {
        int[] helper = new int[4];
        //check if you can add -1 to X - else return
        for (int i = 0; i < coordinatesX.length; i++) {
            helper[i] = coordinatesX[i] + 1;
            if (helper[i] >= MainActivity.W) {
                return;
            }
        }
        // update view properties
        updateX(helper);
    }

    public void down() {
        //get current positions
        //check if you can add 1 to X - else restart thread
        // add 1 to Y
        // update view properties
        int[] helper = new int[4];
        //check if you can add -1 to X - else return
        for (int i = 0; i < coordinatesY.length; i++) {
            helper[i] = coordinatesY[i] + 1;
            if (helper[i] >= MainActivity.H) {
                return;
            }
        }
        // update view properties
        updateY(helper);
    }

    private void updateX(int[] newCoordinates) {
        for (int i = 0; i < 4; i++) {
            activity.findViewById(table[coordinatesX[i]][coordinatesY[i]]).setBackgroundColor(Color.WHITE);
            activity.findViewById(table[newCoordinates[i]][coordinatesY[i]]).setBackgroundColor(Color.RED);
        }
        coordinatesX = newCoordinates;
    }

    private void updateY(int[] newCoordinates) {
        for (int i = 0; i < 4; i++) {
            activity.findViewById(table[coordinatesX[i]][coordinatesY[i]]).setBackgroundColor(Color.WHITE);
            activity.findViewById(table[coordinatesX[i]][newCoordinates[i]]).setBackgroundColor(Color.RED);
        }
        coordinatesY = newCoordinates;
    }

}
