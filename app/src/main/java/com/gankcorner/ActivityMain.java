package com.gankcorner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.gankcorner.Adapter.AdapterBottomFragment;
import com.gankcorner.Fragment.Fragment_Mine;
import com.gankcorner.GankIO.GankIOPage;
import com.gankcorner.Fragment.Fragment_test;
import com.gankcorner.Utils.AppUtil;
import com.gankcorner.Utils.DrawableTextView;
import com.gankcorner.WanAndroid.WanAndroidPage;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "=======zzq";

    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<TextView> textViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeStatusBar();

        initView();

        initOnClickEvents();
    }


    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
//        mBottomTab = (TabLayout) findViewById(R.id.bottom_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        fragmentList.add(new Fragment_Mine());
        fragmentList.add(new WanAndroidPage());
        fragmentList.add(new Fragment_test());
        fragmentList.add(new GankIOPage());
        String[] titles = getResources().getStringArray(R.array.title);
        AdapterBottomFragment adapterBottomFragment = new
                AdapterBottomFragment(getSupportFragmentManager(),
                this, fragmentList, titles);
        mViewPager.setAdapter(adapterBottomFragment);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                changeTitle(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
//        mViewPager.setCanScroll(false);

        //绑定
//        mBottomTab.setupWithViewPager(mViewPager);
    }

    private void initOnClickEvents() {
        CircleImageView mHeadPic = (CircleImageView) findViewById(R.id.head_pic);
        mHeadPic.setOnClickListener(this);
        TextView mMine = (TextView) findViewById(R.id.mine);
        mMine.setOnClickListener(this);
        textViewList.add(mMine);
        TextView mGank = (TextView) findViewById(R.id.gank);
        mGank.setOnClickListener(this);
        textViewList.add(mGank);
        TextView mWelfare = (TextView) findViewById(R.id.welfare);
        mWelfare.setOnClickListener(this);
        textViewList.add(mWelfare);
        TextView mFun = (TextView) findViewById(R.id.fun);
        mFun.setOnClickListener(this);
        textViewList.add(mFun);
        TextView mHome = (TextView) findViewById(R.id.home_page);
        mHome.setOnClickListener(this);
        TextView mQuestion = (TextView) findViewById(R.id.question);
        mQuestion.setOnClickListener(this);
        TextView mAboutMe = (TextView) findViewById(R.id.about_me);
        mAboutMe.setOnClickListener(this);
        TextView mStar = (TextView) findViewById(R.id.star);
        mStar.setOnClickListener(this);
        TextView mLocation = (TextView) findViewById(R.id.location);
        mLocation.setOnClickListener(this);
        TextView mScanCode = (TextView) findViewById(R.id.scan);
        mScanCode.setOnClickListener(this);
        DrawableTextView mSetting = (DrawableTextView) findViewById(R.id.settings);
        mSetting.setOnClickListener(this);
        DrawableTextView mExit = (DrawableTextView) findViewById(R.id.exit);
        mExit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_pic:
                mDrawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.mine:
                mViewPager.setCurrentItem(0);
                changeTitle(0);
                break;
            case R.id.gank:
                mViewPager.setCurrentItem(1);
                changeTitle(1);
                break;
            case R.id.welfare:
                mViewPager.setCurrentItem(2);
                changeTitle(2);
                break;
            case R.id.fun:
                mViewPager.setCurrentItem(3);
                changeTitle(3);
                break;
            case R.id.home_page:
                mDrawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.question:
                Toast.makeText(this, "问题反馈", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_me:
                Toast.makeText(this, "关于我", Toast.LENGTH_SHORT).show();
                break;
            case R.id.star:
                Toast.makeText(this, "我的收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.location:
                Toast.makeText(this, "定位", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scan:
                Toast.makeText(this, "扫码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                //退出应用
                Intent intent = new Intent(AppUtil.getContext(), ActivityMain.class);
                intent.putExtra("Exit_TAG", "SingleTASK");
                startActivity(intent);
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    //退出App
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String tag = intent.getStringExtra("Exit_TAG");
        if (tag != null && !TextUtils.isEmpty(tag)) {
            if ("SingleTASK".equals(tag)) {
                //退出程序
                finish();
            }
        }
    }

    private void changeTitle(int position) {
        for (int i = 0; i < textViewList.size(); i++) {
            if (i == position) {
                textViewList.get(i).setTextColor(getResources().getColor(R.color.black));
                textViewList.get(i).setTextSize(18);
            } else {
                textViewList.get(i).setTextColor(getResources().getColor(R.color.text_color));
                textViewList.get(i).setTextSize(15);
            }
        }
    }

    private void changeStatusBar() {
        // 状态栏透明， 使得沉浸式状态栏有效
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //状态栏字体设置为黑色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }


}
