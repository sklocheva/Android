package main.sophie.tetrisgame.main.sophie.tetrisgame.shape;

import android.graphics.Color;
import android.widget.TableLayout;

import main.sophie.tetrisgame.MainActivity;

/**
 * Created by Laptop on 18.4.2017 г..
 */

public class LShape extends AbstractShape {

    public LShape(TableLayout table[][]) {
        super(table);
    }

    @Override
    public void rotate() {

    }

    @Override
    public void draw() {
        isRotated = false;
        int middle = MainActivity.W;
        if (middle % 2 != 0) {
            middle = middle + 1;
        }
        middle = middle / 2;
        int counterC = 0;
        for (int i = 0; i < 3; i++) {
            table[i][middle - 1].setBackgroundColor(Color.RED);
            coordinatesY[counterC] = i;
            coordinatesX[counterC] = middle - 1;
            counterC++;
        }
        table[2][middle].setBackgroundColor(Color.RED);
        coordinatesY[counterC] = 2;
        coordinatesX[counterC] = middle;
        counterC++;
    }

    @Override
    boolean checkCollisionLeft() {
        return false;
    }

    @Override
    boolean checkCollisionRight() {
        return false;
    }

    @Override
    boolean checkCollisionDown() {
        return false;
    }
}
