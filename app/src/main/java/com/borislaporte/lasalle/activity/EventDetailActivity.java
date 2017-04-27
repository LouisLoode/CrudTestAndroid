package com.borislaporte.lasalle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.borislaporte.lasalle.R;
import com.borislaporte.lasalle.fragment.EventDetailFragment;

public class EventDetailActivity extends AppCompatActivity {

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
    }
}