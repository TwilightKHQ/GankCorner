package com.gankcorner.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import com.gankcorner.Bean.GroupInfoBean;
import com.gankcorner.Bean.WanNavigation;
import com.gankcorner.R;

/**
 * Created by frank on 2017/4/11.
 */
public class ItemDecorationSticky extends RecyclerView.ItemDecoration {

    private GroupInfoCallback mCallback;
    private int mHeaderHeight;
    private int mTopHeight = 60;
    private int mDividerHeight;

    //用来绘制Header上的文字
    private TextPaint mTextPaint;
    private Paint mPaint;
    private float mTextSize;
    private Paint.FontMetrics mFontMetrics;

    private float mTextOffsetX;

    public ItemDecorationSticky(Context context, GroupInfoCallback callback) {
        this.mCallback = callback;
        mDividerHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_divider_height);
        mHeaderHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_height);
        mTextSize = context.getResources().getDimensionPixelOffset(R.dimen.header_text_size);

        mHeaderHeight = (int) Math.max(mHeaderHeight, mTextSize);

        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(mTextSize);
        mFontMetrics = mTextPaint.getFontMetrics();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);

    }

//    @Override
//    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
//                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
//
//        int position = parent.getChildAdapterPosition(view);
//
//        if (mCallback != null) {
//            GroupInfoBean groupInfoBean = mCallback.getGroupInfo(position);
//
//            //如果是组内的第一个则将间距撑开为一个Header的高度，否则就是普通的分割线高度
//            if (groupInfoBean != null && groupInfoBean.isFirstViewInGroup()) {
//                outRect.top = mHeaderHeight;
//            } else {
//                outRect.top = mDividerHeight;
//            }
//        }
//    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int index = parent.getChildAdapterPosition(view);
        if (isFirstOfGroup(index)) {
            outRect.top = mTopHeight;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        // 得到item真实的left和right（减去parent的padding）
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i != parent.getChildCount(); i++) {
            // 直接获得的child只有当前显示的，所以就算i是0的index也只是当前第一个，而不是所有第一个
            View child = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(child);
            if (isFirstOfGroup(index)) {
                // 每组第一个item都留有空间来绘制
                int top = child.getTop() - mTopHeight;
                int bottom = child.getTop();
                // 绘制背景色
                Paint paint = new Paint();
                paint.setColor(Color.YELLOW);
                c.drawRect(left, top, right, bottom, paint);
                // 绘制组名
                paint.setColor(Color.BLACK);
                paint.setTextSize(60);
                paint.setTextAlign(Paint.Align.LEFT);
                paint.setAntiAlias(true);
                c.drawText(getGroupName(index), left, bottom, paint);
            }
        }
    }


    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        for (int i = 0; i != parent.getChildCount(); i++) {
            // 直接获得的child只有当前显示的，所以就算i是0的index也只是当前第一个，而不是所有第一个
            View child = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(child);
//            if (isFirstOfGroup(index)) {
                int bottom = 0;
                if (isFirstOfGroup(index + 1)) {
                    // 下一个组马上到达顶部
                    bottom = Math.min(child.getBottom(), mTopHeight);
                } else {
                    // 普通情况
                    bottom = mTopHeight;
                }
                // 绘制背景色
                Paint paint = new Paint();
                paint.setColor(Color.YELLOW);
                c.drawRect(0, 0, parent.getWidth(), bottom, paint);
                // 绘制组名
                paint.setColor(Color.BLACK);
                paint.setTextSize(60);
                paint.setTextAlign(Paint.Align.LEFT);
                paint.setAntiAlias(true);
//                c.drawText(getGroupName(index), 0, bottom, paint);
                drawHeaderRect(c, getGroupName(index), 0, 0 , parent.getWidth(), bottom);
            }
//        }
    }

    private void drawHeaderRect(Canvas c, String title, int left, int top, int right, int bottom) {
        //绘制Header
        c.drawRect(left, top, right, bottom, mPaint);

        float titleX = left + mTextOffsetX;
        float titleY = bottom - mFontMetrics.descent;
        //绘制Title
        c.drawText(title, titleX, titleY, mTextPaint);
    }

    public GroupInfoCallback getCallback() {
        return mCallback;
    }

    public void setCallback(GroupInfoCallback callback) {
        this.mCallback = callback;
    }

    public interface GroupInfoCallback {
        GroupInfoBean getGroupInfo(int position);
    }
    private String getGroupName(int index) {
        return "第" + (index / 7 + 1) + "组";
    }

    private boolean isFirstOfGroup(int index) {
        return index % 7 == 0;
    }
}
