package in.co.techm.activities;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import in.co.techm.extras.SortListener;
import in.co.techm.fragments.FragmentDrawer;
import in.co.techm.fragments.FragmentLikes;
import in.co.techm.fragments.FragmentStackoverflowQuestion;
import in.co.techm.logging.L;
import in.co.techm.pharmeasy.R;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import me.tatarka.support.job.JobScheduler;


public class ActivityMain extends AppCompatActivity implements MaterialTabListener, View.OnClickListener {

    //tab containing list of questions
    public static final int TAB_QUESTION_LIST = 0;
    //tab to list likes
    public static final int TAB_LIKES = 1;

    //int corresponding to the number of tabs in our Activity
    public static final int TAB_COUNT = 2;
    //int corresponding to the id of our JobSchedulerService
    private static final int JOB_ID = 100;
    //tag associated with the FAB menu button that sorts by creation date
    private static final String TAG_SORT_CREATION_DATE = "sortCD";
    //tag associated with the FAB menu button that sorts by votes
    private static final String TAG_SORT_VOTES = "sortVotes";
    //tag associated with the FAB menu button that sorts by ciew count
    private static final String TAG_SORT_VIEW_COUNT = "sortViewCount";
    //Run the JobSchedulerService every 2 minutes
    private static final long POLL_FREQUENCY = 28800000;
    private JobScheduler mJobScheduler;
    private Toolbar mToolbar;
    //a layout grouping the toolbar and the tabs together
    private ViewGroup mContainerToolbar;
    private MaterialTabHost mTabHost;
    private ViewPager mPager;
    private ViewPagerAdapter mAdapter;
    private FloatingActionButton mFAB;
    private FloatingActionMenu mFABMenu;
    private FragmentDrawer mDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFAB();
        setupTabs();
//        setupJob();
        setupDrawer();
        //animate the Toolbar when it comes into the picture
//        AnimationUtils.animateToolbarDroppingDown(mContainerToolbar);

    }

    private void setupDrawer() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        mContainerToolbar = (ViewGroup) findViewById(R.id.container_app_bar);
        //set the Toolbar as ActionBar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //setup the NavigationDrawer
        mDrawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
    }


    public void onDrawerItemClicked(int index) {
        mPager.setCurrentItem(index);
    }

    public View getContainerToolbar() {
        return mContainerToolbar;
    }

    private void setupTabs() {
        mTabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        mPager = (ViewPager) findViewById(R.id.viewPager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        //when the page changes in the ViewPager, update the Tabs accordingly
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTabHost.setSelectedNavigationItem(position);

            }
        });
        //Add all the Tabs to the TabHost
        for (int i = 0; i < mAdapter.getCount(); i++) {
            mTabHost.addTab(
                    mTabHost.newTab()
                            .setText(mAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    private void setupJob() {
        mJobScheduler = JobScheduler.getInstance(this);
        //set an initial delay with a Handler so that the data loading by the JobScheduler does not clash with the loading inside the Fragment
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //schedule the job after the delay has been elapsed
//                buildJob();
            }
        }, 30000);
    }

//    private void buildJob() {
//        //attach the job ID and the name of the Service that will work in the background
//        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, new ComponentName(this, ServiceMoviesBoxOffice.class));
//        //set periodic polling that needs net connection and works across device reboots
//        builder.setPeriodic(POLL_FREQUENCY)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                .setPersisted(true);
//        mJobScheduler.schedule(builder.build());
//    }

    private void setupFAB() {
        //define the icon for the main floating action button
        ImageView iconFAB = new ImageView(this);
        iconFAB.setImageResource(R.drawable.ic_action_new);

        //set the appropriate background for the main floating action button along with its icon
        mFAB = new FloatingActionButton.Builder(this)
                .setContentView(iconFAB)
                .setBackgroundDrawable(R.drawable.selector_button_red)
                .build();

        //define the icons for the sub action buttons
        ImageView iconSortViewCount = new ImageView(this);
        iconSortViewCount.setImageResource(R.drawable.ic_arrow_drop_up_24dp);
        ImageView iconSortCreationDate = new ImageView(this);
        iconSortCreationDate.setImageResource(R.drawable.ic_action_calendar);
        ImageView iconSortVotes = new ImageView(this);
        iconSortVotes.setImageResource(R.drawable.ic_action_important);

        //set the background for all the sub buttons
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_sub_button_gray));


        //build the sub buttons
        SubActionButton buttonSortViewCount = itemBuilder.setContentView(iconSortViewCount).build();
        SubActionButton buttonSortCreationDate = itemBuilder.setContentView(iconSortCreationDate).build();
        SubActionButton buttonSortVotes = itemBuilder.setContentView(iconSortVotes).build();

        //to determine which button was clicked, set Tags on each button
        buttonSortViewCount.setTag(TAG_SORT_VIEW_COUNT);
        buttonSortCreationDate.setTag(TAG_SORT_CREATION_DATE);
        buttonSortVotes.setTag(TAG_SORT_VOTES);

        buttonSortViewCount.setOnClickListener(this);
        buttonSortCreationDate.setOnClickListener(this);
        buttonSortVotes.setOnClickListener(this);

        //add the sub buttons to the main floating action button
        mFABMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonSortCreationDate)
                .addSubActionView(buttonSortVotes)
                .addSubActionView(buttonSortViewCount)
                .attachTo(mFAB)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present. 
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        // searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLIKCED", "clikced");
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will 
        // automatically handle clicks on the Home/Up button, so long 
        // as you specify a parent activity in AndroidManifest.xml. 
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement 
        if (id == R.id.action_search) {
            L.m("action_search yyyyyyyyyyyyyyyyyyy");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(MaterialTab materialTab) {
        //when a Tab is selected, update the ViewPager to reflect the changes
        mPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {
    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {
    }

    @Override
    public void onClick(View v) {
        //call instantiate item since getItem may return null depending on whether the PagerAdapter is of type FragmentPagerAdapter or FragmentStatePagerAdapter
        Fragment fragment = (Fragment) mAdapter.instantiateItem(mPager, mPager.getCurrentItem());
        if (fragment instanceof SortListener) {

            if (v.getTag().equals(TAG_SORT_CREATION_DATE)) {
                //call the sort by view count method on any Fragment that implements sortlistener
                ((SortListener) fragment).sortByViewCount();
            }
            if (v.getTag().equals(TAG_SORT_VOTES)) {
                //call the sort by creation date method on any Fragment that implements sortlistener
                ((SortListener) fragment).onSortByCreationDate();
            }
            if (v.getTag().equals(TAG_SORT_VIEW_COUNT)) {
                //call the sort by ratings method on any Fragment that implements sortlistener
                ((SortListener) fragment).onSortByVotes();
            }
        }

    }


    private void toggleTranslateFAB(float slideOffset) {
        if (mFABMenu != null) {
            if (mFABMenu.isOpen()) {
                mFABMenu.close(true);
            }
            mFAB.setTranslationX(slideOffset * 200);
        }
    }

    public void onDrawerSlide(float slideOffset) {
        toggleTranslateFAB(slideOffset);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        FragmentManager fragmentManager;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentManager = fm;
        }

        public Fragment getItem(int num) {
            Fragment fragment = null;
//            L.m("getItem called for " + num);
            switch (num) {
                case TAB_QUESTION_LIST:
                    fragment = FragmentStackoverflowQuestion.newInstance("", "");
                    break;
                case TAB_LIKES:
                    fragment = FragmentLikes.newInstance("", "");
                    break;
            }
            return fragment;

        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.drawer_tabs)[position];
        }

    }
} 