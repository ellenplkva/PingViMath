package com.androidexample.demopingvimath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubLevels extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_sub_back = (Button) findViewById(R.id.button_back);
        button_sub_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //команда для кнопки назад
                try {
                    Intent intent = new Intent(SubLevels.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
            }
        });
        // кнопка для перехода на уровень 1
        TextView textView4 = (TextView) findViewById(R.id.fold_lvl1);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(SubLevels.this, SubLevel1.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
            }
        });
    }

    // системная кнопка назад
    @Override
    public void onBackPressed () {
        try {
            Intent intent = new Intent(SubLevels.this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
}


