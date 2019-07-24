package com.gankcorner.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.gankcorner.Adapter.AdapterMenuItem;
import com.gankcorner.Entity.MenuItem;
import com.gankcorner.R;

import java.util.ArrayList;
import java.util.List;

import static com.gankcorner.Utils.AppUtil.getContext;


public class DialogSelect extends DialogFragment {

    private String type = null;

    private int REQUEST_CODE = 21;

    private List<MenuItem> menuItemList = new ArrayList<>();

    private TextView textCancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.dialog_bottom);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        initMenu();
        initView(dialog);
        // 窗口初始化后 请求网络数据
        return dialog;
    }

    //Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    private void initMenu() {
        MenuItem menuItem = new MenuItem("全部", R.mipmap.refresh);
        menuItemList.add(menuItem);
        menuItem = new MenuItem("Android", R.mipmap.copy);
        menuItemList.add(menuItem);
        menuItem = new MenuItem("iOS", R.mipmap.browser);
        menuItemList.add(menuItem);
        menuItem = new MenuItem("休息视频", R.mipmap.colection);
        menuItemList.add(menuItem);
        menuItem = new MenuItem("拓展资源", R.mipmap.colection);
        menuItemList.add(menuItem);
        menuItem = new MenuItem("前端", R.mipmap.colection);
        menuItemList.add(menuItem);
    }

    private void initView(Dialog dialog) {

        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.left_recycle_view);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        final AdapterMenuItem adapterMenuItem = new AdapterMenuItem(R.layout.item_select, menuItemList);
        recyclerView.setAdapter(adapterMenuItem);
        adapterMenuItem.setOnItemClickListener(new AdapterMenuItem.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                clickEvents(menuItemList.get(position).getName());
                if (getTargetFragment() != null) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("refresh_type", type);
                    getTargetFragment().onActivityResult(REQUEST_CODE, Activity.RESULT_OK,
                            resultIntent);
                }
                dismiss();
            }
        });

        textCancel = (TextView) dialog.findViewById(R.id.cancel);

        setListener();

    }

    private void clickEvents(String name) {
        switch (name) {
            case "全部":
                type = "all";
                break;
            case "Android":
                type = "Android";
                break;
            case "iOS":
                type = "iOS";
                break;
            case "休息视频":
                type = "休息视频";
                break;
            case "拓展资源":
                type = "拓展资源";
                break;
            case "前端":
                type = "前端";
                break;
        }
    }

    private void setListener() {
        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
