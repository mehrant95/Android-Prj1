package io.sharif.prj1.st91103527.st91101497;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener {

    private View gopherImage;
    private ImageView rotatingGopher;
    private RelativeLayout.LayoutParams gopherImageLayout;
    private RotateAnimation rotateGopher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createMainMenu();
        gopherImage = findViewById(R.id.gopher_image);

        // Gopher button listeners
        setGopherButtons();

        // set popup menu
        setPopupMenu();

        //rotate anim
        startRotateAnim();
    }

    private void setPopupMenu() {

        findViewById(R.id.popup_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.setOnMenuItemClickListener(MainActivity.this);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.show();

            }
        });

    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_game:
                Toast.makeText(this, "Comedy Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.new_game:
                Toast.makeText(this, "Movies Clicked", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    private void setGopherButtons() {

        ((Button) findViewById(R.id.leftBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gopherImage.animate().x(gopherImage.getX()+10).setDuration(500).start();

            }
        });

        ((Button) findViewById(R.id.rightBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gopherImage.animate().x(gopherImage.getX()+10).setDuration(500).start();

            }
        });

        ((Button) findViewById(R.id.upBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gopherImage.animate().y(gopherImage.getY()-10).setDuration(500).start();

            }
        });

        ((Button) findViewById(R.id.downBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gopherImage.animate().y(gopherImage.getY()+10).setDuration(500).start();

            }
        });

    }

    private void centerGopherImage() {

        if (gopherImage == null)
            gopherImage = findViewById(R.id.gopher_image);

        if (gopherImageLayout == null)
            gopherImageLayout = (RelativeLayout.LayoutParams) gopherImage.getLayoutParams();

        gopherImageLayout.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        gopherImage.setLayoutParams(gopherImageLayout);

    }

    public void startRotateAnim(){//91103527
        rotatingGopher = (ImageView) findViewById(R.id.image_anim);

        RotateAnimation anim =new RotateAnimation(0.0f, 360.0f,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(1000);
        anim.setInterpolator(new LinearInterpolator());

        rotateGopher=anim;
        rotatingGopher.startAnimation(rotateGopher);
    }

    @Override
    protected void onPause() {//91103527
        super.onPause();
        rotateGopher.cancel();
    }

    @Override
    protected void onResume() {//91103527
        super.onResume();
        rotateGopher.reset();
        rotateGopher.start();
    }

    public void createMainMenu(){
       MenuInflater inflater= getMenuInflater();
        inflater.infl

    }

}