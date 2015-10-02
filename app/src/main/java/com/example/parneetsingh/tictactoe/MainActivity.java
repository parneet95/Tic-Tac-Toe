package me.parneetsingh.tictactoe;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button a1,a2,a3,b1,b2,b3,c1,c2,c3,bNewGame;
    Button[] barray;

    boolean turn = true;      // X = True ; 0 = False
    int turn_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a1 = (Button) findViewById(R.id.A1);
        a2 = (Button) findViewById(R.id.A2);
        a3 = (Button) findViewById(R.id.A3);
        b1 = (Button) findViewById(R.id.B1);
        b2 = (Button) findViewById(R.id.B2);
        b3 = (Button) findViewById(R.id.B3);
        c1 = (Button) findViewById(R.id.C1);
        c2 = (Button) findViewById(R.id.C2);
        c3 = (Button) findViewById(R.id.C3);

        bNewGame = (Button) findViewById(R.id.bNewGame);


        barray = new Button[]{a1,a2,a3,b1,b2,b3,c1,c2,c3};

        for(Button b: barray) {

            b.setOnClickListener(this);
        }

        bNewGame.setOnClickListener(new OnClickListener() {  //anonymous inner class concept
            @Override
            public void onClick(View v) {

                turn = true;
                turn_count = 0;
                enableDisableAllButtons(true);
            }
        });

    }

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;            // Casting is possible since View is a superclass of Button
        buttonClicked(b);
    }

    public void buttonClicked(Button b) {

        if(turn){
            b.setText("X");
        }
        else {
            b.setText("O");
        }

        turn_count++;

        // b.setBackgroundColor(Color.LTGRAY); // Changes color on click
        b.setClickable(false);   // Avoids changing already entered value
        turn = !turn;            // Change turn

        checkForWinner();
    }

    private void checkForWinner() {

        boolean there_is_a_winner = false;

        // Horizontal
        if (a1.getText() == a2.getText() && a2.getText() == a3.getText() && !a1.isClickable()){

            there_is_a_winner = true;
        }
        else if (b1.getText() == b2.getText() && b2.getText() == b3.getText() && !b1.isClickable())
            there_is_a_winner = true;
        else if (c1.getText() == c2.getText() && c2.getText() == c3.getText() && !c1.isClickable())
            there_is_a_winner = true;

        //Vertical
        if (a1.getText() == b1.getText() && b1.getText() == c1.getText() && !a1.isClickable())
            there_is_a_winner = true;
        else if (a2.getText() == b2.getText() && b2.getText() == c2.getText() && !b2.isClickable())
            there_is_a_winner = true;
        else if (a3.getText() == b3.getText() && b3.getText() == c3.getText() && !c3.isClickable())
            there_is_a_winner = true;


        //Diagonal
        if (a1.getText() == b2.getText() && b2.getText() == c3.getText() && !a1.isClickable())
            there_is_a_winner = true;
        else if (a3.getText() == b2.getText() && b2.getText() == c1.getText() && !b2.isClickable())
            there_is_a_winner = true;


        if (there_is_a_winner) {

            if (!turn) {             // because O just played and the turn was reversed.
                toast("X Wins");
            } else {
                toast("O Wins");
            }

            enableDisableAllButtons(false);
        } else if (turn_count == 9) {
            toast("Draw");
        }

    }

    private void enableDisableAllButtons(boolean enable){

        for(Button b: barray) {

            b.setClickable(enable);
            if (enable) {
                //b.setBackgroundColor(Color.parseColor("#336699"));
                b.setBackgroundResource(R.drawable.button_selector);
                b.setText("");
            }else {
                b.setBackgroundColor(Color.LTGRAY);
            }
        }
    }
    private void toast (String message){         // To check if the Buttons respond to click
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}

