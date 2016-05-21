package com.me.obo.surfaceviewhook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.me.obo.surfaceviewhook.invocation.TestInvocation;
import com.me.obo.surfaceviewhook.view.TestSurfaceView;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity {

    TestSurfaceView mTestSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        try {
            addProxy();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void findViews() {
        mTestSurfaceView = (TestSurfaceView) findViewById(R.id.surfaceView);
    }

    // 添加代理
    private void addProxy() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {

        // 获取 surfaceView中的 surfaceHolder
        SurfaceHolder mSurfaceHolder = mTestSurfaceView.getHolder();
        // 创建代理接口的实现
        TestInvocation testInvocation = new TestInvocation(mSurfaceHolder);
        // 为 mSurfaceHolder 添加动态代理,并获取添加代理之后的 newSurfaceHolder
        SurfaceHolder newSurfaceHolder = (SurfaceHolder) Proxy.newProxyInstance(mSurfaceHolder.getClass().getClassLoader(),mSurfaceHolder.getClass().getInterfaces(),testInvocation);

        // 获取mSurfaceHolder的field
        Field fieldHolder = SurfaceView.class.getDeclaredField("mSurfaceHolder");
        // 更改为可访问权限
        fieldHolder.setAccessible(true);
        // 用添加代理后的 newSurfaceHolder 替换 mSurfaceHolder
        fieldHolder.set(mTestSurfaceView,newSurfaceHolder);
    }

}
