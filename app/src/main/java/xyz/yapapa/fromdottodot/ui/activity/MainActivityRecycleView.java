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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import xyz.yapapa.fromdottodot.R;

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

    // A Native Express ad is placed in every nth position in the RecyclerView.
    public static final int ITEMS_PER_AD = 11;

    // The Native Express ad height.
    private static final int NATIVE_EXPRESS_AD_HEIGHT = 150;

    // The Native Express ad unit ID.
    private static final String AD_UNIT_ID ="ca-app-pub-2888343178529026/5715534142";

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

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        final  RecyclerView.Adapter adapter = new RecyclerViewAdapter(this, mRecyclerViewItems);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getItemViewType(position)) {
                    case 0:
                        return 1;
                    case 1:
                        return 2;
                    default:
                        return -1;
                }
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        // Update the RecyclerView item's list with menu items and Native Express ads.
        addMenuItems();
        addNativeExpressAds();
        setUpAndLoadNativeExpressAds();

        // Specify an adapter.

        mRecyclerView.setAdapter(adapter);
    }

    /**
     * Adds Native Express ads to the items list.
     */
    private void addNativeExpressAds() {

        // Loop through the items array and place a new Native Express ad in every ith position in
        // the items List.
        for (int i = 0; i <= mRecyclerViewItems.size(); i += ITEMS_PER_AD) {
            final NativeExpressAdView adView = new NativeExpressAdView(MainActivityRecycleView.this);
            mRecyclerViewItems.add(i, adView);
        }
    }

    /**
     * Sets up and loads the Native Express ads.
     */
    private void setUpAndLoadNativeExpressAds() {
        // Use a Runnable to ensure that the RecyclerView has been laid out before setting the
        // ad size for the Native Express ad. This allows us to set the Native Express ad's
        // width to match the full width of the RecyclerView.
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                final float scale = MainActivityRecycleView.this.getResources().getDisplayMetrics().density;
                // Set the ad size and ad unit ID for each Native Express ad in the items list.
                for (int i = 0; i <= mRecyclerViewItems.size(); i += ITEMS_PER_AD) {
                    final NativeExpressAdView adView =
                            (NativeExpressAdView) mRecyclerViewItems.get(i);
                    final CardView cardView = (CardView) findViewById(R.id.ad_card_view);
                    final int adWidth = cardView.getWidth() - cardView.getPaddingLeft()
                            - cardView.getPaddingRight();
                    AdSize adSize = new AdSize((int) (adWidth / scale), NATIVE_EXPRESS_AD_HEIGHT);
                    adView.setAdSize(adSize);
                    adView.setAdUnitId(AD_UNIT_ID);
                }

                // Load the first Native Express ad in the items list.
                loadNativeExpressAd(0);
            }
        });
    }

    /**
     * Loads the Native Express ads in the items list.
     */
    private void loadNativeExpressAd(final int index) {

        if (index >= mRecyclerViewItems.size()) {
            return;
        }

        Object item = mRecyclerViewItems.get(index);
        if (!(item instanceof NativeExpressAdView)) {
            throw new ClassCastException("Expected item at index " + index + " to be a Native"
                    + " Express ad.");
        }

        final NativeExpressAdView adView = (NativeExpressAdView) item;

        // Set an AdListener on the NativeExpressAdView to wait for the previous Native Express ad
        // to finish loading before loading the next ad in the items list.
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                // The previous Native Express ad loaded successfully, call this method again to
                // load the next ad in the items list.
                loadNativeExpressAd(index + ITEMS_PER_AD);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // The previous Native Express ad failed to load. Call this method again to load
                // the next ad in the items list.
                Log.e("MainActivityRecycleView", "The previous Native Express ad failed to load. Attempting to"
                        + " load the next Native Express ad in the items list.");
                loadNativeExpressAd(index + ITEMS_PER_AD);
            }
        });

        // Load the Native Express ad.
        adView.loadAd(new AdRequest.Builder()
               // .addTestDevice("09D7B5315C60A80D280B8CDF618FD3DE")
                .build());
    }

    /**
     * Adds {@link MenuItem}'s from a JSON file.
     */
    private void addMenuItems() {



        mRecyclerViewItems.add(new MenuItem(R.mipmap.d01));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d01_1));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d02));
        mRecyclerViewItems.add(new MenuItem(R.mipmap.d02_1));

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