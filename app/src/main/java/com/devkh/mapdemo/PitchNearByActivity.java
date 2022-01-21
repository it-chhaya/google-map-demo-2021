package com.devkh.mapdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PitchNearByActivity extends AppCompatActivity {

    private final static String TAG = PitchNearByActivity.class.getName();

    private RecyclerView mRcvPitchCards;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitch_near_by);

        mRcvPitchCards = findViewById(R.id.rcv_pitch_card);

        ArrayList<PitchCard> items = new ArrayList<>();

        items.add(new PitchCard(R.drawable.cover, "Rooy7 Sport Club"));
        items.add(new PitchCard(R.drawable.cover, "Premium Sport Club"));
        items.add(new PitchCard(R.drawable.cover, "T-Soccer"));
        items.add(new PitchCard(R.drawable.cover, "White Sport"));
        items.add(new PitchCard(R.drawable.cover, "Big Star Sport Club"));
        items.add(new PitchCard(R.drawable.cover, "AEON 2 Premium Sport"));
        items.add(new PitchCard(R.drawable.cover, "Green Land"));

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        PitchNearByAdapter adapter = new PitchNearByAdapter(this, items);

        mRcvPitchCards.setLayoutManager(mLayoutManager);
        mRcvPitchCards.setAdapter(adapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRcvPitchCards);

        mRcvPitchCards.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    int activePosition = 0;
//                    int firstVisiblePosition = mLayoutManager.findFirstVisibleItemPosition();
//                    int lastVisiblePosition = mLayoutManager.findLastVisibleItemPosition();
//                    if (lastVisiblePosition > 1)
//                        activePosition = firstVisiblePosition + 1;
                    int activePosition = mLayoutManager.findFirstCompletelyVisibleItemPosition();
                    Log.i(TAG, "Current Item: " + activePosition);
                    Log.i(TAG, "Current Item: " + items.get(activePosition));
                }
            }
        });

    }

    /*private void preview() {
        int position = getCurrentItem();
        if (position > 0) {
            setCurrentItem(position - 1);
        }
    }

    private int getCurrentItem() {
        return mLayoutManager.findFirstVisibleItemPosition() + 1;
    }

    private void setCurrentItem(int position) {

    }*/

}