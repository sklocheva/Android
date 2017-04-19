package main.sophie.tetrisgame.main.sophie.tetrisgame.shape;

import android.graphics.Color;
import android.widget.TableLayout;

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

    protected TableLayout[][] table;

    public AbstractShape(TableLayout[][] table) {
        this.table = table;
        this.coordinatesX = new int[4];
        this.coordinatesY = new int[4];
    }

    public abstract void rotate();

    //make internal after tests
    public abstract void draw();

    /**
     * checks for collisions on the left
     *
     * @return true if there are collisions
     */
    abstract boolean checkCollisionLeft();

    /**
     * checks for collisions on the right
     *
     * @return true if there are collisions
     */
    abstract boolean checkCollisionRight();

    /**
     * checks for collisions on going down
     *
     * @return true if there are collisions
     */
    abstract boolean checkCollisionDown();

    public void left() {
        int[] helper = new int[4];
        //check if you can add -1 to X - else return
        for (int i = 0; i < coordinatesX.length; i++) {
            helper[i] = coordinatesX[i] - 1;
            if (helper[i] < 0) {
                return;
            }
            if (checkCollisionLeft()) {
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
            if (checkCollisionRight()) {
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
            if (checkCollisionDown()) {
                return;
            }
        }
        // update view properties
        updateY(helper);
    }

    protected void updateX(int[] newCoordinates) {
        for (int i = 0; i < 4; i++) {
            table[coordinatesY[i]][coordinatesX[i]].setBackgroundColor(Color.WHITE);
//            table[coordinatesY[i]][newCoordinates[i]].setBackgroundColor(Color.RED);
        }
        for (int i = 0; i < 4; i++) {
//            table[coordinatesY[i]][coordinatesX[i]].setBackgroundColor(Color.WHITE);
            table[coordinatesY[i]][newCoordinates[i]].setBackgroundColor(Color.RED);
        }
        coordinatesX = newCoordinates;
    }

    protected void updateY(int[] newCoordinates) {
        for (int i = 0; i < 4; i++) {
            table[coordinatesY[i]][coordinatesX[i]].setBackgroundColor(Color.WHITE);
//            table[newCoordinates[i]][coordinatesX[i]].setBackgroundColor(Color.RED);
        }
        for (int i = 0; i < 4; i++) {
//            table[coordinatesY[i]][coordinatesX[i]].setBackgroundColor(Color.WHITE);
            table[newCoordinates[i]][coordinatesX[i]].setBackgroundColor(Color.RED);
        }
        coordinatesY = newCoordinates;
    }

}
