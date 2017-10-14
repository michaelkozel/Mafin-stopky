package kozliksoft.mafin_stopky;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayCounting extends AppCompatActivity {

    protected int minutes;
    protected TextView txbTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_counting);
        Intent intent = getIntent();
        txbTime = (TextView) findViewById(R.id.txbTime);
        minutes = intent.getIntExtra(MainActivity.MINUTES, 0);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //txbTime.setText(this.minutes);
        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        try {
            startCounting();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void startCounting() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String start = dateFormat.format(date);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(start);
        long difference = minutes * 60000 - 0;


        CountDownTimer timer = new CountDownTimer(difference, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                long second = (millisUntilFinished / 1000) % 60;
                long minute = (millisUntilFinished / (1000 * 60)) % 60;
                long hour = (millisUntilFinished / (1000 * 60 * 60)) % 24;


                String time;

                txbTime.setTextSize(150);
                if (minute < 1) {
                    {
                        time = "0:" + Long.toString(second);
                    }
                    txbTime.setText(time);
                } else {
                    time = Long.toString((millisUntilFinished / 60000) % 60);
                }
                txbTime.setText(time);
            }

            @Override
            public void onFinish() {
                txbTime.setTextColor(Color.parseColor("#ff0000"));
                txbTime.setText("KONEC");
                txbTime.setTextSize(150);

            }
        };
        timer.start();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
