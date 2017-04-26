package main.sophie.tetrisgame.main.sophie.tetrisgame.shape;

import android.graphics.drawable.ColorDrawable;
import android.widget.TableLayout;

import main.sophie.tetrisgame.MainActivity;

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
            table[0][i].setBackgroundColor(ID_SHAPE);
            coordinatesY[counterC] = 0;
            coordinatesX[counterC] = i;
            counterC++;

            table[1][i].setBackgroundColor(ID_SHAPE);
            coordinatesY[counterC] = 1;
            coordinatesX[counterC] = i;
            counterC++;
        }
    }

    @Override
    protected boolean checkCollisionLeft() {
        for (int i = 0; i < 2; i++) {
            if (checkDiffIdColor(((ColorDrawable) table[i][coordinatesX[0] - 1].getBackground()).getColor())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean checkCollisionRight() {
        for (int i = 2; i < 4; i++) {
            int id = ((ColorDrawable) table[i][coordinatesX[2] + 1].getBackground()).getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean checkCollisionDown() {
        int id = ((ColorDrawable) table[coordinatesY[1] + 1][coordinatesX[1]].getBackground()).getColor();
        if (checkDiffIdColor(id)) {
            return true;
        }
        int id2 = ((ColorDrawable) table[coordinatesY[3] + 1][coordinatesX[3]].getBackground()).getColor();
        if (checkDiffIdColor(id2)) {
            return true;
        }
        return false;
    }
}
