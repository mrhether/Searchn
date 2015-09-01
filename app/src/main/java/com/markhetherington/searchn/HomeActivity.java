package com.markhetherington.searchn;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.markhetherington.searchn.ui.RecyclerViewInfinteScrollListener;
import com.markhetherington.searchn.ui.SpacesItemDecoration;
import com.markhetherington.searchn.adapter.ShotsAdapter;
import com.markhetherington.searchn.network.DribbbleService;
import com.markhetherington.searchn.network.model.Shot;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    @Inject SearchNApplication mApplication;
    @Inject DribbbleService mDribbbleService;

    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tabLayout) TabLayout mTabLayout;

    @BindDimen(R.dimen.activity_horizontal_margin) int mMargin;

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
        mRecyclerView.addOnScrollListener(new RecyclerViewInfinteScrollListener() {
            @Override
            public void shouldLoadMore() {
                loadContent(new Runnable() {
                    @Override
                    public void run() {
                        setCanLoadMore(true);
                    }
                });
            }
        });

        // Setup Tabs
        mTabLayout.setOnTabSelectedListener(this);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.list).setTag(R.id.layout_list), true);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.grid).setTag(R.id.layout_grid));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.staggered).setTag(R.id.layout_staggered));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadContent(null);
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

    private void loadContent(final Runnable runnable) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //TODO: RxObserver or in a service
                    final List<Shot> shots = mDribbbleService.animatedShots(mNextPage);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!HomeActivity.this.isDestroyed()) {
                                if (mRecyclerView.getAdapter() instanceof ShotsAdapter) {
                                    ((ShotsAdapter) mRecyclerView.getAdapter()).appendShots(shots);
                                } else {
                                    ShotsAdapter shotsAdapter = new ShotsAdapter(shots);
                                    mRecyclerView.setAdapter(shotsAdapter);
                                }
                                mNextPage++;
                                if (runnable != null) {
                                    runnable.run();
                                }
                            }
                        }
                    });
                } catch(Exception ignored) {
                    //TODO: display error at the bottom of the list
                }
            }
        }).start();
    }


    ////////////////////////////////////////////
    // TabLayout.OnTabSelectedListener
    ////////////////////////////////////////////

    @Override
    public void onTabSelected(TabLayout.Tab mTab) {
        switch ((int)mTab.getTag()) {
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

}
