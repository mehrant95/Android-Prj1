package io.sharif.prj1.st91103527.st91101497;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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

    private RelativeLayout.LayoutParams gopherImageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gopherImage = findViewById(R.id.gopher_image);

        // Gopher button listeners
        setGopherButtons();

        // set popup menu
        setPopupMenu();
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

}