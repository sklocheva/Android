package main.sophie.tetrisgame.main.sophie.tetrisgame.shape;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Laptop on 20.4.2017 г..
 */

enum FigureNames {
    CUBE_SHAPE, FOUR_SHAPE, LINE_SHAPE, L_SHAPE, T_SHAPE;

    private static final List<FigureNames> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static FigureNames randomLetter() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
