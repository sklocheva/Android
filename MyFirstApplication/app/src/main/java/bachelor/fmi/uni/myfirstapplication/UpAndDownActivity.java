package bachelor.fmi.uni.myfirstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UpAndDownActivity extends AppCompatActivity {

    Button upButton;
    Button downButton;
    TextView numberTextView;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_and_down);

        upButton = (Button) findViewById(R.id.upButton);
        downButton = (Button) findViewById(R.id.downButton);
        numberTextView = (TextView) findViewById(R.id.numberTextView);
        upButton.setOnClickListener(onButtonClick);
        downButton.setOnClickListener(onButtonClick);
    }

    View.OnClickListener onButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.upButton:
                {
                    counter++;
                }break;
                case R.id.downButton:
                {
                    --counter;
                }break;
            }

            numberTextView.setText("Number: " + counter);
        }
    };
}
