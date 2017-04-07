package bachelor.fmi.uni.myfirstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GuessTheNumberActivity extends AppCompatActivity {

    TextView directionTextView;
    TextView triesTextView;
    TextView theNumberTextView;
    TextView successTextView;
    EditText guessedNumberEditText;
    Button okButton;
    Button resetButton;

    Random randomNumber = new Random();
    int numberToGuess;
    int tries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_number);

        directionTextView = (TextView)
                findViewById(R.id.directionTextView);

        triesTextView = (TextView) findViewById(R.id.triesTextView);

        theNumberTextView = (TextView)
                findViewById(R.id.hiddenNumberTextView);
        successTextView = (TextView)
                findViewById(R.id.successTextView);
        guessedNumberEditText = (EditText)
                findViewById(R.id.guessedNumberEditText);
        okButton = (Button) findViewById(R.id.guessNumberButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        okButton.setOnClickListener(onButtonClick);
        resetButton.setOnClickListener(onButtonClick);
        resetGame();
    }

    private void resetGame(){
        theNumberTextView.setText("Числото е : X");
        triesTextView.setText("Опити: 0");
        successTextView.setVisibility(View.INVISIBLE);
        directionTextView.setText("");
        numberToGuess = randomNumber.nextInt(100);
        tries = 0;
    }

    View.OnClickListener onButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.resetButton:
                {
                    resetGame();
                }break;

                case R.id.guessNumberButton:
                {
                    String stringNumber = guessedNumberEditText.
                            getText().toString();

                    if(!stringNumber.isEmpty())
                    {
                        int theNumber = Integer.parseInt(stringNumber);

                        if(theNumber > numberToGuess)
                        {
                            directionTextView.setText("↓");
                        }else if(theNumber < numberToGuess)
                        {
                            directionTextView.setText("↑");
                        }else
                        {
                            successTextView.setVisibility(View.VISIBLE);
                            theNumberTextView.setText("Числото е: " + numberToGuess);
                            directionTextView.setText("=");
                        }

                        triesTextView.setText("Опити: " + ++tries);
                    }


                }break;
            }
        }
    };
}
