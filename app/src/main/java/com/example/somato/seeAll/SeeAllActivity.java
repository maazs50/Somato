package com.example.somato.seeAll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.somato.R;
import com.example.somato.Restaurant.view.RetaurantDetailsActivity;
import com.example.somato.dashboard.adapters.HomeAdapter;
import com.example.somato.dashboard.model.Restaurant_;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SeeAllActivity extends AppCompatActivity implements HomeAdapter.HonClickListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView mToolbarTitle;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);
        tabLayout = findViewById(R.id.see_all_tablayout);
        viewPager =findViewById(R.id.viewpager);
        mToolbarTitle = findViewById(R.id.tv_toolbartitle);
        addTabs(viewPager);
        mToolbarTitle.setText("All Restaurants");
        tabLayout.setupWithViewPager(viewPager);

    }
    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DeliveryFragment(), "Delivery");
        adapter.addFrag(new DineOutFragment(), "Dine-out");
        adapter.addFrag(new CafesFragment(), "Cafes");
        adapter.addFrag(new PubsFragment(), "Pubs & Bars");
        adapter.addFrag(new ClubsFragment(), "Clubs & Lounges");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void getRestaurantId(Restaurant_ restaurant) {
        Intent goToDetails = new Intent(this, RetaurantDetailsActivity.class);
        goToDetails.putExtra("res_id", restaurant.getId());
        goToDetails.putExtra("res_name", restaurant.getName());
        goToDetails.putExtra("res_address", restaurant.getLocation().getAddress());
        goToDetails.putExtra("thumb", restaurant.getThumb());
        goToDetails.putExtra("rating_point", restaurant.getUserRating().getAggregateRating());
        startActivity(goToDetails);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}