package com.camera360.yuyidong.pulltorefreshdemo.pulltorefreshdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private PullToRefreshListView mListView;
    private List<String> mList;
    private String[] mStr = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"};
    private MyAdapter mMyAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.i("handleMessage", "handleMessage");
                    mListView.onRefreshComplete();
                    mMyAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = new ArrayList<String>();
        for (int i = 0; i < mStr.length; i++) {
            mList.add(mStr[i]);
        }
        mListView = (PullToRefreshListView) findViewById(R.id.lv_main);
//        mListView.setOnRefreshListener(new MyRefreshListener());
        mListView.setOnRefreshListener(new MyRefreshListener2());
        mMyAdapter = new MyAdapter();
        mListView.setAdapter(mMyAdapter);
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.getLoadingLayoutProxy().setLastUpdatedLabel("setLastUpdatedLabel");
        mListView.getLoadingLayoutProxy().setLoadingDrawable(getResources().getDrawable(R.drawable.ic_launcher));
        mListView.getLoadingLayoutProxy().setPullLabel("setPullLabel");
        mListView.getLoadingLayoutProxy().setRefreshingLabel("setRefreshingLabel");
        mListView.getLoadingLayoutProxy().setReleaseLabel("setReleaseLabel");

    }

    class MyRefreshListener implements PullToRefreshBase.OnRefreshListener {

        @Override
        public void onRefresh(PullToRefreshBase refreshView) {

        }
    }

    class MyRefreshListener2 implements PullToRefreshBase.OnRefreshListener2 {

        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            mList.clear();
            for (int i = 0; i < mStr.length; i++) {
                mList.add(mStr[i]);
            }
            new Thread(new MyRunnable()).start();
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            for (int i = 0; i < mStr.length; i++) {
                mList.add(mStr[i]);
            }
            new Thread(new MyRunnable()).start();
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
                TextView tv = (TextView) view.findViewById(R.id.txt);
                tv.setText(mList.get(i));
            } else {
                TextView tv = (TextView) view.findViewById(R.id.txt);
                tv.setText(mList.get(i));
            }
            return view;
        }
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("MyRunnable", "MyRunnable");
            mHandler.sendEmptyMessage(1);
        }
    }
}
