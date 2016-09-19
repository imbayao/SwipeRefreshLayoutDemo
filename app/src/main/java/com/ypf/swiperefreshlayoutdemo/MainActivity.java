package com.ypf.swiperefreshlayoutdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final int REFRESH_COMPLETE = 1;
    private ListView test_lv;
    private SwipeRefreshLayout mSwipeLayout;
    private ArrayAdapter<String> madpter;
    private List<String> mDatas = new ArrayList<String>(Arrays.asList("Test1","Test2","Test3","Test4","Test5"));

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case REFRESH_COMPLETE:
                    mDatas.addAll(Arrays.asList("Hello", "Elso", "lalala"));
                    madpter.notifyDataSetChanged(); //动态更新ListView
                    mSwipeLayout.setRefreshing(false);  //显示或者隐藏刷新进度条
                    break;
                default:
                    Toast.makeText(getApplication(), "加载失败", Toast.LENGTH_SHORT).show();
                    mSwipeLayout.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test_lv = (ListView)findViewById(R.id.listview1);
        mSwipeLayout = (SwipeRefreshLayout)findViewById(R.id.swiR1);

        mSwipeLayout.setOnRefreshListener(this);

        madpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        test_lv.setAdapter(madpter);
}

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 10); //sendEmptyMessageDelayed(what, 0) 0为延迟0ms
    }
}
