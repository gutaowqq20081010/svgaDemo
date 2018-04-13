package com.gutao.svgademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private SVGAImageView svgaImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        svgaImageView = findViewById(R.id.svgaImageView);
        svgaImageView.setCallback(new SVGACallback() {
            @Override
            public void onPause() {
                Log.d(TAG, "onPause");
            }

            @Override
            public void onFinished() {
                Log.d(TAG, "onFinished");

            }

            @Override
            public void onRepeat() {
                Log.d(TAG, "onRepeat");
            }

            @Override
            public void onStep(int i, double v) {
                Log.d(TAG, "onStep: i=" + i + " v=" + v);
            }
        });
        svgaImageView.setLoops(1);
        SVGAParser parser = new SVGAParser(this);
        parser.parse("lock_air.svga", new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                SVGADrawable drawable = new SVGADrawable(videoItem);
                svgaImageView.setImageDrawable(drawable);
                svgaImageView.startAnimation();

            }

            @Override
            public void onError() {
                Log.i(TAG, "error");
            }
        });
//        svgaImageView.pauseAnimation();暂停动画
    }

}
