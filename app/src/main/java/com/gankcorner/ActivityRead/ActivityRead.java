package com.gankcorner.ActivityRead;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gankcorner.Adapter.AdapterFragment;
import com.gankcorner.R;
import com.gankcorner.Utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityRead extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        initView();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        //同一个TextView实现不同大小字体
        TextView toolbarTitle = (TextView) findViewById(R.id.read_title);
        SpannableString spanStr = new SpannableString(getResources().getString(R.string.read_title));
        spanStr.setSpan(new ForegroundColorSpan(
                getResources().getColor(R.color.black)),
                0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spanStr.setSpan(new RelativeSizeSpan(0.75f),
                2, spanStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        toolbarTitle.setText(spanStr);

        ImageView readBack = (ImageView) findViewById(R.id.read_back);
        readBack.setOnClickListener(this);
        ImageView changeItem = (ImageView) findViewById(R.id.change_item);
        changeItem.setOnClickListener(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        List<Fragment> fragmentList = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.read_page);
        for (int i = 0; i < titles.length; i++) {
            fragmentList.add(new FragmentReadArticle());
        }

        AdapterFragment adapterFragment = new
                AdapterFragment(getSupportFragmentManager(),
                this, fragmentList, titles);

        //在Fragment当中
        viewPager.setAdapter(adapterFragment);
        //将TabLayout与ViewPager联系起来
        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.read_back:
                ActivityRead.this.finish();
                break;
            case R.id.change_item:
                Toast.makeText(this, "改变Tab", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }
}
