package main.sophie.tetrisgame.main.sophie.tetrisgame.shape;

import android.widget.TableLayout;

import main.sophie.tetrisgame.MainActivity;

/**
 * Created by Laptop on 18.4.2017 г..
 */

public class TShape extends AbstractShape {

    public TShape(TableLayout table[][]) {
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
        for (int i = middle - 1; i < middle + 2; i++) {
            table[0][i].setBackgroundColor(ID_SHAPE);
            coordinatesY[counterC] = 0;
            coordinatesX[counterC] = i;
            counterC++;
        }
        table[1][middle].setBackgroundColor(ID_SHAPE);
        coordinatesY[counterC] = 1;
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
