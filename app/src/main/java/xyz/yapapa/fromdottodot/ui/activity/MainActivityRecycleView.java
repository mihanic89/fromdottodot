package xyz.yapapa.fromdottodot.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import xyz.yapapa.fromdottodot.R;

import static xyz.yapapa.fromdottodot.R.id.adView;
import static xyz.yapapa.fromdottodot.R.id.adViewRecycle;

/**
 * A simple activity showing the use of {@link NativeExpressAdView} ads in
 * a {@link RecyclerView} widget.
 * <p>The {@link RecyclerView} widget is a more advanced and flexible version of
 * ListView. This widget helps simplify the display and handling of large data sets
 * by allowing the layout manager to determine when to reuse (recycle) item views that
 * are no longer visible to the user. Recycling views improves performance by avoiding
 * the creation of unnecessary views or performing expensive findViewByID() lookups.</p>
 */
public class MainActivityRecycleView extends AppCompatActivity {

    private AdView mAdView;
    private int recycleGrids=2;

    // The RecyclerView that holds and displays Native Express ads and menu items.
    private RecyclerView mRecyclerView;

    // List of Native Express ads and MenuItems that populate the RecyclerView.
    private List<Object> mRecyclerViewItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycle);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView.


        // Specify a linear layout manager.

        int screenSize = getResources().getConfiguration().screenLayout &
        		Configuration.SCREENLAYOUT_SIZE_MASK;
        if (screenSize==Configuration.SCREENLAYOUT_SIZE_XLARGE) recycleGrids=4;

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, recycleGrids);
        final  RecyclerView.Adapter adapter = new RecyclerViewAdapter(this, mRecyclerViewItems);



        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        // Update the RecyclerView item's list with menu items and Native Express ads.
        addMenuItems();


        // Specify an adapter.

        mRecyclerView.setAdapter(adapter);
        Bundle extras = new Bundle();
        extras.putBoolean("is_designed_for_families", true);
        mAdView = (AdView) findViewById(adViewRecycle);
        AdRequest adRequest = new AdRequest.Builder()
               // .tagForChildDirectedTreatment(true)
                //.addNetworkExtrasBundle(AdMobAdapter.class, extras)
                //.addTestDevice("09D7B5315C60A80D280B8CDF618FD3DE")
                .build();
        mAdView.loadAd(adRequest);
    }

    /**
     * Adds Native Express ads to the items list.
     */






    /**
     * Adds {@link MenuItem}'s from a JSON file.
     */
    private void addMenuItems() {



        mRecyclerViewItems.add(new MenuItem(R.mipmap.d01));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d01_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d02));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d02_1));

        mRecyclerViewItems.add(new MenuItem(R.mipmap.b01));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b01_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b02));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b02_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b03));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b03_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b04));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b04_1));

        mRecyclerViewItems.add(new MenuItem(R.mipmap.p01));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p01_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p02));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p02_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p03));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p03_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p04));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p04_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p05));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p05_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p06));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p06_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p07));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p07_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p08));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p08_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p09));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p09_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p10));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p10_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p11));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p11_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p12));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p12_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p13));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p13_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p14));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p14_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p15));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p15_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p16));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p16_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p17));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p17_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p18));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p18_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p19));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p19_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p20));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p20_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p21));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p21_1));

        mRecyclerViewItems.add(new MenuItem(R.mipmap.b05));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b05_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b06));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b06_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b07));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b07_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b08));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b08_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b09));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b09_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b10));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b10_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b11));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b11_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b12));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b12_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b13));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b13_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b14));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b14_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b15));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b15_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b16));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b16_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b17));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b17_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b18));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b18_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b19));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b19_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b20));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b20_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b21));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b21_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b22));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b22_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b23));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b23_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b24));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b24_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b25));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.b25_1));

        mRecyclerViewItems.add(new MenuItem(R.mipmap.d03));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d03_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d04));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d04_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d05));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d05_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d06));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d06_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d07));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d07_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d08));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d08_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d09));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d09_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d10));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d10_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d11));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d11_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d12));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d12_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d13));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d13_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d14));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d14_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d15));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d15_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d16));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d16_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d17));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d17_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d18));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d18_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d19));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d19_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d20));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d20_1));

        mRecyclerViewItems.add(new MenuItem(R.mipmap.p31));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p31_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p32));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p32_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p33));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p33_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p34));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p34_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p35));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.p35_1));


    }


    @Override
    public void onResume() {
        super.onResume();

        // Resume the AdView.
        mAdView.resume();
    }

    @Override
    public void onPause() {
        // Pause the AdView.
        mAdView.pause();

        super.onPause();
    }

    @Override
    public void onDestroy() {
        // Destroy the AdView.
        mAdView.destroy();

        super.onDestroy();
    }

}



