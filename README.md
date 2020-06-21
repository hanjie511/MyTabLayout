# MyTabLayout
用TabLayout+ViewPager实现頂部和底部导航，底部导航可以设置数字角标  
# TabLayout介绍
* 继承类：android.widget.HorizontalScrollView  
* 所在包：android.support.design.widget  

Tablayout继承自HorizontalScrollView，用作页面切换指示器，因使用简便功能强大而广泛使用在App中。在项目中，它通常与ViewPager+Fragment配合来实现项目的界面导航。  
# 使用TabLayout的默认样式
* 布局文件  
```java  

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">
    <com.google.android.material.tabs.TabLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tabMode="scrollable"
        android:id="@+id/myTabLayout"
        app:tabSelectedTextColor="@color/colorAccent"
        />
    <androidx.viewpager.widget.ViewPager
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/myViewPager"
        />
</LinearLayout>  

```  

* Activity代码  

```java  

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
        for(int i=0;i<titles.length;i++){//添加要展示的Fragment和TabItem
            Fragment1 fragment1=new Fragment1();
            Bundle bundle=new Bundle();
            bundle.putString("content",titles[i]);
            fragment1.setArguments(bundle);
            fragments.add(fragment1);
            myTabLayout.addTab(myTabLayout.newTab());
        }
        myTabLayout.setupWithViewPager(myViewPager,false);//将TabLayout和ViewPager绑定在一起
        FragmentPagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {//初始化ViewPager的数据适配器
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        myViewPager.setAdapter(adapter);//为ViewPager设置适配器
        for(int i=0;i<titles.length;i++){//为TabLayout的每个TabItem设置要显示的文字
            myTabLayout.getTabAt(i).setText(titles[i]);
        }
    }
}  

```  

## 效果
![展示效果](https://github.com/hanjie511/MyTabLayout/blob/master/MyVideo_1.gif)
# 自定义TabLayout样式（包含数字角标）
* 自定义TabItem的布局文件  

```java  

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:gravity="center"
    android:layout_height="match_parent">

    <FrameLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        >
        <ImageView
            android:id="@+id/image_tab_item"
            android:layout_width="36dp"
            android:background="@drawable/image_selector"
            android:layout_height="36dp" />

        <TextView
            android:id="@+id/badge_tab_item"
            android:layout_width="16dp"
            android:layout_height="16dp"
            android:layout_gravity="right|top"
            android:background="@drawable/iten_shape"
            android:text="99"
            android:textSize="12sp"
            android:textColor="@android:color/white" />
    </FrameLayout>
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:textColor="@color/text_selector"
        android:id="@+id/title_tab_item"
        />
</LinearLayout>  

```

注意：要对上面的布局文件中的ImageView和TextView分别设置选择状态改变的selector.  

* 自定义样式的Activity代码  

```java  

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
        for(int i=0;i<titles.length;i++){//添加要展示的Fragment和TabItem,Fragment和TabItem的数量要保持一致
            Fragment1 fragment1=new Fragment1();
            Bundle bundle=new Bundle();
            bundle.putString("content",titles[i]);
            fragment1.setArguments(bundle);
            fragments.add(fragment1);
            secondTabLayout.addTab(secondTabLayout.newTab());
        }
        secondTabLayout.setupWithViewPager(secondViewPager,false);//将TabLayout和ViewPager绑定在一起
        FragmentPagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {//初始化ViewPager的数据适配器
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        secondViewPager.setAdapter(adapter);//为ViewPager设置适配器
        for (int i = 0; i < adapter.getCount(); i++) {//为每个TabItem设置自定义布局的样式
            TabLayout.Tab tab = secondTabLayout.getTabAt(i);//获得每一个tab
            tab.setCustomView(R.layout.tab_item);//给每一个tab设置view
            TextView textView = tab.getCustomView().findViewById(R.id.title_tab_item);
            ImageView imageView=tab.getCustomView().findViewById(R.id.image_tab_item);
            textView.setText(titles[i]);//设置tab上的文字
            if (i == 0) {
                // 设置第一个tab的ImageView是被选择的样式
                tab.getCustomView().findViewById(R.id.image_tab_item).setSelected(true);
                // 设置第一个tab的TextView是被选择的样式
                tab.getCustomView().findViewById(R.id.title_tab_item).setSelected(true);
            }
        }
        secondTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {//设置TabItem切换和点击监听，其主要作用是改变TabItem的状态
            @Override
            public void onTabSelected(TabLayout.Tab tab) {//为选中的TabItem设置选中的状态
                tab.getCustomView().findViewById(R.id.image_tab_item).setSelected(true);
                tab.getCustomView().findViewById(R.id.title_tab_item).setSelected(true);
                secondViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {//为没有选中的TabItem设置未选中的状态
                tab.getCustomView().findViewById(R.id.image_tab_item).setSelected(false);
                tab.getCustomView().findViewById(R.id.title_tab_item).setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
```  

## 展示效果
![展示效果](https://github.com/hanjie511/MyTabLayout/blob/master/MyVideo_2.gif)



