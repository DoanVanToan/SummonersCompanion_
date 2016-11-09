package com.toandoan.lol.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toandoan.lol.R;

/**
 * Created by ToanDoan on 10/4/2016.
 */

public class ProgressView extends LinearLayout {
    private LayoutInflater inflater;
    private TextView tvProgress;
    private int progress;
    private int color;
    private CountDownTimer countDownTimer;

    public ProgressView(Context context) {
        super(context);
        initView(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.progress_view, this);
        tvProgress = (TextView) this.findViewById(R.id.tvProgress);

        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.ProgressView,
                    0, 0);

            try {
                progress = a.getInteger(R.styleable.ProgressView_progress_value, 0);
                color = a.getInteger(R.styleable.ProgressView_background_color, 0);
            } finally {
                a.recycle();
            }

            switch (color) {
                case 0:
                    tvProgress.setBackgroundResource(R.drawable.shap_blue);
                    break;

                case 1:
                    tvProgress.setBackgroundResource(R.drawable.shap_green);
                    break;

                case 2:
                    tvProgress.setBackgroundResource(R.drawable.shap_red);
                    break;

                case 3:
                    tvProgress.setBackgroundResource(R.drawable.shap_violet);
                    break;

                default:
                    tvProgress.setBackgroundResource(R.drawable.shap_red);
                    break;
            }

            setProgress(progress);
        }
    }

    public void setProgress(final int progress) {
        final LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        countDownTimer = new CountDownTimer(1500, 50) {
            @Override
            public void onTick(long millisUntilFinished) {
                float pr = (float) (1500 - millisUntilFinished) / (float) 1500 * progress;
                params.weight = pr;
                tvProgress.setLayoutParams(params);

            }

            @Override
            public void onFinish() {
                tvProgress.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, progress));
            }
        };

        if (progress != 0) {
            countDownTimer.start();
        } else {
            tvProgress.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.0f));
        }
    }

    public int getProgress() {
        return progress;
    }
}
