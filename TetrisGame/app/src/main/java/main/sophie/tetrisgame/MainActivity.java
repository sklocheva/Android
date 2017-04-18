package main.sophie.tetrisgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.LineShape;

public class MainActivity extends AppCompatActivity {
    public enum TileShape {
        LineShape, TShape, SquareShape, LShape, ZShape
    }

    public static final int H = 10;
    public static final int W = 10;
    public static final String nameFormat = "row_%d_%d";
//    private final int WEIGTH = 1;
//    public HashMap<String, TableRow> matrixMap;

    TableLayout gameLayout;
    TableRow.LayoutParams param;
    ImageButton leftButton;
    ImageButton rightButton;
    ImageButton rotateButton;

    //contains coordinates and id number of the view
    int[][] table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //add score and level
        leftButton = (ImageButton) findViewById(R.id.leftButton);
        rightButton = (ImageButton) findViewById(R.id.rightButton);
        rotateButton = (ImageButton) findViewById(R.id.rotateButton);

        populateTable();

        LineShape s = new LineShape(table, this);
        s.draw();

//        findViewById(table[5][5]).setBackgroundColor(Color.RED);

    }

    private void populateTable() {

        table = new int[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                String name = String.format(nameFormat, i, j);
                table[i][j] = getResources().getIdentifier(name, "id", getPackageName());
//                findViewById(table[i][j]).setBackgroundColor(Color.RED);
            }
        }
    }
}
