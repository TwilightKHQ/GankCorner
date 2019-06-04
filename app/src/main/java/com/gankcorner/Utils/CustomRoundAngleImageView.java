package com.gankcorner.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

/*
 * 为ImageView添加圆角效果
 */

public class CustomRoundAngleImageView extends AppCompatImageView {

    float width, height;
    int radian = 20; //圆角大小

    public CustomRoundAngleImageView(Context context) {
        this(context, null);
        init(context, null);
    }

    public CustomRoundAngleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public CustomRoundAngleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (width >= radian && height > radian) {
            Path path = new Path();
            //四个圆角
            path.moveTo(radian, 0);
            path.lineTo(width - radian, 0);
            path.quadTo(width, 0, width, radian);
            path.lineTo(width, height - radian);
            path.quadTo(width, height, width - radian, height);
            path.lineTo(radian, height);
            path.quadTo(0, height, 0, height - radian);
            path.lineTo(0, radian);
            path.quadTo(0, 0, radian, 0);

            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }

}
