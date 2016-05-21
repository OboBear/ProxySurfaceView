package com.me.obo.surfaceviewhook.invocation;

import android.app.Instrumentation;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.SurfaceView;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by obo on 16/5/21.
 * Email:obo1993@gmail.com
 * Git:https://github.com/OboBear
 * Blog:http://blog.csdn.net/leilba
 */
public class TestInvocation implements InvocationHandler {
    Object mObject ;

    public TestInvocation(Object object) {
        mObject = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 截取lockCanvas方法调用
        if ("lockCanvas".equals(method.getName())) {
            // lockCanvas方法返回值是canvas画布
            Canvas canvas = (Canvas) method.invoke(mObject,args);
            // 添加镜像
            canvas.scale(-1,1,canvas.getWidth()/2,canvas.getHeight()/2);

            return canvas;
        }
        return method.invoke(mObject,args);
    }
}
