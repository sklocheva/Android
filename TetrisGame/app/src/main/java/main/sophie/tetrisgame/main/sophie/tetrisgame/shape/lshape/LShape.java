package main.sophie.tetrisgame.main.sophie.tetrisgame.shape.lshape;

import android.widget.TableLayout;

import java.util.List;

import main.sophie.tetrisgame.MainActivity;
import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.AbstractShape;

/**
 * Created by Laptop on 18.4.2017 г..
 */

public class LShape extends AbstractShape {
    protected enum Rotations {FIRST, SECOND, THIRD, FOURTH}

    protected Rotations current;
    protected ILShape currentShape;

    public LShape(TableLayout table[][]) {
        super(table);
        current = Rotations.FIRST;
        currentShape = new LShapeFirst();
    }

    @Override
    public void rotate() {
        List<int[]> listC = null;
        switch (current) {
            case FIRST:
                currentShape = new LShapeSecond();
                listC = currentShape.rotateToNext(table, coordinatesX, coordinatesY);
                current = Rotations.SECOND;
                break;
            case SECOND:
                currentShape = new LShapeThird();
                listC = currentShape.rotateToNext(table, coordinatesX, coordinatesY);
                current = Rotations.THIRD;
                break;
            case THIRD:
                currentShape = new LShapeFourth();
                listC = currentShape.rotateToNext(table, coordinatesX, coordinatesY);
                current = Rotations.FOURTH;
                break;
            case FOURTH:
                currentShape = new LShapeFirst();
                listC = currentShape.rotateToNext(table, coordinatesX, coordinatesY);
                current = Rotations.FIRST;
                break;
        }
        if (listC != null) {
            updateX(listC.get(0));
            updateY(listC.get(1));
        }
    }

    @Override
    public void draw() {
        int middle = MainActivity.W;
        if (middle % 2 != 0) {
            middle = middle + 1;
        }
        middle = middle / 2;
        int counterC = 0;
        for (int i = 0; i < 3; i++) {
            table[i][middle - 1].setBackgroundColor(ID_SHAPE);
            coordinatesY[counterC] = i;
            coordinatesX[counterC] = middle - 1;
            counterC++;
        }
        table[2][middle].setBackgroundColor(ID_SHAPE);
        coordinatesY[counterC] = 2;
        coordinatesX[counterC] = middle;
        counterC++;
    }

    @Override
    protected boolean checkCollisionLeft() {
        if (currentShape.checkLeft(table, coordinatesX, coordinatesY)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean checkCollisionRight() {
        if (currentShape.checkRight(table, coordinatesX, coordinatesY)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean checkCollisionDown() {
        if (currentShape.checkDown(table, coordinatesX, coordinatesY)) {
            return true;
        }
        return false;
    }
}
