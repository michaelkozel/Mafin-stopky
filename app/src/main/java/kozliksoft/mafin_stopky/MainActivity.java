package kozliksoft.mafin_stopky;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private final int MY_PERMISSIONS_REQUEST_INTERNET = 1;
    public final static String MINUTES = "kozliksoft.countdown.MINUTES";
    EditText minutesAdd;
    String topic = "MAFIN";
    String topic2 = "mafin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    android.Manifest.permission.INTERNET)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.INTERNET},
                        MY_PERMISSIONS_REQUEST_INTERNET);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        minutesAdd = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);
        button.setText("Start");
        Log.d("TAG","Test log");
        FirebaseMessaging.getInstance().subscribeToTopic(topic);
        FirebaseMessaging.getInstance().subscribeToTopic(topic2);
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
