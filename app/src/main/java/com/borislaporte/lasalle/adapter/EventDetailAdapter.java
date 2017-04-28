package com.borislaporte.lasalle.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.borislaporte.lasalle.fragment.EventDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by louisloode on 28/04/2017.
 */

public class EventDetailAdapter extends FragmentPagerAdapter {

    private List<EventItem> eventList;

    public EventDetailAdapter(FragmentManager fm) {
        super(fm);

        eventList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {

        String eventId = eventList.get(position).getEvent().getId();

        EventDetailFragment eventDetailFragment = new EventDetailFragment();
        eventDetailFragment.setEventId(eventId);

        return eventDetailFragment;
    }

    @Override
    public int getCount() {
        if(eventList != null) {
            return eventList.size();
        }
        return 0;
    }


    public void refresh(List<EventItem> events) {
        eventList.clear();
        eventList.addAll(events);

        //Refresh UI
        this.notifyDataSetChanged();
    }

}