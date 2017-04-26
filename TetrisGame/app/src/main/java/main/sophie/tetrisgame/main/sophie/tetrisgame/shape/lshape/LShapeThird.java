package main.sophie.tetrisgame.main.sophie.tetrisgame.shape.lshape;

import android.graphics.drawable.ColorDrawable;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.AbstractShape;

import static main.sophie.tetrisgame.main.sophie.tetrisgame.shape.AbstractShape.ID_BACKGROUND;

/**
 * Created by Laptop on 26.4.2017 г..
 */

class LShapeThird implements ILShape {


    private boolean checkCollisionRotate(TableLayout[][] table, int[] coordinatesX, int[] coordinatesY) {
        int id = ((ColorDrawable) table[coordinatesY[1] + 1][coordinatesX[1]].getBackground()).getColor();
        int id2 = ((ColorDrawable) table[coordinatesY[1] - 1][coordinatesX[1]].getBackground()).getColor();
        int id3 = ((ColorDrawable) table[coordinatesY[2] + 1][coordinatesX[2]].getBackground()).getColor();
        if ((id != ID_BACKGROUND || id2 != ID_BACKGROUND) || id3 != ID_BACKGROUND) {
            return true;
        }
        return false;
    }

    @Override
    public List<int[]> rotateToNext(TableLayout[][] table, int[] coordinatesX, int[] coordinatesY) {
        //rotate from second to third
        if (checkCollisionRotate(table, coordinatesX, coordinatesY)) {
            return null;
        }
        int x;
        int y;
        final int[] helperX = new int[4];
        final int[] helperY = new int[4];

        y = 1;
        x = -1;
        for (int i = 0; i < 3; i++) {
            helperX[i] = coordinatesX[i] + x;
            helperY[i] = coordinatesY[i] + y;
            y--;
            x++;
        }
        helperX[3] = coordinatesX[3];
        helperY[3] = coordinatesY[3] - 2;
        return new ArrayList<int[]>() {{
            add(helperX);
            add(helperY);
        }};
    }

    @Override
    public boolean checkLeft(TableLayout[][] table, int[] coordinatesX, int[] coordinatesY) {
        int id = ((ColorDrawable) table[coordinatesY[3]][coordinatesX[3] - 1].getBackground()).getColor();
        if (id != ID_BACKGROUND) {
            return true;
        }
        for (int i = 0; i < 2; i++) {
            int id2 = ((ColorDrawable) table[coordinatesY[i]][coordinatesX[i] - 1].getBackground()).getColor();
            if (id2 != ID_BACKGROUND) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkRight(TableLayout[][] table, int[] coordinatesX, int[] coordinatesY) {
        for (int i = 0; i < 3; i++) {
            int id = ((ColorDrawable) table[coordinatesY[i]][coordinatesX[i] + 1].getBackground()).getColor();
            if (id != ID_BACKGROUND) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDown(TableLayout[][] table, int[] coordinatesX, int[] coordinatesY) {
        int id = ((ColorDrawable) table[coordinatesY[0] + 1][coordinatesX[0]].getBackground()).getColor();
        int id2 = ((ColorDrawable) table[coordinatesY[3] + 1][coordinatesX[3]].getBackground()).getColor();
        if (id != ID_BACKGROUND || id2 != ID_BACKGROUND) {
            return true;
        }
        return false;
    }
}
