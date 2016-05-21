package com.me.obo.surfaceviewhook.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by obo on 16/5/21.
 * Email:obo1993@gmail.com
 * Git:https://github.com/OboBear
 * Blog:http://blog.csdn.net/leilba
 */
public class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    public TestSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 获取canvas
        Canvas canvas = holder.lockCanvas();

        canvas.drawColor(Color.rgb(220,220,220));
        Paint paint = new Paint();

        paint.setTextSize(60);

        // 绘制文字
        canvas.drawText("Hello, this is SurfaceView",200,600,paint);
        // 绘制圆
        canvas.drawCircle(300,800,100,paint);

        // 显示
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}
}
