package main.sophie.tetrisgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.AbstractShape;
import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.ShapeCreator;
import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.TShape;

public class MainActivity extends AppCompatActivity {

    public static final int H = 20;
    public static final int W = 20;

    ImageButton leftButton;
    ImageButton rightButton;
    ImageButton rotateButton;
    TableLayout matrixLayout;

    //contains coordinates and id number of the view
    TableLayout[][] table;

    private ShapeCreator creator;
    private AbstractShape shape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add score and level
        leftButton = (ImageButton) findViewById(R.id.leftButton);
        rightButton = (ImageButton) findViewById(R.id.rightButton);
        rotateButton = (ImageButton) findViewById(R.id.rotateButton);
        matrixLayout = (TableLayout) findViewById(R.id.game_matrix);

        leftButton.setOnClickListener(leftClick);
        rightButton.setOnClickListener(rightClick);
        rotateButton.setOnClickListener(rotateClick);
        rotateButton.setOnLongClickListener(downClick);

        populateTable();
        shape = creator.createShape();
    }

    View.OnClickListener leftClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            shape.left();
        }
    };
    View.OnClickListener rightClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            shape.right();
        }
    };
    View.OnClickListener rotateClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            shape.rotate();
        }
    };
    View.OnLongClickListener downClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            if (shape.down()) {
                return true;
            }
            return false;
        }
    };

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
                l.setBackgroundColor(AbstractShape.ID_BACKGROUND);
                table[i][j] = l;
                row.addView(l);
            }
            matrixLayout.addView(row);
        }

        creator = new ShapeCreator(table);
    }
}
