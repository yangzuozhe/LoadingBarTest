package com.example.loadingbartest;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyProgress extends LinearLayout {
    private TextView mTvProgress;
    private ProgressBar mPbProgress;
    private TextView mTvButton;
    public static final int KEY_UI = 88;
    MyHandler mHandler = new MyHandler();
    private int mTime;

    public MyProgress(Context context) {
        this(context, null);
    }

    public MyProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.my_progress, this);
        mTvProgress = findViewById(R.id.tvProgress);
        mPbProgress = findViewById(R.id.pbProgress);
        mTvButton = findViewById(R.id.tvButton);
        mTvButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startProgressThread();

            }
        });


    }

    /**
     * 开启进度条线程
     */
    private void startProgressThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        mTime = mTime + 10;
                        Thread.sleep(1000);
                        mHandler.sendMessage(KEY_UI, mTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    private void setProgress(int progress) {
        if (progress >= 100) {
            mTvProgress.setText("100%");
            mPbProgress.setProgress(100);
            mTvButton.setVisibility(VISIBLE);
            mTvButton.setSelected(true);
            return;
        } else {
            mTvButton.setVisibility(GONE);
            mTvProgress.setText(progress + "%");
            mPbProgress.setProgress(progress);
        }

    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case KEY_UI: {
                    final int progress = (int) msg.obj;
                    setProgress(progress);
                    break;
                }
                default:
                    break;
            }
        }

        public void sendMessage(int what, int progress) {
            Message message = Message.obtain();
            message.what = what;
            message.obj = progress;
            mHandler.sendMessage(message);
        }
    }


}
