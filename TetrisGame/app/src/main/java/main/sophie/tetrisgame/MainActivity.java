package main.sophie.tetrisgame;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.CubeShape;
import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.FourShape;
import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.LShape;
import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.LineShape;
import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.TShape;

public class MainActivity extends AppCompatActivity {
    public enum TileShape {
        LineShape, TShape, SquareShape, LShape, ZShape
    }

    public static final int H = 20;
    public static final int W = 20;
    public static final float H1 = 10f;
    public static final float W1 = 10f;
    public static final String nameFormat = "row_%d_%d";
//    private final int WEIGTH = 1;
//    public HashMap<String, TableRow> matrixMap;

    TableLayout gameLayout;
    TableRow.LayoutParams param;
    ImageButton leftButton;
    ImageButton rightButton;
    ImageButton rotateButton;
    TableLayout matrixLayout;

    //contains coordinates and id number of the view
    TableLayout[][] table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //add score and level
        leftButton = (ImageButton) findViewById(R.id.leftButton);
        rightButton = (ImageButton) findViewById(R.id.rightButton);
        rotateButton = (ImageButton) findViewById(R.id.rotateButton);

        matrixLayout = (TableLayout) findViewById(R.id.game_matrix);


        populateTable();

        TShape s = new TShape(table);
        s.draw();
        s.down();
        s.down();


    }

    private void populateTable() {

        table = new TableLayout[H][W];
        matrixLayout.setWeightSum(H);
        TableLayout.LayoutParams matrixRowP = new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 0, 1f);
        TableRow.LayoutParams rowElP = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
        rowElP.setMargins(1, 1, 1, 1);

        for (int i = 0; i < H; i++) {
            TableRow row = new TableRow(this);
            row.setWeightSum(W);
            row.setLayoutParams(matrixRowP);
            for (int j = 0; j < W; j++) {
                TableLayout l = new TableLayout(this);
                l.setLayoutParams(rowElP);
                l.setBackgroundColor(Color.WHITE);
                table[i][j] = l;
                row.addView(l);
            }
            matrixLayout.addView(row);
        }
    }
}
