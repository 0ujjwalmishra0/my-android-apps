package aqura.ujjwal.javademo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
//1 is red, 0 is yellow,2 is empty
    int[] gameState= {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;
    int activePlayer=0;

    public void restart(View view){
        this.recreate();
        //or we can do this like below:-
//        finish();
//        overridePendingTransition(0, 0);
//        startActivity(getIntent());
//        overridePendingTransition(0, 0);
    }
    public void dropIn(View view){
        FloatingActionButton button = findViewById(R.id.button);
        TextView textView= findViewById(R.id.textView);
        ImageView counter= (ImageView) view;
        int tappedCounter= Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive){
        gameState[tappedCounter]= activePlayer;
        counter.setTranslationY(-1000);
        if(activePlayer==0){
            counter.setImageResource(R.drawable.yellow);
            activePlayer=1;
        }
        else{
            counter.setImageResource(R.drawable.red);
            activePlayer=0;
        }
        counter.animate().translationYBy(1000).setDuration(300);
        counter.animate().rotation(360).setDuration(300);
        for(int [] i:winningPositions) {
            if (gameState[i[0]] == gameState[i[1]] && gameState[i[1]] == gameState[i[2]] && gameState[i[0]] != 2) {
                //someone has one
                String winner = "";
                if (activePlayer == 1) {
                    //winner is opposite of active player,bcz we are switching active player
                    winner = "Yellow";
                    gameActive=false;
                } else {
                    winner = "Red";
                    gameActive=false;
                }
//                Toast.makeText(this, winner + " has won!", Toast.LENGTH_LONG).show();
                textView.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                textView.setText(winner+" has won!");



            }
        }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}