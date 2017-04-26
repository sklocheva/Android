package main.sophie.tetrisgame.main.sophie.tetrisgame.shape;

import android.graphics.drawable.ColorDrawable;
import android.widget.TableLayout;

import main.sophie.tetrisgame.MainActivity;

/**
 * Created by Laptop on 11.4.2017 г..
 */

public class LineShape extends AbstractShape {
    public LineShape(TableLayout[][] table) {
        super(table);
    }

    /**
     * Rotates the line vertical, if there is enough Y space.
     * If it is already roteted - rotates it back to horizontal.
     */
    @Override
    public void rotate() {
        //early kill
        for (int x : coordinatesY) {
            if (x < 2) {
                return;
            }
        }
        if (checkCollisionRotate()) {
            return;
        }
        int x;
        int y;
        int[] helperX = new int[4];
        int[] helperY = new int[4];

        //if roteted - rotate back
        if (isRotated) {
            isRotated = false;
            //rotate back
            x = -2;
            y = 2;
            for (int i = 0; i < 4; i++) {
                helperX[i] = coordinatesX[i] + x;
                helperY[i] = coordinatesY[i] + y;
                x++;
                y--;
            }
        } else {
            isRotated = true;
            //rotate
            x = 2;
            y = -2;
            for (int i = 0; i < 4; i++) {
                helperX[i] = coordinatesX[i] + x;
                helperY[i] = coordinatesY[i] + y;
                x--;
                y++;
            }
        }
        updateX(helperX);
        updateY(helperY);
    }

    /**
     * draws the shape of a line
     */
    @Override
    public void draw() {
        //select middle and draw 2 cubes on both sides
        isRotated = false;
        int middle = MainActivity.W;
        if (middle % 2 != 0) {
            middle = middle + 1;
        }
        middle = middle / 2;
        int counterC = 0;
        for (int i = middle - 2; i < middle + 2; i++) {
            table[0][i].setBackgroundColor(ID_SHAPE);
            coordinatesY[counterC] = 0;
            coordinatesX[counterC] = i;
            counterC++;
        }
    }

    private boolean checkCollisionRotate() {
        if (isRotated) {
            for (int i = 1; i < 3; i++) {
                int id = ((ColorDrawable) table[coordinatesY[2]][coordinatesX[2] - i].getBackground()).getColor();
                if (checkDiffIdColor(id)) {
                    return true;
                }
            }
            int id = ((ColorDrawable) table[coordinatesY[2]][coordinatesX[2] + 1].getBackground()).getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }
        } else {
            int id = ((ColorDrawable) table[coordinatesY[2] + 1][coordinatesX[2]].getBackground()).getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    boolean checkCollisionLeft() {
        if (isRotated) {
            for (int y : coordinatesY) {
                int id = ((ColorDrawable)
                        table[y][coordinatesX[0] - 1]
                                .getBackground())
                                    .getColor();
                if (checkDiffIdColor(id)) {
                    return true;
                }
            }
            return false;
        } else {
            int id = ((ColorDrawable)
                    table[coordinatesY[0]][coordinatesX[0] - 1]
                            .getBackground())
                                .getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }
            return false;
        }
    }

    @Override
    boolean checkCollisionRight() {
        if (isRotated) {
            for (int y : coordinatesY) {
                int id = ((ColorDrawable) table[y][coordinatesX[0] + 1].getBackground()).getColor();
                if (checkDiffIdColor(id)) {
                    return true;
                }
            }
            return false;
        } else {
            int id = ((ColorDrawable) table[coordinatesY[0]][coordinatesX[coordinatesX.length - 1] + 1].getBackground()).getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }
            return false;
        }
    }

    @Override
    boolean checkCollisionDown() {
        if (isRotated) {
            int id = ((ColorDrawable) table[coordinatesY[coordinatesY.length - 1] + 1][coordinatesX[0]].getBackground()).getColor();
            if (checkDiffIdColor(id)) {
                return true;
            }
            return false;
        } else {
            for (int x : coordinatesX) {
                int id = ((ColorDrawable) table[coordinatesY[0] + 1][x].getBackground()).getColor();
                if (checkDiffIdColor(id)) {
                    return true;
                }
            }
            return false;
        }
    }

}
