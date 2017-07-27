package xyz.yapapa.fromdottodot.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import xyz.yapapa.fromdottodot.R;


public class MainActivityRecycleView extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private AdView mAdViewMain;

    private StaggeredGridLayoutManager gaggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycle);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        if (screenSize==Configuration.SCREENLAYOUT_SIZE_XLARGE) {
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);}
        else
        {gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);}
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);

        List<ItemObjects> gaggeredList = getListItemData();

        ItemRecyclerViewAdapter rcAdapter = new ItemRecyclerViewAdapter(MainActivityRecycleView.this, gaggeredList);
        recyclerView.setAdapter(rcAdapter);



        mAdViewMain = (AdView) findViewById(R.id.adViewMain);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("09D7B5315C60A80D280B8CDF618FD3DE")
                .build();
        mAdViewMain.loadAd(adRequest);
    }

    private List<ItemObjects> getListItemData(){
        List<ItemObjects> listViewItems = new ArrayList<ItemObjects>();

        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        if (screenSize==Configuration.SCREENLAYOUT_SIZE_XLARGE) {

            listViewItems.add(new ItemObjects(R.mipmap.d01));
            listViewItems.add(new ItemObjects(R.mipmap.d01_1));
            listViewItems.add(new ItemObjects(R.mipmap.d02));
            listViewItems.add(new ItemObjects(R.mipmap.d02_1));

            listViewItems.add(new ItemObjects(R.mipmap.p01));
            listViewItems.add(new ItemObjects(R.mipmap.p01_1));
            listViewItems.add(new ItemObjects(R.mipmap.p02));
            listViewItems.add(new ItemObjects(R.mipmap.p02_1));
            listViewItems.add(new ItemObjects(R.mipmap.p03));
            listViewItems.add(new ItemObjects(R.mipmap.p03_1));
            listViewItems.add(new ItemObjects(R.mipmap.p04));
            listViewItems.add(new ItemObjects(R.mipmap.p04_1));
            listViewItems.add(new ItemObjects(R.mipmap.p05));
            listViewItems.add(new ItemObjects(R.mipmap.p05_1));
            listViewItems.add(new ItemObjects(R.mipmap.p06));
            listViewItems.add(new ItemObjects(R.mipmap.p06_1));
            listViewItems.add(new ItemObjects(R.mipmap.p07));
            listViewItems.add(new ItemObjects(R.mipmap.p07_1));
            listViewItems.add(new ItemObjects(R.mipmap.p08));
            listViewItems.add(new ItemObjects(R.mipmap.p08_1));
            listViewItems.add(new ItemObjects(R.mipmap.p09));
            listViewItems.add(new ItemObjects(R.mipmap.p09_1));
            listViewItems.add(new ItemObjects(R.mipmap.p10));
            listViewItems.add(new ItemObjects(R.mipmap.p10_1));
            listViewItems.add(new ItemObjects(R.mipmap.p11));
            listViewItems.add(new ItemObjects(R.mipmap.p11_1));
            listViewItems.add(new ItemObjects(R.mipmap.p12));
            listViewItems.add(new ItemObjects(R.mipmap.p12_1));
            listViewItems.add(new ItemObjects(R.mipmap.p13));
            listViewItems.add(new ItemObjects(R.mipmap.p13_1));
            listViewItems.add(new ItemObjects(R.mipmap.p14));
            listViewItems.add(new ItemObjects(R.mipmap.p14_1));
            listViewItems.add(new ItemObjects(R.mipmap.p15));
            listViewItems.add(new ItemObjects(R.mipmap.p15_1));
            listViewItems.add(new ItemObjects(R.mipmap.p16));
            listViewItems.add(new ItemObjects(R.mipmap.p16_1));
            listViewItems.add(new ItemObjects(R.mipmap.p17));
            listViewItems.add(new ItemObjects(R.mipmap.p17_1));
            listViewItems.add(new ItemObjects(R.mipmap.p18));
            listViewItems.add(new ItemObjects(R.mipmap.p18_1));
            listViewItems.add(new ItemObjects(R.mipmap.p19));
            listViewItems.add(new ItemObjects(R.mipmap.p19_1));
            listViewItems.add(new ItemObjects(R.mipmap.p20));
            listViewItems.add(new ItemObjects(R.mipmap.p20_1));
            listViewItems.add(new ItemObjects(R.mipmap.p21));
            listViewItems.add(new ItemObjects(R.mipmap.p21_1));

            listViewItems.add(new ItemObjects(R.mipmap.d03));
            listViewItems.add(new ItemObjects(R.mipmap.d03_1));
            listViewItems.add(new ItemObjects(R.mipmap.d04));
            listViewItems.add(new ItemObjects(R.mipmap.d04_1));
            listViewItems.add(new ItemObjects(R.mipmap.d05));
            listViewItems.add(new ItemObjects(R.mipmap.d05_1));
            listViewItems.add(new ItemObjects(R.mipmap.d06));
            listViewItems.add(new ItemObjects(R.mipmap.d06_1));
            listViewItems.add(new ItemObjects(R.mipmap.d07));
            listViewItems.add(new ItemObjects(R.mipmap.d07_1));
            listViewItems.add(new ItemObjects(R.mipmap.d08));
            listViewItems.add(new ItemObjects(R.mipmap.d08_1));
            listViewItems.add(new ItemObjects(R.mipmap.d09));
            listViewItems.add(new ItemObjects(R.mipmap.d09_1));
            listViewItems.add(new ItemObjects(R.mipmap.d10));
            listViewItems.add(new ItemObjects(R.mipmap.d10_1));
            listViewItems.add(new ItemObjects(R.mipmap.d11));
            listViewItems.add(new ItemObjects(R.mipmap.d11_1));
            listViewItems.add(new ItemObjects(R.mipmap.d12));
            listViewItems.add(new ItemObjects(R.mipmap.d12_1));
            listViewItems.add(new ItemObjects(R.mipmap.d13));
            listViewItems.add(new ItemObjects(R.mipmap.d13_1));
            listViewItems.add(new ItemObjects(R.mipmap.d14));
            listViewItems.add(new ItemObjects(R.mipmap.d14_1));
            listViewItems.add(new ItemObjects(R.mipmap.d15));
            listViewItems.add(new ItemObjects(R.mipmap.d15_1));
            listViewItems.add(new ItemObjects(R.mipmap.d16));
            listViewItems.add(new ItemObjects(R.mipmap.d16_1));
            listViewItems.add(new ItemObjects(R.mipmap.d17));
            listViewItems.add(new ItemObjects(R.mipmap.d17_1));
            listViewItems.add(new ItemObjects(R.mipmap.d18));
            listViewItems.add(new ItemObjects(R.mipmap.d18_1));
            listViewItems.add(new ItemObjects(R.mipmap.d19));
            listViewItems.add(new ItemObjects(R.mipmap.d19_1));
            listViewItems.add(new ItemObjects(R.mipmap.d20));
            listViewItems.add(new ItemObjects(R.mipmap.d20_1));

            listViewItems.add(new ItemObjects(R.mipmap.p31));
            listViewItems.add(new ItemObjects(R.mipmap.p31_1));
            listViewItems.add(new ItemObjects(R.mipmap.p32));
            listViewItems.add(new ItemObjects(R.mipmap.p32_1));
            listViewItems.add(new ItemObjects(R.mipmap.p33));
            listViewItems.add(new ItemObjects(R.mipmap.p33_1));
            listViewItems.add(new ItemObjects(R.mipmap.p34));
            listViewItems.add(new ItemObjects(R.mipmap.p34_1));
            listViewItems.add(new ItemObjects(R.mipmap.p35));
            listViewItems.add(new ItemObjects(R.mipmap.p35_1));
        }

		else{
            listViewItems.add(new ItemObjects(R.mipmap.d01));
            listViewItems.add(new ItemObjects(R.mipmap.d01_1));
            listViewItems.add(new ItemObjects(R.mipmap.d02));
            listViewItems.add(new ItemObjects(R.mipmap.d02_1));
            listViewItems.add(new ItemObjects(R.mipmap.p01));
            listViewItems.add(new ItemObjects(R.mipmap.p01_1));
            listViewItems.add(new ItemObjects(R.mipmap.p02));
            listViewItems.add(new ItemObjects(R.mipmap.p02_1));
            listViewItems.add(new ItemObjects(R.mipmap.p03));
            listViewItems.add(new ItemObjects(R.mipmap.p03_1));
            listViewItems.add(new ItemObjects(R.mipmap.p04));
            listViewItems.add(new ItemObjects(R.mipmap.p04_1));
            listViewItems.add(new ItemObjects(R.mipmap.p05));
            listViewItems.add(new ItemObjects(R.mipmap.p05_1));
            listViewItems.add(new ItemObjects(R.mipmap.p06));
            listViewItems.add(new ItemObjects(R.mipmap.p06_1));
            listViewItems.add(new ItemObjects(R.mipmap.p07));
            listViewItems.add(new ItemObjects(R.mipmap.p07_1));
            listViewItems.add(new ItemObjects(R.mipmap.p08));
            listViewItems.add(new ItemObjects(R.mipmap.p08_1));
            listViewItems.add(new ItemObjects(R.mipmap.p09));
            listViewItems.add(new ItemObjects(R.mipmap.p09_1));
            listViewItems.add(new ItemObjects(R.mipmap.p10));
            listViewItems.add(new ItemObjects(R.mipmap.p10_1));
            listViewItems.add(new ItemObjects(R.mipmap.p11));
            listViewItems.add(new ItemObjects(R.mipmap.p11_1));
            listViewItems.add(new ItemObjects(R.mipmap.p12));
            listViewItems.add(new ItemObjects(R.mipmap.p12_1));
            listViewItems.add(new ItemObjects(R.mipmap.p13));
            listViewItems.add(new ItemObjects(R.mipmap.p13_1));
            listViewItems.add(new ItemObjects(R.mipmap.p14));
            listViewItems.add(new ItemObjects(R.mipmap.p14_1));
            listViewItems.add(new ItemObjects(R.mipmap.p15));
            listViewItems.add(new ItemObjects(R.mipmap.p15_1));
            listViewItems.add(new ItemObjects(R.mipmap.p16));
            listViewItems.add(new ItemObjects(R.mipmap.p16_1));
            listViewItems.add(new ItemObjects(R.mipmap.p17));
            listViewItems.add(new ItemObjects(R.mipmap.p17_1));
            listViewItems.add(new ItemObjects(R.mipmap.p18));
            listViewItems.add(new ItemObjects(R.mipmap.p18_1));
            listViewItems.add(new ItemObjects(R.mipmap.p19));
            listViewItems.add(new ItemObjects(R.mipmap.p19_1));
            listViewItems.add(new ItemObjects(R.mipmap.p20));
            listViewItems.add(new ItemObjects(R.mipmap.p20_1));
            listViewItems.add(new ItemObjects(R.mipmap.p21));
            listViewItems.add(new ItemObjects(R.mipmap.p21_1));

            listViewItems.add(new ItemObjects(R.mipmap.d03));
            listViewItems.add(new ItemObjects(R.mipmap.d03_1));
            listViewItems.add(new ItemObjects(R.mipmap.d04));
            listViewItems.add(new ItemObjects(R.mipmap.d04_1));
            listViewItems.add(new ItemObjects(R.mipmap.d05));
            listViewItems.add(new ItemObjects(R.mipmap.d05_1));
            listViewItems.add(new ItemObjects(R.mipmap.d06));
            listViewItems.add(new ItemObjects(R.mipmap.d06_1));
            listViewItems.add(new ItemObjects(R.mipmap.d07));
            listViewItems.add(new ItemObjects(R.mipmap.d07_1));
            listViewItems.add(new ItemObjects(R.mipmap.d08));
            listViewItems.add(new ItemObjects(R.mipmap.d08_1));
            listViewItems.add(new ItemObjects(R.mipmap.d09));
            listViewItems.add(new ItemObjects(R.mipmap.d09_1));
            listViewItems.add(new ItemObjects(R.mipmap.d10));
            listViewItems.add(new ItemObjects(R.mipmap.d10_1));
            listViewItems.add(new ItemObjects(R.mipmap.d11));
            listViewItems.add(new ItemObjects(R.mipmap.d11_1));
            listViewItems.add(new ItemObjects(R.mipmap.d12));
            listViewItems.add(new ItemObjects(R.mipmap.d12_1));
            listViewItems.add(new ItemObjects(R.mipmap.d13));
            listViewItems.add(new ItemObjects(R.mipmap.d13_1));
            listViewItems.add(new ItemObjects(R.mipmap.d14));
            listViewItems.add(new ItemObjects(R.mipmap.d14_1));
            listViewItems.add(new ItemObjects(R.mipmap.d15));
            listViewItems.add(new ItemObjects(R.mipmap.d15_1));
            listViewItems.add(new ItemObjects(R.mipmap.d16));
            listViewItems.add(new ItemObjects(R.mipmap.d16_1));
            listViewItems.add(new ItemObjects(R.mipmap.d17));
            listViewItems.add(new ItemObjects(R.mipmap.d17_1));
            listViewItems.add(new ItemObjects(R.mipmap.d18));
            listViewItems.add(new ItemObjects(R.mipmap.d18_1));
            listViewItems.add(new ItemObjects(R.mipmap.d19));
            listViewItems.add(new ItemObjects(R.mipmap.d19_1));
            listViewItems.add(new ItemObjects(R.mipmap.d20));
            listViewItems.add(new ItemObjects(R.mipmap.d20_1));

            listViewItems.add(new ItemObjects(R.mipmap.p31));
            listViewItems.add(new ItemObjects(R.mipmap.p31_1));
            listViewItems.add(new ItemObjects(R.mipmap.p32));
            listViewItems.add(new ItemObjects(R.mipmap.p32_1));
            listViewItems.add(new ItemObjects(R.mipmap.p33));
            listViewItems.add(new ItemObjects(R.mipmap.p33_1));
            listViewItems.add(new ItemObjects(R.mipmap.p34));
            listViewItems.add(new ItemObjects(R.mipmap.p34_1));
            listViewItems.add(new ItemObjects(R.mipmap.p35));
            listViewItems.add(new ItemObjects(R.mipmap.p35_1));


        }

        return listViewItems;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Resume the AdView.
        mAdViewMain.resume();
    }

    @Override
    public void onPause() {
        // Pause the AdView.
        mAdViewMain.pause();

        super.onPause();
    }

    @Override
    public void onDestroy() {
        // Destroy the AdView.

        mAdViewMain.destroy();
        super.onDestroy();
    }
}
