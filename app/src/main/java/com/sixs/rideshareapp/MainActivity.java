package com.sixs.rideshareapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sixs.rideshareapp.Fragment.HomeFragment;
import com.sixs.rideshareapp.Fragment.MyRatingFragment;
import com.sixs.rideshareapp.Fragment.MyScoreFragment;
import com.sixs.rideshareapp.Fragment.MyTripsFragment;
import com.sixs.rideshareapp.Fragment.MyVehiclesFragment;
import com.sixs.rideshareapp.Fragment.PreferredDestinationFragment;
import com.sixs.rideshareapp.Fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    HomeFragment homeFragment;
    PreferredDestinationFragment preferredDestinationFragment;
    MyTripsFragment myTripsFragment;
    MyVehiclesFragment myVehiclesFragment;
    MyRatingFragment myRatingFragment;
    MyScoreFragment myScoreFragment;
    SettingsFragment settingsFragment;

    FragmentManager fragmentManager;

    int currentPosition;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();

        homeFragment = new HomeFragment();
        myVehiclesFragment = new MyVehiclesFragment();
        preferredDestinationFragment = new PreferredDestinationFragment();
        myTripsFragment = new MyTripsFragment();
        myRatingFragment = new MyRatingFragment();
        myScoreFragment = new MyScoreFragment();
        settingsFragment  = new SettingsFragment();
    }

    public void initNavigationDrawer() {
        fragmentManager = getSupportFragmentManager();

        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                Log.v("TTT", "menuItem.getItemId() = " + menuItem.getItemId());

                switch (id){
                    case R.id.home:
                        toolbar.setTitle(getString(R.string.home));

                        if (homeFragment != null) {

                            fragmentManager.beginTransaction().setCustomAnimations(R.anim.activity_exit,R.anim.activity_enter).replace(R.id.frame_container, homeFragment).commit();

                            drawerLayout.closeDrawers();
                        } else {
                            // error in creating fragment
                            Log.e("MainActivity", "Error in creating fragment");
                        }
                        currentPosition = 0;
                        break;
                    case R.id.my_vehicles:
                        toolbar.setTitle(getString(R.string.my_vehicles));

                        if (myVehiclesFragment != null) {
                            fragmentManager.beginTransaction().setCustomAnimations(R.anim.activity_exit,R.anim.activity_enter).replace(R.id.frame_container, myVehiclesFragment).commit();
                            drawerLayout.closeDrawers();
                        } else {
                            // error in creating fragment
                            Log.e("MainActivity", "Error in creating fragment");
                        }
                        currentPosition++;
                        break;
                    case R.id.preferred_destinations:
                        toolbar.setTitle(getString(R.string.preferred_destinations));

                        if (preferredDestinationFragment != null) {
                            fragmentManager.beginTransaction().setCustomAnimations(R.anim.activity_exit,R.anim.activity_enter).replace(R.id.frame_container, preferredDestinationFragment).commit();
                            drawerLayout.closeDrawers();
                        } else {
                            // error in creating fragment
                            Log.e("MainActivity", "Error in creating fragment");
                        }
                        currentPosition++;
                        break;

//                    case R.id.custom_route:
//                        toolbar.setTitle("Custom Route");
//                        customRouteFragment = new CustomRouteFragment();
//                        if (customRouteFragment != null) {
//                            MainActivity.this.overridePendingTransition(R.anim.activity_enter,R.anim.activity_exit);
//                            fragmentManager.beginTransaction().replace(R.id.frame_container, customRouteFragment).commit();
//                            drawerLayout.closeDrawers();
//                        } else {
//                            // error in creating fragment
//                            Log.e("MainActivity", "Error in creating fragment");
//                        }
//                        currentPosition++;
//                        break;
                    case R.id.my_trips:
                        toolbar.setTitle(getString(R.string.my_trips));

                        if (myTripsFragment != null) {
                            fragmentManager.beginTransaction().setCustomAnimations(R.anim.activity_exit,R.anim.activity_enter).replace(R.id.frame_container, myTripsFragment).commit();
                            drawerLayout.closeDrawers();
                        } else {
                            // error in creating fragment
                            Log.e("MainActivity", "Error in creating fragment");
                        }
                        currentPosition++;
                        break;
//                    case R.id.my_accounts:
//                        toolbar.setTitle("My Accounts");
//                        myAccountFragment = new MyAccountFragment();
//                        if (myAccountFragment != null) {
//                            MainActivity.this.overridePendingTransition(R.anim.activity_enter,R.anim.activity_exit);
//                            fragmentManager.beginTransaction().replace(R.id.frame_container, myAccountFragment).commit();
//                            drawerLayout.closeDrawers();
//                        } else {
//                            // error in creating fragment
//                            Log.e("MainActivity", "Error in creating fragment");
//                        }
//                        currentPosition++;
//                        break;

                    case R.id.my_rating:
                        toolbar.setTitle(getString(R.string.my_rating));

                        if (myRatingFragment != null) {
                            fragmentManager.beginTransaction().setCustomAnimations(R.anim.activity_exit,R.anim.activity_enter).replace(R.id.frame_container, myRatingFragment).commit();
                            drawerLayout.closeDrawers();
                        } else {
                            // error in creating fragment
                            Log.e("MainActivity", "Error in creating fragment");
                        }
                        currentPosition++;
                        break;

                    case R.id.my_score:
                        toolbar.setTitle(getString(R.string.my_scrore));

                        if (myScoreFragment != null) {
                            fragmentManager.beginTransaction().setCustomAnimations(R.anim.activity_exit,R.anim.activity_enter).replace(R.id.frame_container, myScoreFragment).commit();
                            drawerLayout.closeDrawers();
                        } else {
                            // error in creating fragment
                            Log.e("MainActivity", "Error in creating fragment");
                        }
                        currentPosition++;
                        break;

                    case R.id.settings:
                        toolbar.setTitle(getString(R.string.settings));

                        if (settingsFragment != null) {
                            fragmentManager.beginTransaction().setCustomAnimations(R.anim.activity_exit,R.anim.activity_enter).replace(R.id.frame_container, settingsFragment).commit();
                            drawerLayout.closeDrawers();
                        } else {
                            // error in creating fragment
                            Log.e("MainActivity", "Error in creating fragment");
                        }
                        currentPosition++;
                        break;
//                    case R.id.logout:
//                        finish();
                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
        TextView tv_company_name = (TextView)header.findViewById(R.id.tv_company_name);
        tv_company_name.setText("6S Solutions Pvt. Ltd.");
        tv_email.setText("Rashmi Narola");
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };


        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        drawerLayout.closeDrawers();
        actionBarDrawerToggle.syncState();

        navigationView.getMenu().getItem(0).setChecked(true);
        homeFragment = new HomeFragment();
        if (homeFragment != null) {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.activity_exit,R.anim.activity_enter).replace(R.id.frame_container, homeFragment).commit();
//            drawerLayout.closeDrawers();
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
        toolbar.setTitle(getString(R.string.home));
    }

    @Override
    public void onBackPressed() {
        if(currentPosition == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
            builder.setTitle("Exit");
            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            System.exit(0);
//                        android.os.Process.killProcess(android.os.Process.myPid());
                            MainActivity.this.finish();
                            moveTaskToBack(true);


                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

            Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
            nbutton.setTextColor(getResources().getColor(R.color.black_light));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(getResources().getColor(R.color.blue_light));
//            navigationView.getsetCheckedItem(1);
        }
        else {
            currentPosition = 0;
            navigationView.getMenu().getItem(0).setChecked(true);
            toolbar.setTitle(getString(R.string.home));
            if (homeFragment != null) {
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.activity_exit,R.anim.activity_enter).replace(R.id.frame_container, homeFragment).commit();
                drawerLayout.closeDrawers();
            } else {
                // error in creating fragment
                Log.e("MainActivity", "Error in creating fragment");
            }
        }
    }

    @Override
    protected void onDestroy() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }

    //        super.onBackPressed();
    }

    //    /**
