package com.markhetherington.searchn;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.markhetherington.searchn.ui.RecyclerViewInfinteScrollListener;
import com.markhetherington.searchn.ui.SpacesItemDecoration;
import com.markhetherington.searchn.adapter.ShotsAdapter;
import com.markhetherington.searchn.network.DribbbleService;
import com.markhetherington.searchn.network.model.Shot;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;

public class HomeActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, Callback<List<Shot>> {

    private static final String TAG = HomeActivity.class.getSimpleName();

    @Inject SearchNApplication mApplication;
    @Inject DribbbleService mDribbbleService;

    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tabLayout) TabLayout mTabLayout;

    @BindDimen(R.dimen.activity_horizontal_margin) int mMargin;

    private final RecyclerViewInfinteScrollListener mScrollListener = new HomeRecyclerViewInfinteScrollListener(this);
    private int mNextPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SearchNApplication.get(this).getAppComponent().inject(this);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        // Setup RecyclerView
        setListLayout();
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(mMargin));
        mRecyclerView.addOnScrollListener(mScrollListener);

        // Setup Tabs
        mTabLayout.setOnTabSelectedListener(this);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.list).setTag(R.id.layout_list), true);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.grid).setTag(R.id.layout_grid));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.staggered).setTag(R.id.layout_staggered));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadContent();
    }

    private void setListLayout() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mApplication);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void setGridLayout() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mApplication, 2);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void setStaggeredLayout() {
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void loadContent() {
        mDribbbleService.animatedShots(mNextPage).enqueue(this);
    }

    ////////////////////////////////////////////
    // Callback<List<Shot>>
    ////////////////////////////////////////////

    @Override
    public void onResponse(Response<List<Shot>> response) {
        List<Shot> shots = response.body();

        if (!HomeActivity.this.isDestroyed()) {
            if (mRecyclerView.getAdapter() instanceof ShotsAdapter) {
                ((ShotsAdapter) mRecyclerView.getAdapter()).appendShots(shots);
            } else {
                ShotsAdapter shotsAdapter = new ShotsAdapter(shots);
                mRecyclerView.setAdapter(shotsAdapter);
            }
            mNextPage++;
            mScrollListener.setCanLoadMore(true);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        // // TODO: 15-09-01 display error
        Log.d(TAG, "Exception: \n" + t.toString());
    }

    ////////////////////////////////////////////
    // TabLayout.OnTabSelectedListener
    ////////////////////////////////////////////

    @Override
    public void onTabSelected(TabLayout.Tab mTab) {
        switch ((int) mTab.getTag()) {
            case R.id.layout_list:
                setListLayout();
                break;
            case R.id.layout_grid:
                setGridLayout();
                break;
            case R.id.layout_staggered:
                setStaggeredLayout();
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab mTab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab mTab) {
    }

    ////////////////////////////////////////////
    // RecyclerViewInfinteScrollListener
    ////////////////////////////////////////////

    private static class HomeRecyclerViewInfinteScrollListener extends RecyclerViewInfinteScrollListener {

        private WeakReference<HomeActivity> mActivity;

        public HomeRecyclerViewInfinteScrollListener(HomeActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void shouldLoadMore() {
            HomeActivity homeActivity = mActivity.get();
            if (homeActivity != null) {
                homeActivity.loadContent();
            }
        }
    }

}
