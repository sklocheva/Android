package main.sophie.tetrisgame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import main.sophie.tetrisgame.main.sophie.tetrisgame.shape.AbstractShape;

public class MainActivity extends AppCompatActivity {

    public static final int H = 20;
    public static final int W = 15;

    private final int DISPLAY_LENGTH = 1000;
    LinearLayout mainBackground;
    ImageButton leftButton;
    ImageButton rightButton;
    ImageButton rotateButton;
    ImageButton pauseButton;
    TableLayout matrixLayout;

    public static TextView score;
    TextView level;

    SharedPreferences pref;

    //contains coordinates and id number of the view
    GameThread thread;
    TableLayout[][] table;
    AlertDialog.Builder dlgAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainBackground = (LinearLayout) findViewById(R.id.main_background);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        thread.isPaused = true;
        switch (id) {
            case R.id.about:
                new AlertDialog.Builder(this)
                        .setTitle("About App")
                        .setMessage("This is a university project app. Creator: Sophia Klocheva 1401682030")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                thread.isPaused = false;
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;

            case R.id.exit:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private TableLayout[][] populateTable() {

        TableLayout[][] table = new TableLayout[H][W];
        matrixLayout.setWeightSum(H);
        TableLayout.LayoutParams matrixRowP =
                new TableLayout.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, 0, 1f);
        TableRow.LayoutParams rowElP =
                new TableRow.LayoutParams(
                        0, TableRow.LayoutParams.MATCH_PARENT, 1f);
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
