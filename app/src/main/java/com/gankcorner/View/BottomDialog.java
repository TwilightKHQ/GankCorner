package com.gankcorner.View;

import android.app.Dialog;
import android.content.Context;

import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.gankcorner.R;

/*
 *  全屏Dialog， 点击窗口外需要可以返回
 */
public class BottomDialog extends Dialog {

    private Context mContext;

    private RelativeLayout rootLayout;
    private Button btnCancel;

    public BottomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;

        initView();
    }

    private void initView() {

        // 设置外部点击 取消dialog
        setCancelable(true);
        // 获得窗体对象
        Window window = this.getWindow();
        // 设置窗体的对齐方式
        window.setGravity(Gravity.BOTTOM);
        // 设置窗体动画
        window.setWindowAnimations(R.style.AnimBottom);
        // 设置窗体的padding
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_dialog, null);
        rootLayout = (RelativeLayout) view.findViewById(R.id.rootView);
        btnCancel = (Button) view.findViewById(R.id.cancel);
        setContentView(view);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dismiss();
            }
        });

        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


}
