package com.androidexample.demopingvimath;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class level1 extends AppCompatActivity {

    public int numLeft;//для левой картинки и текста
    public int numRight;//для правой
    public int sum = 0;
    Array array = new Array();
    Random random = new Random();//для генерации случайных чисел
    public int count = 0;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) // эта штука добавилась при setClipOutline
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        //скругление углов для макета цифр левая картинка
        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        img_left.setClipToOutline(true);

        //добавление скругления для правой картинки цифр
        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        img_right.setClipToOutline(true);


        // развернуть игру на весь экран
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Для кнопки больше
        Button button_greater = (Button) findViewById(R.id.button_greater);
        //Для кнопки равно
        Button button_equals = (Button) findViewById(R.id.button_equally);
        //Дя кнопки меньше
        Button button_less = (Button) findViewById(R.id.button_less);

        Button btn_back1 = (Button) findViewById(R.id.button_back1);

        // кнопка назад в приложении
        btn_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // обработка нажатия на кнопку
                try {
                    Intent intent = new Intent(level1.this, Gamelevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        // Для левой картинки цифры
        numLeft = random.nextInt(10);// генерирую случайное число
        img_left.setImageResource(array.images1[numLeft]);// достаю картинку

        //Для правой картинки цифры
        numRight = random.nextInt(10);
        img_right.setImageResource(array.images1[numRight]);

        final int[] progress = {
                R.id.point1,R.id.point2,R.id.point3,R.id.point4,R.id.point5,
                R.id.point6,R.id.point7,R.id.point8,R.id.point9,R.id.point10,
                R.id.point11,R.id.point12,R.id.point13,R.id.point14,R.id.point15,
                R.id.point16,R.id.point17,R.id.point18,R.id.point19,R.id.point20,
        };


        //обработка касаний на кнопки сравнения
        button_greater.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //блокирую другие кнопки

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button_equals.setEnabled(false);
                    button_less.setEnabled(false);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // если отпустили палец
                    if(numLeft > numRight){
                        if(count < 20){
                            count+=1;
                        }
                        // закрашываю прогресс серым цветом
                        for(int i = 0; i < 20;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // определяю правильные ответы и закрышиваю зелёным
                        for(int i = 0; i < count; i ++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else
                        {
                        // если левая картинка меньше и ответ неверный
                            if(count > 0){
                                count -= 1;
                            }
                        }
                    // при неверном ответе отнимается от шкалы одна единица и закрашивается обратно в серый
                    for(int i = 0; i < 19;i++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points);
                    }
                    // определяю правильные ответы и закрышиваю зелёным
                    for(int i = 0; i < count; i ++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                    }
                    if(count == 20){
                        //выход из уровня
                    }else{
                        // палец отпустили значит надо генерить новые числа, но если ошибка в первом ответе, тогда надо ждать пока будет правильно

                        if(count < 1){
                            button_equals.setEnabled(true);
                            button_less.setEnabled(true);
                            // выводится высплывающее сообщение " попробуй ещё раз"
                             int time = 1000;
                             Toast toast = Toast.makeText(getApplicationContext(),"Попробуй ещё раз",Toast.LENGTH_LONG);
                             toast.setGravity(Gravity.CENTER,0,0);
                             toast.show();

                        }
                        else {
                            numLeft = random.nextInt(10);// генерирую случайное число
                            img_left.setImageResource(array.images1[numLeft]);// достаю картинку
                            //text_left.setText(array.texts1[numLeft]); // для изменения текста под картинкой

                            //Для правой картинки цифры
                            numRight = random.nextInt(10);
                            img_right.setImageResource(array.images1[numRight]);
                            // вкулючаем обратно кнопки
                            button_equals.setEnabled(true);
                            button_less.setEnabled(true);
                        }

                    }
                }
                return true;
            }
        });
        // Обрабатываю нажатие на кнопку равно
        button_equals.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //блокирую другие кнопки

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button_greater.setEnabled(false);
                    button_less.setEnabled(false);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // если отпустили палец
                    if(numLeft == numRight){
                        if(count < 20){
                            count+=1;
                        }
                        // закрашываю прогресс серым цветом
                        for(int i = 0; i < 20;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // определяю правильные ответы и закрышиваю зелёным
                        for(int i = 0; i < count; i ++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else
                    {
                        // если левая картинка не равна и ответ неверный
                        if(count > 0){
                            count -= 1;
                        }
                    }
                    // при неверном ответе отнимается от шкалы одна единица и закрашивается обратно в серый
                    for(int i = 0; i < 19;i++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points);
                    }

                    for(int i = 0; i < count; i ++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                    }
                    if(count == 20){
                        //выход из уровня
                    }else{
                        // палец отпустили значит надо генерить новые числа, но если ошибка в первом ответе, тогда надо ждать пока будет правильно

                        if(count < 1){
                            button_greater.setEnabled(true);
                            button_less.setEnabled(true);
                            // выводится высплывающее сообщение " попробуй ещё раз"
                            int time = 1000;
                            Toast toast = Toast.makeText(getApplicationContext(),"Попробуй ещё раз",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                            //только оно пока что не выводится
                        }
                        else {
                            numLeft = random.nextInt(10);// генерирую случайное число
                            img_left.setImageResource(array.images1[numLeft]);// достаю картинку

                            //Для правой картинки цифры
                            numRight = random.nextInt(10);
                            img_right.setImageResource(array.images1[numRight]);
                            // вкулючаем обратно кнопки
                            button_greater.setEnabled(true);
                            button_less.setEnabled(true);
                        }

                    }
                }
                return true;
            }
        });
        // обрабатываю нажатие на кнопку меньше
        button_less.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //блокирую другие кнопки

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button_equals.setEnabled(false);
                    button_greater.setEnabled(false);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // если отпустили палец
                    if(numLeft < numRight){
                        if(count < 20){
                            count+=1;
                        }
                        // закрашываю прогресс серым цветом
                        for(int i = 0; i < 20;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // определяю правильные ответы и закрышиваю зелёным
                        for(int i = 0; i < count; i ++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else
                    {
                        // если левая картинка меньше и ответ неверный
                        if(count > 0){
                            count -= 1;
                        }
                    }
                    // при неверном ответе отнимается от шкалы одна единица и закрашивается обратно в серый
                    for(int i = 0; i < 19;i++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points);
                    }
                    // определяю правильные ответы и закрышиваю зелёным
                    for(int i = 0; i < count; i ++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                    }
                    if(count == 20){
                        //выход из уровня
                    }else{
                        // палец отпустили значит надо генерить новые числа, но если ошибка в первом ответе, тогда надо ждать пока будет правильно

                        if(count < 1){
                            button_equals.setEnabled(true);
                            button_greater.setEnabled(true);
                            // выводится высплывающее сообщение " попробуй ещё раз"
                            int time = 1000;
                            Toast toast = Toast.makeText(getApplicationContext(),"Попробуй ещё раз",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                            //только оно пока что не выводится
                        }
                        else {
                            numLeft = random.nextInt(10);// генерирую случайное число
                            img_left.setImageResource(array.images1[numLeft]);// достаю картинку
                            //text_left.setText(array.texts1[numLeft]); // для изменения текста под картинкой

                            //Для правой картинки цифры
                            numRight = random.nextInt(10);
                            img_right.setImageResource(array.images1[numRight]);
                            // вкулючаем обратно кнопки
                            button_equals.setEnabled(true);
                            button_greater.setEnabled(true);
                        }

                    }
                }
                return true;
            }
        });
    }

    // системная кнопка назад
    @Override
    public void onBackPressed () {
        try {
            Intent intent = new Intent(level1.this, Gamelevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
      }




}

