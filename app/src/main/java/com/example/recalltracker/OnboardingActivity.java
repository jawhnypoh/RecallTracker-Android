package com.example.recalltracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.gc.materialdesign.views.ButtonFlat;
import com.ogaclejapan.smarttablayout.SmartTabLayout;


public class OnboardingActivity extends FragmentActivity {

    private ViewPager pager;
    private SmartTabLayout indicator;
    private Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_onboarding);

        pager = (ViewPager) findViewById(R.id.pager);
        indicator = (SmartTabLayout) findViewById(R.id.indicator);
        skip = (Button) findViewById(R.id.skip);

        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new OnboardingFragment1();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                // returns number of screens
                return 1;
            }
        };

        pager.setAdapter(adapter);
        indicator.setViewPager(pager);
        indicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    skip.setVisibility(View.VISIBLE);
                }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishOnboarding();
            }
        });
    }

    private void finishOnboarding() {
        SharedPreferences preferences =
                getSharedPreferences("my_preferences", MODE_PRIVATE);

        preferences.edit()
                .putBoolean("onboarding_complete",true).apply();

        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);

        finish();
    }
}

