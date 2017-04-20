package main.sophie.tetrisgame.main.sophie.tetrisgame.shape;

import android.graphics.drawable.ColorDrawable;
import android.widget.TableLayout;

import main.sophie.tetrisgame.MainActivity;

/**
 * Created by Laptop on 18.4.2017 г..
 */

public class FourShape extends AbstractShape {

    public FourShape(TableLayout table[][]) {
        super(table);
    }

    @Override
    public void rotate() {
        //rotate a 4 shape
        //early kill
        if (coordinatesY[3] + 1 >= MainActivity.W || checkCollisionRotate()) {
            return;
        }
        int x;
        int y;
        int[] helperX = new int[4];
        int[] helperY = new int[4];

        //if rotated - rotate back
        if (isRotated) {
            isRotated = false;
            //rotate back
            y = -2;
            for (int i = 0; i < 4; i++) {
                if (i % 2 == 0) {
                    x = 0;
                } else {
                    x = -1;
                }
                helperX[i] = coordinatesX[i] + x;
                helperY[i] = coordinatesY[i] + y;
                y++;
            }
        } else {
            isRotated = true;
            //rotate
            y = 1;
            for (int i = 0; i < 4; i++) {
                if (i % 2 == 0) {
                    x = 0;
                } else {
                    x = 1;
                }
                helperX[i] = coordinatesX[i] + x;
                helperY[i] = coordinatesY[i] + y;
                y--;
            }
        }

        updateX(helperX);

        updateY(helperY);

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
        int counterY = 0;
        for (int i = middle - 1; i < middle + 1; i++) {
            table[counterY][i].setBackgroundColor(ID_SHAPE);
            coordinatesY[counterC] = counterY;
            coordinatesX[counterC] = i;
            counterC++;
            counterY++;
            table[counterY][i].setBackgroundColor(ID_SHAPE);
            coordinatesY[counterC] = counterY;
            coordinatesX[counterC] = i;
            counterC++;
        }
    }

    private boolean checkCollisionRotate() {
        if (isRotated) {
            int id = ((ColorDrawable) table[coordinatesY[0] - 1][coordinatesX[0]].getBackground()).getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }
            int id2 = ((ColorDrawable) table[coordinatesY[1] + 1][coordinatesX[1]].getBackground()).getColor();
            if (checkDiffIdColor(id2)) {
                return true;
            }
        } else {
            int id = ((ColorDrawable) table[coordinatesY[2] - 1][coordinatesX[2]].getBackground()).getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }
            int id2 = ((ColorDrawable) table[coordinatesY[2] - 1][coordinatesX[2] + 1].getBackground()).getColor();
            if (checkDiffIdColor(id2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    boolean checkCollisionLeft() {
        if (isRotated) {
            int id = ((ColorDrawable) table[coordinatesY[0]][coordinatesX[0] - 1].getBackground()).getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }
            int id2 = ((ColorDrawable) table[coordinatesY[2]][coordinatesX[2] - 1].getBackground()).getColor();
            if (checkDiffIdColor(id2)) {
                return true;
            }
        } else {
            for (int i = 0; i < 2; i++) {
                int id = ((ColorDrawable) table[coordinatesY[i]][coordinatesX[0] - 1].getBackground()).getColor();
                if (checkDiffIdColor(id)) {
                    return true;
                }
            }
            int id = ((ColorDrawable) table[coordinatesY[3]][coordinatesX[3] - 1].getBackground()).getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    boolean checkCollisionRight() {
        if (isRotated) {
            int id = ((ColorDrawable) table[coordinatesY[3]][coordinatesX[3] + 1].getBackground()).getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }
            int id2 = ((ColorDrawable) table[coordinatesY[1]][coordinatesX[1] + 1].getBackground()).getColor();
            if (checkDiffIdColor(id2)) {
                return true;
            }
        } else {
            for (int i = 2; i < 4; i++) {
                int id = ((ColorDrawable) table[coordinatesY[i]][coordinatesX[0] + 1].getBackground()).getColor();
                if (checkDiffIdColor(id)) {
                    return true;
                }
            }
            int id = ((ColorDrawable) table[coordinatesY[0]][coordinatesX[0] + 1].getBackground()).getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    boolean checkCollisionDown() {
        if (isRotated) {
            for (int i = 0; i < 2; i++) {
                int id = ((ColorDrawable) table[coordinatesY[0] + 1][coordinatesX[i]].getBackground()).getColor();
                if (checkDiffIdColor(id)) {
                    return true;
                }
            }
            int id = ((ColorDrawable) table[coordinatesY[3] + 1][coordinatesX[3]].getBackground()).getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }

        } else {
            int id = ((ColorDrawable) table[coordinatesY[1] + 1][coordinatesX[1]].getBackground()).getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }
            int id2 = ((ColorDrawable) table[coordinatesY[3] + 1][coordinatesX[3]].getBackground()).getColor();
            if (checkDiffIdColor(id2)) {
                return true;
            }
        }
        return false;
    }
}
