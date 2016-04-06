package io.sharif.prj1.st91103527.st91101497;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout l=(LinearLayout)findViewById(R.id.sample_main_layout);
        TextView tv=new TextView(getApplicationContext());
        tv.setText("alakieeee!!!");
        l.addView(tv);
    }

}
