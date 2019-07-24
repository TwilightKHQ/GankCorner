package com.gankcorner.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 为ImageView添加圆角效果
 * 图片尺寸为宽度铺满高度自适应
 */
public class ImageViewWidth extends AppCompatImageView {

    float width, height;
    float x, y;
    int radian = 20; //圆角大小

    public ImageViewWidth(Context context) {
        this(context, null);
        init(context, null);
    }

    public ImageViewWidth(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public ImageViewWidth(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    public void setImageSize(float x, float y) {
        this.x = x;
        this.y = y;
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable d = getDrawable();
        if (d != null) {
            //宽度铺满半个屏幕， 高度自适应
            int widthPix = MeasureSpec.getSize(widthMeasureSpec) / 2;
            //高度根据使得图片的宽度充满屏幕计算而得
            int heightPix = (int) Math.ceil((float) widthPix * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());
            setMeasuredDimension(widthPix, heightPix);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
