package main.sophie.tetrisgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.AbstractShape;

public class MainActivity extends AppCompatActivity {

    public static final int H = 20;
    public static final int W = 15;

    private final int DISPLAY_LENGTH = 1000;
    ImageButton leftButton;
    ImageButton rightButton;
    ImageButton rotateButton;
    ImageButton pauseButton;
    TableLayout matrixLayout;

    public static TextView score;
    TextView level;

    //contains coordinates and id number of the view
    GameThread thread;
    TableLayout[][] table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add score and level
        leftButton = (ImageButton) findViewById(R.id.leftButton);
        rightButton = (ImageButton) findViewById(R.id.rightButton);
        rotateButton = (ImageButton) findViewById(R.id.rotateButton);
        pauseButton = (ImageButton) findViewById(R.id.pause_button);
        matrixLayout = (TableLayout) findViewById(R.id.game_matrix);

        score = (TextView) findViewById(R.id.score);
        level = (TextView) findViewById(R.id.level);

        leftButton.setOnClickListener(leftClick);
        rightButton.setOnClickListener(rightClick);
        rotateButton.setOnClickListener(rotateClick);
        pauseButton.setOnClickListener(pauseClick);
        matrixLayout.setClickable(true);
        matrixLayout.setOnClickListener(downClick);
        GameThread thread = new GameThread(this, populateTable());
        thread.run();
    }

    View.OnClickListener leftClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            thread.shape.left();
        }
    };
    View.OnClickListener rightClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            thread.shape.right();
        }
    };
    View.OnClickListener rotateClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            thread.shape.rotate();
        }
    };
    View.OnClickListener downClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            thread.shape.down();
        }
    };
    View.OnClickListener pauseClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (thread.isPaused) {
                thread.isPaused = false;
            } else {
                thread.isPaused = true;
            }
        }
    };

    private TableLayout[][] populateTable() {

        TableLayout[][] table = new TableLayout[H][W];
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
        return table;
    }


}
