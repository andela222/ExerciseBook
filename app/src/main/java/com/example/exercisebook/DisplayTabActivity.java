package com.example.exercisebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;



public class DisplayTabActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private TabLayout tabLayout;

    private long userId;

    public Fragment currentFragment;
    public static final String EXTRA_DAY_TYPE = "com.example.exercisebook.EXTRA_DAY_TYPE";
    public static final int NUMBER_OF_TABS = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_display_layout);

        Intent intent = getIntent();
        if(intent.hasExtra(AddEditUserActivity.EXTRA_USER_ID)){
            userId = intent.getLongExtra(AddEditUserActivity.EXTRA_USER_ID,-1);

            if (userId != -1) {

                viewPager = findViewById(R.id.pager);
                pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), NUMBER_OF_TABS, userId);

                viewPager.setAdapter(pagerAdapter);

                tabLayout = findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


            }
        }
        //when activity is first created current fragment is ExerciseDayFragment
        currentFragment = new ExerciseDayFragment();
        //listener for tab changing
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentFragment = pagerAdapter.getRegisteredFragment(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        FloatingActionButton addDayButton = findViewById(R.id.buttonAddDay);
        addDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DisplayTabActivity.this, PickDate.class);
                intent.putExtra(AddEditUserActivity.EXTRA_USER_ID, userId);

                if (currentFragment instanceof ExerciseDayFragment) {
                    intent.putExtra(EXTRA_DAY_TYPE,"addExerciseDay");
                    startActivity(intent);
                } else if (currentFragment instanceof MeasurementDayFragment) {
                    intent.putExtra(EXTRA_DAY_TYPE,"addMeasurementDay");
                    startActivity(intent);
                }
            }
        });

        FloatingActionButton backBtn = findViewById(R.id.buttonBackToUsers);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayTabActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
