package com.camera360.yuyidong.mymodule.app2.viewpagerbackgroudcolorchange.viewpagerbackgroudcolorchange;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private BackGroundImage mImg;
    private ViewPager mViewPager;
    private List<View> mViewLists;
    private List<Drawable> mDrawableLists;
    private static final int ALL = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initViews();
        mImg.setmDrawableLists(mDrawableLists);
        mViewPager.setAdapter(new MyAdapter());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                mImg.setmDegree(v);
                mImg.setmPosition(i);
                mImg.invalidate();
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initViews() {
        mImg = (BackGroundImage) findViewById(R.id.img);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewLists = new ArrayList<View>();
        mDrawableLists = new ArrayList<Drawable>();
        for (int i = 0; i < ALL; i++) {
            View view = getLayoutInflater().inflate(R.layout.vp, null);
            mViewLists.add(view);
            if (i % 2 == 0) {
                mDrawableLists.add(getResources().getDrawable(R.drawable.bg_img1));
            } else {
                mDrawableLists.add(getResources().getDrawable(R.drawable.bg_img2));
            }
        }
    }


    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViewLists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(mViewLists.get(position));
            //super.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = mViewLists.get(position);
            TextView textView = (TextView) v.findViewById(R.id.txt);
            textView.setText("第" + position + "个");
            ((ViewPager) container).addView(v);
            return v;
        }
    }
}
