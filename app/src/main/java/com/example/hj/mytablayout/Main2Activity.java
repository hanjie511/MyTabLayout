package com.example.hj.mytablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private ViewPager secondViewPager;
    private TabLayout secondTabLayout;
    private String [] titles=new String[]{"item1","item2","item3","item4","item5"};
    private ArrayList<Fragment> fragments=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }


    private void initView(){
        secondViewPager=findViewById(R.id.secondViewPager);
        secondTabLayout=findViewById(R.id.secondTabLayout);
        for(int i=0;i<titles.length;i++){
            Fragment1 fragment1=new Fragment1();
            Bundle bundle=new Bundle();
            bundle.putString("content",titles[i]);
            fragment1.setArguments(bundle);
            fragments.add(fragment1);
            secondTabLayout.addTab(secondTabLayout.newTab());
        }
        secondTabLayout.setupWithViewPager(secondViewPager,false);
        FragmentPagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        secondViewPager.setAdapter(adapter);
        for (int i = 0; i < adapter.getCount(); i++) {
            TabLayout.Tab tab = secondTabLayout.getTabAt(i);//获得每一个tab
            tab.setCustomView(R.layout.tab_item);//给每一个tab设置view
            TextView textView = tab.getCustomView().findViewById(R.id.title_tab_item);
            ImageView imageView=tab.getCustomView().findViewById(R.id.image_tab_item);
            textView.setText(titles[i]);//设置tab上的文字
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                tab.getCustomView().findViewById(R.id.image_tab_item).setSelected(true);//第一个tab被选中
                tab.getCustomView().findViewById(R.id.title_tab_item).setSelected(true);//第一个tab被选中
            }
        }
        secondTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.image_tab_item).setSelected(true);//第一个tab被选中
                tab.getCustomView().findViewById(R.id.title_tab_item).setSelected(true);//第一个tab被选中
                secondViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.image_tab_item).setSelected(false);//第一个tab被选中
                tab.getCustomView().findViewById(R.id.title_tab_item).setSelected(false);//第一个tab被选中
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
