package com.darkhorse.louissankey.darkhorse;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity implements
        LiveContestsFragment.OnFragmentInteractionListener,
        RecentContestsFragment.OnFragmentInteractionListener,
        UpcomingContestsFragment.OnFragmentInteractionListener,
        ViewPagerHolderFragment.OnFragmentInteractionListener,
        NBAFragment.OnFragmentInteractionListener,
        MLBFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView username = (TextView) findViewById(R.id.activity_main_username);
        username.setPaintFlags(username.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_play_circle_outline_black_24dp, "Live"))
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "Home"))
                .addItem(new BottomNavigationItem(R.drawable.ic_schedule_black_24dp, "Upcoming"))
                .addItem(new BottomNavigationItem(R.drawable.ic_assignment_black_24dp, "Recent"))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor(R.color.primaryOrange)
                .setInActiveColor("#FFFFFF")
                .setBarBackgroundColor(R.color.secondaryBackgroundColor)
                .setFirstSelectedPosition(1)
                .initialise();

        ViewPagerHolderFragment viewPagerHolderFragment = new ViewPagerHolderFragment();
        openFragment(viewPagerHolderFragment);


        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {

                switch (position){
                    case 0:
                        LiveContestsFragment liveContestsFragment = new LiveContestsFragment();
                        openFragment(liveContestsFragment);
                        break;
                    case 1:
                        ViewPagerHolderFragment viewPagerHolderFragment = new ViewPagerHolderFragment();
                        openFragment(viewPagerHolderFragment);
                        break;
                    case 2:
                        UpcomingContestsFragment upcomingContestsFragment = new UpcomingContestsFragment();
                        openFragment(upcomingContestsFragment);
                        break;
                    case 3:
                        RecentContestsFragment recentContestsFragment = new RecentContestsFragment();
                        openFragment(recentContestsFragment);
                        break;
                    default:
                        break;
                }

            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void openFragment(final Fragment fragment)   {
        androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.viewpager_framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
