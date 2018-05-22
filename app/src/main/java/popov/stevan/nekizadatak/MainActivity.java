package popov.stevan.nekizadatak;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button drugi;
    Button start;
    Button stop;
    TextView text;
    private Context context;
    private static final String LOG_TAG = "ExampleService";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drugi = (Button) findViewById(R.id.drugi_main_activity);
        start = (Button) findViewById(R.id.start_main_activity);
        stop = (Button) findViewById(R.id.stop_main_activity);
        text = (TextView) findViewById(R.id.text_main);
        context = this;
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                SharedPreferences mPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
//                String Text = mPreferences.getString("Text", null);
//                text.setText(Text);
//
//            }
//        }).start();

        runThread();
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(LOG_TAG, "Hello from Main" );
//                SharedPreferences mPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
//                String Text = mPreferences.getString("Text", null);
//                text.setText(Text);
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        drugi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelloService.class);
                startService(intent);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelloService.class);
                stopService(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG, "Hello from Main" );
                SharedPreferences mPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                String Text = mPreferences.getString("Text", null);
                text.setText(Text);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void runThread() {

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Log.d(LOG_TAG, "Hello from Main" );
                                SharedPreferences mPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                String Text = mPreferences.getString("Text", null);
                                text.setText(Text);
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
