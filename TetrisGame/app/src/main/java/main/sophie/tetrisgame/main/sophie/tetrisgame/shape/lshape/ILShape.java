package main.sophie.tetrisgame.main.sophie.tetrisgame.shape.lshape;

import android.widget.TableLayout;

import java.util.List;

/**
 * Created by Laptop on 26.4.2017 г..
 */

interface ILShape {
    List<int[]> rotateToNext(TableLayout[][] table, int[] coordinatesX, int[] coordinatesY);
    boolean checkLeft(TableLayout[][] table, int[] coordinatesX, int[] coordinatesY);
    boolean checkRight(TableLayout[][] table, int[] coordinatesX, int[] coordinatesY);
    boolean checkDown(TableLayout[][] table, int[] coordinatesX, int[] coordinatesY);
}
