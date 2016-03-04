package com.example.enzo.practicedemos.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.enzo.practicedemos.Animations.DepthPageTransformer;
import com.example.enzo.practicedemos.Fragments.SwipeViewFragment;
import com.example.enzo.practicedemos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DrawerPagerActivity extends AppCompatActivity
{

    private String[] mPlanetTitles = {"AAAA", "BBBB", "CCCC", "DDDD", "EEEE"};
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_pager);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
//        CustomBaseAdapter adapter = new CustomBaseAdapter(this, getData(), R.layout
//                .item_listview_base_adapter, new String[]{"title", "content", "points"}, new
//                int[]{R.id.title, R.id.content, R.id.points});

        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_drawr_list,
                mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setPageTransformer(true, new DepthPageTransformer());
        mPagerAdapter = new SwipeViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    private class SwipeViewPagerAdapter extends FragmentStatePagerAdapter
    {
        public SwipeViewPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            return new SwipeViewFragment();
        }

        @Override
        public int getCount()
        {
            return NUM_PAGES;
        }
    }

    public ArrayList<Map<String, Object>> getData()
    {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("title", "user " + i);
            listItem.put("content", "this is content " + i);
            listItem.put("points", "points" + i);
            list.add(listItem);
        }
        return list;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            selectItem(position);
        }
    }

    private void selectItem(int position)
    {
//        Fragment fragment = new PlanetFragment();
//        Bundle args = new Bundle();
//        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
//        fragment.setArguments(args);
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
//
//        mDrawerList.setItemChecked(position, true);
//
//        mDrawerLayout.closeDrawer(mDrawerList);

        Toast.makeText(this, "position clicked:" + position, Toast.LENGTH_SHORT).show();
    }
}
