package com.androidexample.demopingvimath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonFold;
    Button buttonCompare;
    Button buttonSubstract;
    Button buttonSelection;

    private long backPressedTime;
    private Toast backToast; // необходимо для закрытие всплывающей надписи во время выхода из приложения


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // запрет показа строки состояния на экране
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //находим все кнопки на макете по идентификаторам
        buttonFold = (Button) findViewById(R.id.button_fold);
        buttonCompare = (Button) findViewById(R.id.button_compare) ;
        buttonSubstract = (Button) findViewById(R.id.button_substract);
        buttonSelection = (Button) findViewById(R.id.button_selection);

        // кнопка сравни перехода к игре сравни
        buttonCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, Gamelevels.class);// намерение перейти из файла MainActivity в геймлевел
                    startActivity(intent);finish();

                }catch (Exception e){

                }
            }
        });
        // кнопка перехода к игре сложи
        buttonFold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, FoldLevels.class);// намерение перейти из файла MainActivity в геймлевел
                    startActivity(intent);finish();

                }catch (Exception e){

                }
            }
        });
        //кнопка перехода к игре вычти
        buttonSubstract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, SubLevels.class);// намерение перейти из файла MainActivity в геймлевел
                    startActivity(intent);finish();

                }catch (Exception e){

                }
            }
        });
        //кнопка перехода к игре подбери цифру
        buttonSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, SelectLevels.class);// намерение перейти из файла MainActivity в геймлевел
                    startActivity(intent);finish();

                }catch (Exception e){

                }
            }
        });


    }
//системная кнопка назад, выход двойным нажатием
    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
         }else{
            backToast = Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis(); // получение времени нажатия на кнопку назад

    }
}