/*
    private void getListItemData(){
       

        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        if (screenSize==Configuration.SCREENLAYOUT_SIZE_XLARGE) {

            mRecyclerViewItems.add(R.mipmap.d01);
            mRecyclerViewItems.add(R.mipmap.d01_1);
            mRecyclerViewItems.add(R.mipmap.d02);
            mRecyclerViewItems.add(R.mipmap.d02_1);

            mRecyclerViewItems.add(R.mipmap.p01);
            mRecyclerViewItems.add(R.mipmap.p01_1);
            mRecyclerViewItems.add(R.mipmap.p02);
            mRecyclerViewItems.add(R.mipmap.p02_1);
            mRecyclerViewItems.add(R.mipmap.p03);
            mRecyclerViewItems.add(R.mipmap.p03_1);
            mRecyclerViewItems.add(R.mipmap.p04);
            mRecyclerViewItems.add(R.mipmap.p04_1);
            mRecyclerViewItems.add(R.mipmap.p05);
            mRecyclerViewItems.add(R.mipmap.p05_1);
            mRecyclerViewItems.add(R.mipmap.p06);
            mRecyclerViewItems.add(R.mipmap.p06_1);
            mRecyclerViewItems.add(R.mipmap.p07);
            mRecyclerViewItems.add(R.mipmap.p07_1);
            mRecyclerViewItems.add(R.mipmap.p08);
            mRecyclerViewItems.add(R.mipmap.p08_1);
            mRecyclerViewItems.add(R.mipmap.p09);
            mRecyclerViewItems.add(R.mipmap.p09_1);
            mRecyclerViewItems.add(R.mipmap.p10);
            mRecyclerViewItems.add(R.mipmap.p10_1);
            mRecyclerViewItems.add(R.mipmap.p11);
            mRecyclerViewItems.add(R.mipmap.p11_1);
            mRecyclerViewItems.add(R.mipmap.p12);
            mRecyclerViewItems.add(R.mipmap.p12_1);
            mRecyclerViewItems.add(R.mipmap.p13);
            mRecyclerViewItems.add(R.mipmap.p13_1);
            mRecyclerViewItems.add(R.mipmap.p14);
            mRecyclerViewItems.add(R.mipmap.p14_1);
            mRecyclerViewItems.add(R.mipmap.p15);
            mRecyclerViewItems.add(R.mipmap.p15_1);
            mRecyclerViewItems.add(R.mipmap.p16);
            mRecyclerViewItems.add(R.mipmap.p16_1);
            mRecyclerViewItems.add(R.mipmap.p17);
            mRecyclerViewItems.add(R.mipmap.p17_1);
            mRecyclerViewItems.add(R.mipmap.p18);
            mRecyclerViewItems.add(R.mipmap.p18_1);
            mRecyclerViewItems.add(R.mipmap.p19);
            mRecyclerViewItems.add(R.mipmap.p19_1);
            mRecyclerViewItems.add(R.mipmap.p20);
            mRecyclerViewItems.add(R.mipmap.p20_1);
            mRecyclerViewItems.add(R.mipmap.p21);
            mRecyclerViewItems.add(R.mipmap.p21_1);

            mRecyclerViewItems.add(R.mipmap.d03);
            mRecyclerViewItems.add(R.mipmap.d03_1);
            mRecyclerViewItems.add(R.mipmap.d04);
            mRecyclerViewItems.add(R.mipmap.d04_1);
            mRecyclerViewItems.add(R.mipmap.d05);
            mRecyclerViewItems.add(R.mipmap.d05_1);
            mRecyclerViewItems.add(R.mipmap.d06);
            mRecyclerViewItems.add(R.mipmap.d06_1);
            mRecyclerViewItems.add(R.mipmap.d07);
            mRecyclerViewItems.add(R.mipmap.d07_1);
            mRecyclerViewItems.add(R.mipmap.d08);
            mRecyclerViewItems.add(R.mipmap.d08_1);
            mRecyclerViewItems.add(R.mipmap.d09);
            mRecyclerViewItems.add(R.mipmap.d09_1);
            mRecyclerViewItems.add(R.mipmap.d10);
            mRecyclerViewItems.add(R.mipmap.d10_1);
            mRecyclerViewItems.add(R.mipmap.d11);
            mRecyclerViewItems.add(R.mipmap.d11_1);
            mRecyclerViewItems.add(R.mipmap.d12);
            mRecyclerViewItems.add(R.mipmap.d12_1);
            mRecyclerViewItems.add(R.mipmap.d13);
            mRecyclerViewItems.add(R.mipmap.d13_1);
            mRecyclerViewItems.add(R.mipmap.d14);
            mRecyclerViewItems.add(R.mipmap.d14_1);
            mRecyclerViewItems.add(R.mipmap.d15);
            mRecyclerViewItems.add(R.mipmap.d15_1);
            mRecyclerViewItems.add(R.mipmap.d16);
            mRecyclerViewItems.add(R.mipmap.d16_1);
            mRecyclerViewItems.add(R.mipmap.d17);
            mRecyclerViewItems.add(R.mipmap.d17_1);
            mRecyclerViewItems.add(R.mipmap.d18);
            mRecyclerViewItems.add(R.mipmap.d18_1);
            mRecyclerViewItems.add(R.mipmap.d19);
            mRecyclerViewItems.add(R.mipmap.d19_1);
            mRecyclerViewItems.add(R.mipmap.d20);
            mRecyclerViewItems.add(R.mipmap.d20_1);

            mRecyclerViewItems.add(R.mipmap.p31);
            mRecyclerViewItems.add(R.mipmap.p31_1);
            mRecyclerViewItems.add(R.mipmap.p32);
            mRecyclerViewItems.add(R.mipmap.p32_1);
            mRecyclerViewItems.add(R.mipmap.p33);
            mRecyclerViewItems.add(R.mipmap.p33_1);
            mRecyclerViewItems.add(R.mipmap.p34);
            mRecyclerViewItems.add(R.mipmap.p34_1);
            mRecyclerViewItems.add(R.mipmap.p35);
            mRecyclerViewItems.add(R.mipmap.p35_1);
        }

		else{
            mRecyclerViewItems.add(R.mipmap.d01);
            mRecyclerViewItems.add(R.mipmap.d01_1);
            mRecyclerViewItems.add(R.mipmap.d02);
            mRecyclerViewItems.add(R.mipmap.d02_1);
            mRecyclerViewItems.add(R.mipmap.p01);
            mRecyclerViewItems.add(R.mipmap.p01_1);
            mRecyclerViewItems.add(R.mipmap.p02);
            mRecyclerViewItems.add(R.mipmap.p02_1);
            mRecyclerViewItems.add(R.mipmap.p03);
            mRecyclerViewItems.add(R.mipmap.p03_1);
            mRecyclerViewItems.add(R.mipmap.p04);
            mRecyclerViewItems.add(R.mipmap.p04_1);
            mRecyclerViewItems.add(R.mipmap.p05);
            mRecyclerViewItems.add(R.mipmap.p05_1);
            mRecyclerViewItems.add(R.mipmap.p06);
            mRecyclerViewItems.add(R.mipmap.p06_1);
            mRecyclerViewItems.add(R.mipmap.p07);
            mRecyclerViewItems.add(R.mipmap.p07_1);
            mRecyclerViewItems.add(R.mipmap.p08);
            mRecyclerViewItems.add(R.mipmap.p08_1);
            mRecyclerViewItems.add(R.mipmap.p09);
            mRecyclerViewItems.add(R.mipmap.p09_1);
            mRecyclerViewItems.add(R.mipmap.p10);
            mRecyclerViewItems.add(R.mipmap.p10_1);
            mRecyclerViewItems.add(R.mipmap.p11);
            mRecyclerViewItems.add(R.mipmap.p11_1);
            mRecyclerViewItems.add(R.mipmap.p12);
            mRecyclerViewItems.add(R.mipmap.p12_1);
            mRecyclerViewItems.add(R.mipmap.p13);
            mRecyclerViewItems.add(R.mipmap.p13_1);
            mRecyclerViewItems.add(R.mipmap.p14);
            mRecyclerViewItems.add(R.mipmap.p14_1);
            mRecyclerViewItems.add(R.mipmap.p15);
            mRecyclerViewItems.add(R.mipmap.p15_1);
            mRecyclerViewItems.add(R.mipmap.p16);
            mRecyclerViewItems.add(R.mipmap.p16_1);
            mRecyclerViewItems.add(R.mipmap.p17);
            mRecyclerViewItems.add(R.mipmap.p17_1);
            mRecyclerViewItems.add(R.mipmap.p18);
            mRecyclerViewItems.add(R.mipmap.p18_1);
            mRecyclerViewItems.add(R.mipmap.p19);
            mRecyclerViewItems.add(R.mipmap.p19_1);
            mRecyclerViewItems.add(R.mipmap.p20);
            mRecyclerViewItems.add(R.mipmap.p20_1);
            mRecyclerViewItems.add(R.mipmap.p21);
            mRecyclerViewItems.add(R.mipmap.p21_1);

            mRecyclerViewItems.add(R.mipmap.d03);
            mRecyclerViewItems.add(R.mipmap.d03_1);
            mRecyclerViewItems.add(R.mipmap.d04);
            mRecyclerViewItems.add(R.mipmap.d04_1);
            mRecyclerViewItems.add(R.mipmap.d05);
            mRecyclerViewItems.add(R.mipmap.d05_1);
            mRecyclerViewItems.add(R.mipmap.d06);
            mRecyclerViewItems.add(R.mipmap.d06_1);
            mRecyclerViewItems.add(R.mipmap.d07);
            mRecyclerViewItems.add(R.mipmap.d07_1);
            mRecyclerViewItems.add(R.mipmap.d08);
            mRecyclerViewItems.add(R.mipmap.d08_1);
            mRecyclerViewItems.add(R.mipmap.d09);
            mRecyclerViewItems.add(R.mipmap.d09_1);
            mRecyclerViewItems.add(R.mipmap.d10);
            mRecyclerViewItems.add(R.mipmap.d10_1);
            mRecyclerViewItems.add(R.mipmap.d11);
            mRecyclerViewItems.add(R.mipmap.d11_1);
            mRecyclerViewItems.add(R.mipmap.d12);
            mRecyclerViewItems.add(R.mipmap.d12_1);
            mRecyclerViewItems.add(R.mipmap.d13);
            mRecyclerViewItems.add(R.mipmap.d13_1);
            mRecyclerViewItems.add(R.mipmap.d14);
            mRecyclerViewItems.add(R.mipmap.d14_1);
            mRecyclerViewItems.add(R.mipmap.d15);
            mRecyclerViewItems.add(R.mipmap.d15_1);
            mRecyclerViewItems.add(R.mipmap.d16);
            mRecyclerViewItems.add(R.mipmap.d16_1);
            mRecyclerViewItems.add(R.mipmap.d17);
            mRecyclerViewItems.add(R.mipmap.d17_1);
            mRecyclerViewItems.add(R.mipmap.d18);
            mRecyclerViewItems.add(R.mipmap.d18_1);
            mRecyclerViewItems.add(R.mipmap.d19);
            mRecyclerViewItems.add(R.mipmap.d19_1);
            mRecyclerViewItems.add(R.mipmap.d20);
            mRecyclerViewItems.add(R.mipmap.d20_1);

            mRecyclerViewItems.add(R.mipmap.p31);
            mRecyclerViewItems.add(R.mipmap.p31_1);
            mRecyclerViewItems.add(R.mipmap.p32);
            mRecyclerViewItems.add(R.mipmap.p32_1);
            mRecyclerViewItems.add(R.mipmap.p33);
            mRecyclerViewItems.add(R.mipmap.p33_1);
            mRecyclerViewItems.add(R.mipmap.p34);
            mRecyclerViewItems.add(R.mipmap.p34_1);
            mRecyclerViewItems.add(R.mipmap.p35);
            mRecyclerViewItems.add(R.mipmap.p35_1);


        }


    }

    @Override
    public void onResume() {
        super.onResume();
        // Resume the AdView.
       // mAdViewMain.resume();
    }

    @Override
    public void onPause() {
        // Pause the AdView.
        //mAdViewMain.pause();

        super.onPause();
    }

    @Override
    public void onDestroy() {
        // Destroy the AdView.

       // mAdViewMain.destroy();
        super.onDestroy();
    }
}
*/