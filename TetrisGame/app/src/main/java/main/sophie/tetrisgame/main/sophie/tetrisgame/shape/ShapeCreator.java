package main.sophie.tetrisgame.main.sophie.tetrisgame.shape;

import android.widget.TableLayout;

import main.sophie.tetrisgame.MainActivity;

/**
 * Created by Sophie on 11.4.2017 г..
 */

public class ShapeCreator {

    private TableLayout[][] table;
    private FigureNames name;
    private AbstractShape shape;

    public ShapeCreator(TableLayout[][] table) {
        this.table = table;
    }

    /**
     * Generates a random shape and draws is in the matrix.
     *
     * @return a random shape object
     */
    public AbstractShape createShape() {
        name = name.randomLetter();
        switch (name) {
            case CUBE_SHAPE:
                shape = new CubeShape(table);
                break;
            case FOUR_SHAPE:
                shape = new FourShape(table);
                break;
            case LINE_SHAPE:
                shape = new LineShape(table);
                break;
            case L_SHAPE:
                shape = new LShape(table);
                break;
            case T_SHAPE:
                shape = new TShape(table);
                break;
        }
        shape.draw();
        return shape;
    }
}
