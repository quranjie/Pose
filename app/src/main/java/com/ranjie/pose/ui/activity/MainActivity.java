package com.ranjie.pose.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ranjie.http.api.BaseResultEntity;
import com.ranjie.http.http.HttpManager;
import com.ranjie.http.listener.HttpOnNextListener;
import com.ranjie.pose.R;
import com.ranjie.pose.http.api.SubjectPostApi;
import com.ranjie.pose.http.result.SubjectResult;
import com.ranjie.pose.ui.activity.base.BaseActivity;
import com.ranjie.pose.ui.adapter.StaggeredRecycleViewAdapter;
import com.ranjie.pose.util.L;
import com.ranjie.recyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private int mCount = 1;
    private StaggeredRecycleViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(true);
        }

        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
        //mPullLoadMoreRecyclerView.setRefreshing(true);
        mPullLoadMoreRecyclerView.setStaggeredGridLayout(2);
        mRecyclerViewAdapter = new StaggeredRecycleViewAdapter(this, setList());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());

        simpleDo();
    }

    //    完美封装简化版
    private void simpleDo() {
        SubjectPostApi postEntity = new SubjectPostApi(simpleOnNextListener, this);
        postEntity.setAll(true);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(postEntity);
    }

    //   回调一一对应
    HttpOnNextListener simpleOnNextListener = new HttpOnNextListener<List<SubjectResult>>() {
        @Override
        public void onNext(List<SubjectResult> subjects) {
            L.d("网络返回：\n" + subjects.toString());
        }

        @Override
        public void onCacheNext(String cache) {
            /*缓存回调*/
            Gson gson = new Gson();
            java.lang.reflect.Type type = new TypeToken<BaseResultEntity<List<SubjectResult>>>() {
            }.getType();
            BaseResultEntity resultEntity = gson.fromJson(cache, type);
            L.d("缓存返回：\n" + resultEntity.getData().toString());
        }

        /*用户主动调用，默认是不需要覆写该方法*/
        @Override
        public void onError(Throwable e) {
            super.onError(e);
            L.d("失败：\n" + e.toString());
        }

        /*用户主动调用，默认是不需要覆写该方法*/
        @Override
        public void onCancel() {
            super.onCancel();
            L.d("取消請求");
        }
    };

    private List<Map<String, String>> setList() {
        List<Map<String, String>> dataList = new ArrayList<>();
        int start = 30 * (mCount - 1);
        Map<String, String> map;
        for (int i = start; i < 30 * mCount; i++) {
            map = new HashMap<>();
            map.put("text", "Third" + i);
            map.put("height", (120 + 5 * i) + "");
            dataList.add(map);
        }
        return dataList;

    }


    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerViewAdapter.getDataList().addAll(setList());
                mRecyclerViewAdapter.notifyDataSetChanged();
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        }, 1000);
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            setRefresh();
            getData();
        }

        @Override
        public void onLoadMore() {
            mCount = mCount + 1;
            getData();
        }
    }

    private void setRefresh() {

        mRecyclerViewAdapter.getDataList().clear();

        mCount = 1;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_clear:
                Toast.makeText(MainActivity.this, "First清除成功", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_about:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/WuXiaolong/PullLoadMoreRecyclerView"));
                startActivity(intent);
                break;
        }

        return true;
    }
}
