package com.borislaporte.lasalle.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.borislaporte.lasalle.R;
import com.borislaporte.lasalle.activity.EventDetailActivity;
import com.borislaporte.lasalle.adapter.EventItem;
import com.borislaporte.lasalle.model.Event;
import com.borislaporte.lasalle.network.EventManager;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsListFragment extends Fragment {

    @BindView(R.id.recyclerview_events)
    RecyclerView eventsRecyclerView;

    private FastItemAdapter<EventItem> eventsAdapter;

    public EventsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_events_list, container, false);

        // TRICK pour garder le click
        // seulement au niveau du fragment le plus haut
        rootView.setClickable(true);

        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        eventsAdapter = new FastItemAdapter<>();

        eventsAdapter.withOnClickListener(new FastAdapter.OnClickListener<EventItem>() {
            @Override
            public boolean onClick(View v, IAdapter<EventItem> adapter, EventItem item, int position) {

                Event event = item.getEvent();

                String eventId = event.getId();

                ImageView rowImageView = (ImageView)v.findViewById(R.id.row_event_image_view);
                String transitionName = getString(R.string.transition_main_to_detail);

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), rowImageView ,transitionName);

                //Intent intent = EventDetailActivity.createIntent(getContext(), eventId);
                Intent intent = EventDetailActivity.createIntent(getContext(), position);
                startActivity(intent, options.toBundle());

                return true;
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        eventsRecyclerView.setLayoutManager(linearLayoutManager);

        eventsRecyclerView.setAdapter(eventsAdapter);

        EventManager.getAllEvents(new EventManager.Listener<List<Event>>() {
            @Override
            public void onReceived(List<Event> result) {
                List<EventItem> items = new ArrayList<EventItem>();

                for(Event event : result) {
                    items.add(new EventItem(event));
                }

                eventsAdapter.add(items);
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
