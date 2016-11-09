package com.toandoan.lol.widget.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.toandoan.lol.R;

/**
 * Created by Who are you on 20-Jun-16.
 */
public class AutoScrollViewPager extends RelativeLayout {
    private Context mContext;
    private ViewPager viewPager;
    private LinearLayout llSaleDots;
    private PagerAdapter adapter;
    private Runnable mAutoRunnable;
    private Handler mHandler;
    private int scrollPeriod = 2000;
    private long mThresHold = 0;
    private float preX = 0;
    private float preY = 0;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onPageSelected(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public int getScrollPeriod() {
        return scrollPeriod;
    }

    public void setScrollPeriod(int scrollPeriod) {
        this.scrollPeriod = scrollPeriod;
    }

    public void setAdapter(PagerAdapter saleAdapter) {
        this.adapter = saleAdapter;
        viewPager.setAdapter(adapter);
        for (int i = 0;
             i < adapter.getCount(); i++) {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext
                    .getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(dm);
            float density = dm.density;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (7 * density),
                    (int) (7 * density));
            params.rightMargin = (int) (4 * density);
            ImageView iv = new ImageView(getContext());
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setLayoutParams(params);
            if (i == 0) {
                iv.setImageResource(R.mipmap.dot_selected);
            } else {
                iv.setImageResource(R.mipmap.dot_not_selected);
            }
            llSaleDots.addView(iv);
        }
//        autoScroll();
    }

    public AutoScrollViewPager(Context context) {
        super(context);
        mContext = context;
        initUI();
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initUI();
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initUI();
    }

    private void setSelectedDot(int position) {
        for (int i = 0; i < adapter.getCount(); i++) {
            if (llSaleDots.getChildAt(i) == null)
                return;
            if (i == position) {
                ((ImageView) llSaleDots.getChildAt(i)).setImageResource(R.mipmap.dot_selected);
            } else {
                ((ImageView) llSaleDots.getChildAt(i)).setImageResource(R.mipmap.dot_not_selected);
            }
        }
    }

    public PagerAdapter getAdapter() {
        return adapter;
    }

    private void autoScroll() {
        mHandler.postDelayed(mAutoRunnable, scrollPeriod);
    }

    public void startScroll() {
        stopScroll();
        autoScroll();
    }

    private void stopScroll() {
        mHandler.removeCallbacks(mAutoRunnable);
    }

    private void initUI() {
        mHandler = new Handler();
        mAutoRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                if (currentItem != adapter.getCount() - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                } else {
                    viewPager.setCurrentItem(0, true);
                }
                autoScroll();
            }
        };

        View view = LayoutInflater.from(mContext).inflate(R.layout.auto_scroll_pager, this);
        llSaleDots = (LinearLayout) view.findViewById(R.id.llSaleDots);
        viewPager = (ViewPager) view.findViewById(R.id.vpSale);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setSelectedDot(position);
                mOnItemClickListener.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        mThresHold = System.currentTimeMillis();
                        preX = event.getX();
                        preY = event.getY();
                        mHandler.removeCallbacks(mAutoRunnable);
                        Log.d("chitm", "viewPager ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_UP:
                        long diff = System.currentTimeMillis() - mThresHold;
                        float diffX = event.getX() - preX;
                        float diffY = event.getY() - preY;
                        diffX = Math.abs(diffX);
                        diffY = Math.abs(diffY);
                        if (diff <= 100 && diffX <= 30 && diffY <= 30) {
                            //click event
                            mOnItemClickListener.onItemClick(viewPager.getCurrentItem());
                        }
                        mHandler.removeCallbacks(mAutoRunnable);
                        autoScroll();
                        Log.d("chitm", "viewPager ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.d("chitm", "viewPager ACTION_CANCEL");
                        mHandler.removeCallbacks(mAutoRunnable);
                        autoScroll();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

//        viewPager.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnItemClickListener.onItemClick(viewPager.getCurrentItem());
//            }
//        });
    }
}
