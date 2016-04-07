package io.sharif.prj1.st91103527.st91101497;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener {

    private View gopherImage;

    private float centerX, centerY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize
        gopherImage = findViewById(R.id.gopher_image);

        // listen for gopher image position
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

                        centerX = gopherImage.getX();

                        centerY = gopherImage.getY();

                        ((View) (gopherImage.getParent())).getViewTreeObserver().removeOnGlobalLayoutListener(this);
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
                break;
            case R.id.new_game:
                showCustomToast(getString(R.string.game_start));
                centerGopherImage();
                break;
        }
        return true;
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

                gopherImage.animate().x(gopherImage.getX()-10).setDuration(500).start();

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

        gopherImage.animate().x(centerX).y(centerY).setDuration(1000).start();

    }

}