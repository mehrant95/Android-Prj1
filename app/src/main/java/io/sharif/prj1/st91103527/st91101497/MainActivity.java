package io.sharif.prj1.st91103527.st91101497;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener {

    private View gopherImage;

    private float centerX, centerY, boardWidth, boardHeight, next_pos;

    private final float delta_x = 10;

    private final long delta_t = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize
        gopherImage = findViewById(R.id.gopher_image);

        // listen for gopher image position set in parent relative layout
        listenGopherImage();

        // Gopher button listeners
        setGopherButtons();

        // set popup menu
        setPopupMenu();
    }

    private void listenGopherImage() {

        ((View) (gopherImage.getParent())).getViewTreeObserver().addOnGlobalLayoutListener(

                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        View parent = (View)gopherImage.getParent();

                        ViewGroup.MarginLayoutParams lp =
                                (ViewGroup.MarginLayoutParams) parent.getLayoutParams();

                        boardWidth = parent.getWidth();

                        boardHeight = parent.getHeight();

                        centerX = (boardWidth - gopherImage.getWidth()) / 2;

                        centerY = (boardHeight - gopherImage.getHeight()) / 2;

                        // load previous position
                        loadGame();

                        // delete listener
                        ((View) (gopherImage.getParent())).getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this);
                    }
                });

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
                showCustomToast(getString(R.string.game_saved));
                saveGame();
                break;
            case R.id.new_game:
                showCustomToast(getString(R.string.game_start));
                centerGopherImage();
                break;
        }
        return true;
    }

    private void saveGame() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putFloat("x_position", gopherImage.getX());
        editor.putFloat("y_position", gopherImage.getY());

        editor.commit();

    }

    private void loadGame() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        gopherImage.animate()
                .x(prefs.getFloat("x_position", centerX))
                .y(prefs.getFloat("y_position", centerY))
                .setDuration(1000).start();

    }

    private void showCustomToast(String str) {

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new RainbowSpan(this), 0, str.length(), 0);

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(spannableString);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

    private void setGopherButtons() {

        ((Button) findViewById(R.id.leftBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                next_pos = gopherImage.getX() - delta_x;

                if (next_pos < 0) next_pos = 0;

                gopherImage.animate().x(next_pos).setDuration(delta_t).start();

            }
        });

        ((Button) findViewById(R.id.rightBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                next_pos = gopherImage.getX() + delta_x;

                if (next_pos + gopherImage.getWidth() > boardWidth)
                    next_pos = boardWidth - gopherImage.getWidth();

                gopherImage.animate().x(next_pos).setDuration(delta_t).start();

            }
        });

        ((Button) findViewById(R.id.upBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                next_pos = gopherImage.getY() - delta_x;

                if (next_pos < 0) next_pos = 0;

                gopherImage.animate().y(next_pos).setDuration(delta_t).start();

            }
        });

        ((Button) findViewById(R.id.downBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                next_pos = gopherImage.getY() + delta_x;

                if (next_pos + gopherImage.getHeight() > boardHeight)
                    next_pos = boardHeight - gopherImage.getHeight();

                gopherImage.animate().y(next_pos).setDuration(delta_t).start();

            }
        });

    }

    private void centerGopherImage() {

        // position to center
        gopherImage.animate().x(centerX).y(centerY).setDuration(1000).start();

    }

}