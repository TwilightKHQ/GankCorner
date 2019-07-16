package com.gankcorner.Utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public class BaseFragment extends Fragment implements FragmentUserVisibleController.UserVisibleCallback {

    private FragmentUserVisibleController userVisibleController;

    private boolean isFragmentVisible;
    private boolean isReuseView;
    private boolean isFirstVisible;
    private View rootView;

    public BaseFragment() {
        userVisibleController = new FragmentUserVisibleController(this, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userVisibleController.activityCreated();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //如果setUserVisibleHint()在rootView创建前调用时，那么
        //就等到rootView创建完后才回调onFragmentVisibleChange(true)
        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作
        if (rootView == null) {
            rootView = view;
            if (getUserVisibleHint() && isVisibleToUser()) {
                if (isFirstVisible) {
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                onFragmentVisibleChange(true);
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(isReuseView ? rootView : view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        initVariable();
    }

    private void initVariable() {
        isFirstVisible = true;
        isFragmentVisible = false;
        rootView = null;
        isReuseView = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        userVisibleController.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        userVisibleController.pause();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        userVisibleController.setUserVisibleHint(isVisibleToUser);
        if (rootView == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser()) {
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        if (isVisibleToUser()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }
    }

    @Override
    public void setWaitingShowToUser(boolean waitingShowToUser) {
        userVisibleController.setWaitingShowToUser(waitingShowToUser);
    }

    @Override
    public boolean isWaitingShowToUser() {
        return userVisibleController.isWaitingShowToUser();
    }

    @Override
    public boolean isVisibleToUser() {
        return userVisibleController.isVisibleToUser();
    }

    @Override
    public void callSuperSetUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {

    }

    /**
     * 设置是否使用 view 的复用，默认开启
     * view 的复用是指，ViewPager 在销毁和重建 Fragment 时会不断调用 onCreateView() -> onDestroyView()
     * 之间的生命函数，这样可能会出现重复创建 view 的情况，导致界面上显示多个相同的 Fragment
     * view 的复用其实就是指保存第一次创建的 view，后面再 onCreateView() 时直接返回第一次创建的 view
     *
     * @param isReuse 是否复用
     */
    protected void reuseView(boolean isReuse) {
        isReuseView = isReuse;
    }

    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     * <p>
     * 可在该回调方法里进行一些ui显示与隐藏，比如加载框的显示和隐藏
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    /**
     * 在fragment首次可见时回调，可在这里进行加载数据，保证只在第一次打开Fragment时才会加载数据，
     * 这样就可以防止每次进入都重复加载数据
     * 该方法会在 onFragmentVisibleChange() 之前调用，所以第一次打开时，可以用一个全局变量表示数据下载状态，
     * 然后在该方法内将状态设置为下载状态，接着去执行下载的任务
     * 最后在 onFragmentVisibleChange() 里根据数据下载状态来控制下载进度ui控件的显示与隐藏
     */
    protected void onFragmentFirstVisible() {

    }

    protected boolean isFragmentVisible() {
        return isFragmentVisible;
    }
}