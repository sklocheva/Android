package main.sophie.tetrisgame.main.sophie.tetrisgame.shape;

import android.widget.TableLayout;

import main.sophie.tetrisgame.MainActivity;

/**
 * Created by Sophie on 11.4.2017 г..
 */

public class ShapeCreator {

    private TableLayout[][] table;

    public ShapeCreator(TableLayout[][] table) {
        this.table = table;
    }

    public static AbstractShape createShape(MainActivity.TileShape tileShape) {
        return null;
    }
}