//     * Diplaying fragment view for selected nav drawer list item
//     * */
//    private void displayView(int position) {
//        // update the main content by replacing fragments
//        Fragment fragment = null;
//        switch (position) {
//            case 0:
//                fragment = new HomeFragment();
//                break;
//            case 1:
////                fragment = new HomeFragment();
//                break;
//            case 2:
////                fragment = new HomeFragment();
//                break;
//            case 3:
////                fragment = new HomeFragment();
//                break;
//            case 4:
////                fragment = new HomeFragment();
//                break;
//            case 5:
////                fragment = new HomeFragment();
//                break;
//
//            default:
//                break;
//        }
//
//        if (fragment != null) {
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.frame_container, fragment).commit();
//
//            // update selected item and title, then close the drawer
////            mDrawerList.setItemChecked(position, true);
////            mDrawerList.setSelection(position);
//            setTitle(navMenuTitles[position]);
//            mDrawerLayout.closeDrawer(mDrawerList);
//        } else {
//            // error in creating fragment
//            Log.e("MainActivity", "Error in creating fragment");
//        }
//    }


//    private DrawerLayout mDrawerLayout;
//    private ListView mDrawerList;
//    private ActionBarDrawerToggle mDrawerToggle;
//
//    // nav drawer title
//    private CharSequence mDrawerTitle;
//
//    // used to store app title
//    private CharSequence mTitle;
//
//    // slide menu items
//    private String[] navMenuTitles;
//    private TypedArray navMenuIcons;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mTitle = mDrawerTitle = getTitle();
//
//        // load slide menu items
//        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
//
//        // nav drawer icons from resources
//        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
//
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
//        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
//
//        navDrawerItems = new ArrayList<NavDrawerItem>();
//
//        // adding nav drawer items to array
//        // Home
//        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
//        // Find People
//        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
//        // Photos
//        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
//        // Communities, Will add a counter here
//        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
//        // Pages
//        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
//        // What's hot, We  will add a counter here
//        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
//
//        // Recycle the typed array
//        navMenuIcons.recycle();
//
//        // setting the nav drawer list adapter
//        mDrawerList.setAdapter(adapter);
//
//        // enabling action bar app icon and behaving it as toggle button
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
//                R.drawable.ic_drawer, //nav menu toggle icon
//                R.string.app_name, // nav drawer open - description for accessibility
//                R.string.app_name // nav drawer close - description for accessibility
//        ){
//            public void onDrawerClosed(View view) {
//                getActionBar().setTitle(mTitle);
//                // calling onPrepareOptionsMenu() to show action bar icons
////                invalidateOptionsMenu();
//            }
//
//            public void onDrawerOpened(View drawerView) {
//                getActionBar().setTitle(mDrawerTitle);
//                // calling onPrepareOptionsMenu() to hide action bar icons
////                invalidateOptionsMenu();
//            }
//        };
//
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//        if (savedInstanceState == null) {
//            // on first time display view for first nav item
//            displayView(0);
//        }
//    }
//
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.main, menu);
////        return true;
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        // toggle nav drawer on selecting action bar app icon/title
////        if (mDrawerToggle.onOptionsItemSelected(item)) {
////            return true;
////        }
////        // Handle action bar actions click
////        switch (item.getItemId()) {
////            case R.id.action_settings:
////                return true;
////            default:
////                return super.onOptionsItemSelected(item);
////        }
////    }
////
////    /***
////     * Called when invalidateOptionsMenu() is triggered
////     */
////    @Override
////    public boolean onPrepareOptionsMenu(Menu menu) {
////        // if nav drawer is opened, hide the action items
////        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
////        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
////        return super.onPrepareOptionsMenu(menu);
////    }
//
//    @Override
//    public void setTitle(CharSequence title) {
//        mTitle = title;
//        getSupportActionBar().setTitle(mTitle);
//    }
//
//    /**
//     * When using the ActionBarDrawerToggle, you must call it during
//     * onPostCreate() and onConfigurationChanged()...
//     */
//
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        // Sync the toggle state after onRestoreInstanceState has occurred.
//        mDrawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        // Pass any configuration change to the drawer toggls
//        mDrawerToggle.onConfigurationChanged(newConfig);
//    }
//
//    /**
//     * Slide menu item click listener
//     * */
//    private class SlideMenuClickListener implements
//            ListView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position,
//                                long id) {
//            // display view for selected nav drawer item
//            displayView(position);
//        }
//    }
//

//}
