package com.gankcorner.Utils;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.gankcorner.Adapter.AdapterMenuItem;
import com.gankcorner.Bean.MenuItem;
import com.gankcorner.R;

import java.util.ArrayList;
import java.util.List;


public class BottomDialogFragment extends DialogFragment {

    private List<MenuItem> menuItemList = new ArrayList<>();

    private TextView textCancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.bottom_dialog_fragment);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
//        lp.height = getActivity().getWindowManager().getDefaultDisplay().getHeight() / 3;
        window.setAttributes(lp);
        initMenu();
        initView(dialog);
        // 窗口初始化后 请求网络数据
        return dialog;
    }

    private void initMenu() {
        MenuItem menuItem = new MenuItem("刷新", R.mipmap.refresh);
        menuItemList.add(menuItem);
        menuItem = new MenuItem("复制链接", R.mipmap.copy);
        menuItemList.add(menuItem);
        menuItem = new MenuItem("浏览器打开", R.mipmap.browser);
        menuItemList.add(menuItem);
        menuItem = new MenuItem("添加到收藏", R.mipmap.colection);
        menuItemList.add(menuItem);
    }

    private void initView(Dialog dialog) {

        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ContextUtil.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        AdapterMenuItem adapterMenuItem = new AdapterMenuItem(menuItemList);
        recyclerView.setAdapter(adapterMenuItem);

        textCancel = (TextView) dialog.findViewById(R.id.cancel);

        setListener();

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
