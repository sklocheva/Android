package main.sophie.tetrisgame.main.sophie.tetrisgame.shape;

import android.graphics.Color;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static final String MATRIX_BACKGROUND = "#66FFFFFF";
    public static final int ID_BACKGROUND = Color.parseColor(MATRIX_BACKGROUND);
    public static int ID_SHAPE;

    private Random randomGenerator;
    //list of available colors for the shapes
    private static final List<Integer> COLORS = new ArrayList<Integer>() {{
        add(Color.RED);
        add(Color.GREEN);
        add(Color.CYAN);
        add(Color.BLUE);
        add(Color.WHITE);
        add(Color.YELLOW);
    }};

    public AbstractShape(TableLayout[][] table) {
        this.table = table;
        this.coordinatesX = new int[4];
        this.coordinatesY = new int[4];
        randomGenerator = new Random();
        ID_SHAPE = COLORS.get(randomGenerator.nextInt(COLORS.size()));
    }

    /**
     * Checks for collisions and then rotates the shape.
     */
    public abstract void rotate();

    //make internal after tests

    /**
     * Draws the shape in the upper middle of the matrix.
     * Can be used to draw the figure in the ShapeCreator.
     */
    abstract void draw();

    /**
     * Checks for collisions on the left.
     *
     * @return true if there are collisions
     */
    abstract boolean checkCollisionLeft();

    /**
     * Checks for collisions on the right.
     *
     * @return true if there are collisions
     */
    abstract boolean checkCollisionRight();

    /**
     * Checks for collisions on going down.
     *
     * @return true if there are collisions
     */
    abstract boolean checkCollisionDown();

    /**
     * Checks for collisions and then goes one cube to the left.
     */
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

    /**
     * Checks for collisions and then goes one cube to the right.
     */
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

    /**
     * Checks for collisions and then goes one cube down.
     */
    public boolean down() {
        //get current positions
        //check if you can add 1 to X - else restart thread
        // add 1 to Y
        // update view properties
        int[] helper = new int[4];
        //check if you can add -1 to X - else return
        for (int i = 0; i < coordinatesY.length; i++) {
            helper[i] = coordinatesY[i] + 1;
            if (helper[i] >= MainActivity.H) {
                return false;
            }
            if (checkCollisionDown()) {
                return false;
            }
        }
        // update view properties
        updateY(helper);
        return true;
    }

    /**
     * Updates the new coordinates X of the shape
     *
     * @param newCoordinates
     */
    protected void updateX(int[] newCoordinates) {
        for (int i = 0; i < 4; i++) {
            table[coordinatesY[i]][coordinatesX[i]].setBackgroundColor(ID_BACKGROUND);
        }
        for (int i = 0; i < 4; i++) {
            table[coordinatesY[i]][newCoordinates[i]].setBackgroundColor(ID_SHAPE);
        }
        coordinatesX = newCoordinates;
    }

    /**
     * Updates the new coordinates Y of the shape
     *
     * @param newCoordinates
     */
    protected void updateY(int[] newCoordinates) {
        for (int i = 0; i < 4; i++) {
            table[coordinatesY[i]][coordinatesX[i]].setBackgroundColor(ID_BACKGROUND);
        }
        for (int i = 0; i < 4; i++) {
            table[newCoordinates[i]][coordinatesX[i]].setBackgroundColor(ID_SHAPE);
        }
        coordinatesY = newCoordinates;
    }

    /**
     * Checks if the color is different from the background, meaning there is a figure on that place.
     *
     * @param id color of the cube we need to check
     * @return true if it's different color than the background
     */
    protected boolean checkDiffIdColor(int id) {
        if (id != ID_BACKGROUND) {
            return true;
        }
        return false;
    }

}
