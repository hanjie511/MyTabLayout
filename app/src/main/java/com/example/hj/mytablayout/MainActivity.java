package com.example.hj.mytablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout myTabLayout;
    private ViewPager myViewPager;
    private String [] titles=new String[]{"直播","推荐","热门","追番","影视","说唱区","知识区"};
    private ArrayList<Fragment> fragments=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        myTabLayout=findViewById(R.id.myTabLayout);
        myViewPager=findViewById(R.id.myViewPager);
        for(int i=0;i<titles.length;i++){
            Fragment1 fragment1=new Fragment1();
            Bundle bundle=new Bundle();
            bundle.putString("content",titles[i]);
            fragment1.setArguments(bundle);
            fragments.add(fragment1);
            myTabLayout.addTab(myTabLayout.newTab());
        }
        myTabLayout.setupWithViewPager(myViewPager,false);
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
        myViewPager.setAdapter(adapter);
        for(int i=0;i<titles.length;i++){
            myTabLayout.getTabAt(i).setText(titles[i]);
        }
    }
}
