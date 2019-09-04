package com.example.navdrawerold;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.navdrawerold.ui.gallery.GalleryFragment;
import com.example.navdrawerold.ui.home.HomeFragment;
import com.example.navdrawerold.ui.slideshow.SlideshowFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    ActionBarDrawerToggle mDrawerToggle;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

//        final ActionBar actionBar = getSupportActionBar();
//
//        if (actionBar != null)
//        {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, 1, 1)
//            {
//
//                public void onDrawerClosed(View view)
//                {
//                    supportInvalidateOptionsMenu();
//                    //drawerOpened = false;
//                }
//
//                public void onDrawerOpened(View drawerView)
//                {
//                    supportInvalidateOptionsMenu();
//                    //drawerOpened = true;
//                }
//            };
//            mDrawerToggle.setDrawerIndicatorEnabled(true);
//            drawer.setDrawerListener(mDrawerToggle);
//            mDrawerToggle.syncState();
//        }

        toolbar.setTitle("");
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        //change the hamburger icon
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_menu_slideshow, null);
                toolbar.setNavigationIcon(d);
            }
        });

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_gallery, R.id.nav_send, R.id.nav_share,
                R.id.nav_slideshow, R.id.nav_tools,
                R.id.nav_slidesho, R.id.nav_tool)
                .setDrawerLayout(drawer)
                .build();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.container_layout, new HomeFragment());
                        ft.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_gallery:
                        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                        ft2.replace(R.id.container_layout, new GalleryFragment());
                        ft2.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_slideshow:
                        FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                        ft3.replace(R.id.container_layout, new SlideshowFragment());
                        ft3.commit();
                        drawer.closeDrawers();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.container_layout);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}
