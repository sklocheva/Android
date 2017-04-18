package main.sophie.tetrisgame.main.sophie.tetrisgame.shape;

import android.graphics.Color;

import main.sophie.tetrisgame.MainActivity;

/**
 * Created by Laptop on 11.4.2017 г..
 */

public class LineShape extends AbstractShape {
    public LineShape(int[][] table, MainActivity activity) {
        super(table, activity);
    }

    @Override
    public void rotate() {
        int x;
        int y;
        int[] helperX = new int[4];
        int[] helperY = new int[4];

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
    }

    public void draw() {
        //select middle and draw 2 cubes on both sides
        isRotated = false;
        int middle = MainActivity.W;
        if (middle % 2 != 0) {
            middle = middle + 1;
        }
        int counterC = 0;
        for (int i = middle - 2; i < middle + 2; i++) {
            activity.findViewById(table[i][0]).setBackgroundColor(Color.RED);

            coordinatesX[counterC] = i;
            coordinatesY[counterC] = 0;
            counterC++;
        }
    }

}
