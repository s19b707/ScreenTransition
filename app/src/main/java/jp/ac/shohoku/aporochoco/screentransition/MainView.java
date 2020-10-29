package jp.ac.shohoku.aporochoco.screentransition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 一つの Activty，一つの View で状態遷移する場合
 Created on 2020/10/23.
 */
public class MainView extends View{
    public final int FIRST = 1; //状態を表す定数 1
    public final int SECOND = 2; //状態を表す定数 2
    public final int THIRD = 3; //状態を表す定数 3
    int state; //状態を表す変数
    /**
     * コンストラクタ
     * @param context
     * @param attrs
     */
    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        state = FIRST; //はじめは状態１
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();

        if(state == FIRST) { //状態 1 の場合の描画
              p.setColor(Color.BLUE);
              canvas.drawARGB(255, 255, 255, 255);
              canvas.drawRect(100, 100, 300, 200, p);
        } else if (state == SECOND) { //状態 2 の場合の描画
              p.setColor(Color.RED);
              canvas.drawARGB(255, 255, 255, 0);
              canvas.drawRect(400, 100, 600, 200, p);
        } else if (state == THIRD){ //状態 3 の場合の描画
              p.setColor(Color.GREEN);
              canvas.drawARGB(255, 0, 255, 255);
              canvas.drawRect(700, 100, 900, 200, p);
        } else { //それ以外の場合は，Log にエラーを吐き出す
              Log.d("error", "never come here");
        }
    }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
          int x = (int) event.getX();
          int y = (int) event.getY();


          //長方形の内部で
          if(x>100 && x < 300 && y>100 && y<200) {
              if (state == FIRST) { //状態１だったら状態２へ
                  state = SECOND;
              }
          }
          if(x>400 && x < 600 && y>100 && y<200) {
              if (state == SECOND) { //状態 2 だったら状態 1 へ
                  state = THIRD;
              }
          }
          if(x>700 && x < 900 && y>100 && y<200) {
              if (state == THIRD){ //状態 3だったら状態 1 へ
                  state = FIRST;
          }
              } else { //それ以外だったらエラーを吐き出す
                  Log.d("error", "never come here");
              }


          invalidate(); //再描画
          return super.onTouchEvent(event);
        }
}