package com.upv.arbe.arcp;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.upv.arbe.arcp.views.ArView;
import com.upv.arbe.arcp.views.MenuView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private AppState appState;
    private DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appState = new AppState();

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        appState.setCenterX(metrics.widthPixels / 2);
        appState.setCenterY(metrics.heightPixels / 2);

        ArView arView = (ArView) getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);
        MenuView gallery = findViewById(R.id.gallery_layout);

        assert arView != null && gallery != null;

        arView.init(new WeakReference<>(this));
        gallery.init(new WeakReference<>(this));
    }

    public void TouchView(View view)
    {
        if (!appState.getIsFocusing()) return;
        
        int centerX = metrics.widthPixels / 2;
        int centerY = metrics.heightPixels / 2;
        view.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
                SystemClock.uptimeMillis() + 100, MotionEvent.ACTION_DOWN, centerX, centerY, 0));
        view.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
                SystemClock.uptimeMillis() + 100, MotionEvent.ACTION_UP, centerX, centerY, 0));
    }

    public AppState getAppState() {
        return appState;
    }
}
