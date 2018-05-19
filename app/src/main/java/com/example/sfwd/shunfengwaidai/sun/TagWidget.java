package com.example.sfwd.shunfengwaidai.sun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sfwd.shunfengwaidai.R;

public class TagWidget extends LinearLayout{
    private ImageView mImg;
    private TextView mTxv;
    private boolean isSelected = false;

    private float mDensity = 1.0f;

    public TagWidget(Context context) {
        super(context);
        initView();
    }

    public TagWidget(Context context,
                     @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TagWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        mDensity = getScreenDensity();

        setMinimumHeight((int) mDensity * 28);
        setMinimumWidth((int) mDensity * 80);

        setBackgroundResource(R.color.beige);

        mImg = new ImageView(getContext());
        mImg.setScaleType(ScaleType.CENTER_INSIDE);
        mImg.setImageResource(R.mipmap.ic_launcher_round);
        mImg.setVisibility(View.GONE);
        addView(mImg);

        mTxv = new TextView(getContext());
        mTxv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        mTxv.setTextColor(Color.GRAY);
        mTxv.setGravity(Gravity.CENTER);
        mTxv.setText("");
        addView(mTxv);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        LayoutParams parentParams = (LayoutParams) getLayoutParams();
        parentParams.width = (int) mDensity * 80;
        parentParams.height = (int) mDensity * 28;
        setLayoutParams(parentParams);

        LayoutParams params = (LayoutParams) mImg.getLayoutParams();
        params.setMarginStart((int) mDensity * 4);
        mImg.setLayoutParams(params);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 切换标签选中状态
     */
    public void toggle() {
        isSelected = !isSelected;
        if (isSelected) {
            mImg.setVisibility(View.VISIBLE);
            mTxv.setTextColor(Color.RED);
            setBackgroundResource(R.color.red);
        } else {
            mImg.setVisibility(View.GONE);
            mTxv.setTextColor(Color.GRAY);
            setBackgroundResource(R.color.grey);
        }
    }

    /**
     * 获取标签里面的内容
     */
    public String getContent() {
        return mTxv == null ? "" : mTxv.getText().toString().trim();
    }

    /**
     * 设置标签内容
     */
    public void setContent(String content) {
        mTxv.setText(content);
    }

    /**
     * 设置标签内容
     *
     * @param res 内容对应的资源地址
     */
    public void setContent(int res) {
        mTxv.setText(getContext().getString(res));
    }

    /**
     * 获取标签的选中状态
     *
     * @return 如果选中返回 true，否则返回 false。
     */
    public boolean getStatus() {
        return isSelected;
    }

    private float getScreenDensity() {
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }
}
