package main.sophie.tetrisgame;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.AbstractShape;
import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.ShapeCreator;

public class MainActivity extends AppCompatActivity {

    public static final int H = 20;
    public static final int W = 20;

    private final int DISPLAY_LENGTH = 1000;
    ImageButton leftButton;
    ImageButton rightButton;
    ImageButton rotateButton;
    TableLayout matrixLayout;

    //contains coordinates and id number of the view
    TableLayout[][] table;

    private ShapeCreator creator;
    private AbstractShape shape;
    private boolean playing = true;

    private Thread gameThread = new Thread() {

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            shape = creator.createShape();

                        }
                    });
                    Thread figureThread = new Thread() {

                        @Override
                        public void run() {
                            try {
                                playing = true;
                                while (playing) {
                                    Thread.sleep(1000);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (!shape.down()) {
                                                playing = false;
                                            }
                                        }
                                    });
                                }
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();//preserve the message
                                return;//
                            }
                        }
                    };
                    figureThread.start();
                    try {
                        figureThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();//preserve the message
                return;//
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add score and level
        leftButton = (ImageButton) findViewById(R.id.leftButton);
        rightButton = (ImageButton) findViewById(R.id.rightButton);
        rotateButton = (ImageButton) findViewById(R.id.rotateButton);
        matrixLayout = (TableLayout) findViewById(R.id.game_matrix);

        TextView score = (TextView) findViewById(R.id.score);
        TextView level = (TextView) findViewById(R.id.level);

        leftButton.setOnClickListener(leftClick);
        rightButton.setOnClickListener(rightClick);
        rotateButton.setOnClickListener(rotateClick);
        rotateButton.setOnLongClickListener(downClick);

        populateTable();
        gameThread.start();

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
