package com.borislaporte.lasalle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.borislaporte.lasalle.R;
import com.borislaporte.lasalle.adapter.EventDetailAdapter;
import com.borislaporte.lasalle.adapter.EventItem;
import com.borislaporte.lasalle.model.Event;
import com.borislaporte.lasalle.network.EventManager;

import java.util.ArrayList;
import java.util.List;

public class EventDetailActivity extends AppCompatActivity {


    private static final String EXTRA_EVENT_POSITION = "extra_event_position";

    public static Intent createIntent(Context context, Integer position) {

        Intent intent = new Intent(context, EventDetailActivity.class);
        Log.d("CREATE INTENT", position.toString());
        intent.putExtra(EXTRA_EVENT_POSITION, position.toString());

        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_detail);

        final EventDetailAdapter eventDetailAdapter = new EventDetailAdapter(getSupportFragmentManager());

        final ViewPager eventDetailViewPager = (ViewPager) findViewById(R.id.event_detail_viewpager);
        eventDetailViewPager.setAdapter(eventDetailAdapter);

        EventManager.getAllEvents(new EventManager.Listener<List<Event>>() {
            @Override
            public void onReceived(List<Event> events) {
                List<EventItem> items = new ArrayList<>();

                for(Event event : events) {
                    items.add(new EventItem(event));
                }
                eventDetailAdapter.refresh(items);
                eventDetailViewPager.setCurrentItem(
                        Integer.parseInt(
                                getIntent().getExtras().getString(EXTRA_EVENT_POSITION) ), false);
            }

            @Override
            public void onFailed() {

            }
        });
    }







    /*
    private static final String EXTRA_EVENT_ID = "extra_event_id";

    public static Intent createIntent(Context context, String eventId) {

        Intent intent = new Intent(context, EventDetailActivity.class);
        intent.putExtra(EXTRA_EVENT_ID, eventId);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        String eventId = getIntent().getStringExtra(EXTRA_EVENT_ID);

        EventDetailFragment eventDetailFragment = (EventDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.event_detail_fragment);


        eventDetailFragment.setEventId(eventId);
    }*/
}