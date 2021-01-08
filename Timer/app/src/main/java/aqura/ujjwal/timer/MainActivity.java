package aqura.ujjwal.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    Button button;
    CountDownTimer countDownTimer;
    int time=30;
    boolean isCounterActive=false;
    void displayTime(int i,TextView t){
        String q,r;
        q= i/60<10 ? "0"+i/60 : ""+i/60;
        r= i%60<10?"0"+i%60:""+i%60;
        String text= q+" : "+ r;
        t.setText(text);
    }

    void resetTimer(){
        countDownTimer.cancel();
        textView.setText("00: 30");
        seekBar.setEnabled(true);
        seekBar.setProgress(30);
        button.setText("START");
        isCounterActive=false;
    }
     public void startTimer(View view) {

             seekBar.setEnabled(false);
             button.setText("Stop");
             TextView textView = findViewById(R.id.textView);
             if (!isCounterActive) {
                 isCounterActive = true;
                 countDownTimer = new CountDownTimer(time * 1000, 1000) {
                     @Override
                     public void onTick(long l) {
                         displayTime((int) l / 1000, textView);
                         Log.i("seconds left", String.valueOf(l / 1000));
                     }

                     @Override
                     public void onFinish() {
                         Log.i("finished", "finished");
                         MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wolf);
                         mediaPlayer.start();
                         resetTimer();
                     }
                 }.start();
             } else {
                 resetTimer();
             }
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar= findViewById(R.id.seekBar);
        ImageView imageView= findViewById(R.id.imageView);
        button= findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        seekBar.setMax(600);
        seekBar.setProgress(30);

         seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                time=i;
                displayTime(i,textView);
                Log.i("time is",String.valueOf(time));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}