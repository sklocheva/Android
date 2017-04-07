package bachelor.fmi.uni.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button helloButton;
    Button upAndDownButton;
    Button guessTheNumberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        helloButton = (Button) findViewById(R.id.helloButton);
        upAndDownButton = (Button) findViewById(R.id.upDownButton);
        guessTheNumberButton = (Button) findViewById(R.id.guessButton);

        helloButton.setOnClickListener(onButtonClick);
        upAndDownButton.setOnClickListener(onButtonClick);
        guessTheNumberButton.setOnClickListener(onButtonClick);
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
        if (id == R.id.exit) {
            finish();
            return true;
        }else if(id == R.id.red)
        {

        }

        return super.onOptionsItemSelected(item);
    }
}
