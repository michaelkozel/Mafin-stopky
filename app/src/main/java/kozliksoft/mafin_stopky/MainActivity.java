package kozliksoft.mafin_stopky;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {


    public final static String MINUTES = "svandeliksoftware.countdown.MINUTES";
    EditText minutesAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minutesAdd = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);
        button.setText("Start");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareCounting(v);
            }
        });
    }

    /**
     * Called when the user clicks the Send button
     */
    public void prepareCounting(View view) {
        Intent intent = new Intent(this, DisplayCounting.class);
        int minutes = Integer.parseInt(minutesAdd.getText().toString());


        //button.setText(timePicker.getCurrentMinute().toString());

        intent.putExtra(MINUTES, minutes);
        startActivity(intent);
    }

}
