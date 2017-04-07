package bachelor.fmi.uni.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    Button helloButton;
    Button upAndDownButton;
    Button guessTheNumberButton;
    Button shoppingListActivity;
    LinearLayout menuBackground;
    SharedPreferences pref;

    static final String BACKGROUND_COLOR = "backgroundColor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        pref = getPreferences(Context.MODE_PRIVATE);

        shoppingListActivity = (Button) findViewById(R.id.shoppingListButton);
        helloButton = (Button) findViewById(R.id.helloButton);
        upAndDownButton = (Button) findViewById(R.id.upDownButton);
        guessTheNumberButton = (Button) findViewById(R.id.guessButton);
        menuBackground = (LinearLayout) findViewById
                (R.id.menuBackground);

        helloButton.setOnClickListener(onButtonClick);
        upAndDownButton.setOnClickListener(onButtonClick);
        guessTheNumberButton.setOnClickListener(onButtonClick);
        shoppingListActivity.setOnClickListener(onButtonClick);

        menuBackground.setBackgroundColor(pref.getInt
                (BACKGROUND_COLOR, Color.WHITE));
    }

    View.OnClickListener onButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();

            switch (v.getId())
            {
                case R.id.helloButton:
                {
                    intent = new Intent(MenuActivity.this,
                            MainActivity.class);
                }break;
                case R.id.upDownButton:
                {
                    intent = new Intent(MenuActivity.this,
                            UpAndDownActivity.class);
                }break;
                case R.id.guessButton:
                {
                    intent = new Intent(MenuActivity.this,
                            GuessTheNumberActivity.class);
                }break;
                case R.id.shoppingListButton:
                {
                    intent = new Intent(MenuActivity.this,
                            ShoppingListActivity.class);

                    intent.putExtra("color", pref.getInt
                            (BACKGROUND_COLOR, Color.WHITE));
                }
            }


            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        SharedPreferences.Editor editor = pref.edit();

        if (id == R.id.exit) {
            finish();
            return true;
        }else if(id == R.id.red)
        {
            menuBackground.setBackgroundColor(Color.RED);
            editor.putInt(BACKGROUND_COLOR, Color.RED);
        }else if(id == R.id.green)
        {
            menuBackground.setBackgroundColor(Color.GREEN);
            editor.putInt(BACKGROUND_COLOR, Color.GREEN);
        }else if(id == R.id.pink)
        {
            menuBackground.setBackgroundColor(Color.argb(255,255,102,178));
            editor.putInt(BACKGROUND_COLOR, Color.argb(255,255,102,178));
        }

        editor.commit();

        return super.onOptionsItemSelected(item);
    }
}
