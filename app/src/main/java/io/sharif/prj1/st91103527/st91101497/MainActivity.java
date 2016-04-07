package io.sharif.prj1.st91103527.st91101497;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gopher button listeners
        setGopherButtons();

    }

    private void setGopherButtons() {


        View positiveButton = findViewById(R.id.gopher_image);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)positiveButton.getLayoutParams();
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        positiveButton.setLayoutParams(layoutParams);

//        ImageView img = (ImageView) findViewById(R.id.gopher_image);
//
//
//
//        img.animate().x(50).y(100).setDuration(1000).start();

    }

}
