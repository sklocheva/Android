package main.sophie.tetrisgame.main.sophie.tetrisgame.shape;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TableLayout;
import android.widget.TableRow;

import main.sophie.tetrisgame.MainActivity;

import static android.R.attr.id;

/**
 * Created by Laptop on 18.4.2017 г..
 */

public class CubeShape extends AbstractShape {

    public CubeShape(TableLayout table[][]) {
        super(table);
    }

    @Override
    public void rotate() {
        //cube has no rotation
        return;
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

        for (int i = middle - 1; i < middle + 1; i++) {
            table[0][i].setBackgroundColor(Color.RED);
            coordinatesY[counterC] = 0;
            coordinatesX[counterC] = i;
            counterC++;

            table[1][i].setBackgroundColor(Color.RED);
            coordinatesY[counterC] = 1;
            coordinatesX[counterC] = i;
            counterC++;
        }
    }

    @Override
    boolean checkCollisionLeft() {
        for (int i = 0; i < 2; i++) {
            int id = ((ColorDrawable) table[i][coordinatesX[i] - 1].getBackground()).getColor();
            if (id != Color.WHITE) {
                return true;
            }
        }
        return false;
    }

    @Override
    boolean checkCollisionRight() {
        for (int i = 2; i < 4; i++) {
            int id = ((ColorDrawable) table[i][coordinatesX[i] + 1].getBackground()).getColor();
            if (id != Color.WHITE) {
                return true;
            }
        }
        return false;
    }

    @Override
    boolean checkCollisionDown() {
        int id = ((ColorDrawable) table[coordinatesY[1] + 1][coordinatesX[1]].getBackground()).getColor();
        if (id != Color.WHITE) {
            return true;
        }
        int id2 = ((ColorDrawable) table[coordinatesY[3] + 1][coordinatesX[3]].getBackground()).getColor();
        if (id2 != Color.WHITE) {
            return true;
        }
        return false;
    }
}
