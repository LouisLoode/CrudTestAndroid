package com.borislaporte.lasalle.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.borislaporte.lasalle.R;
import com.borislaporte.lasalle.model.Event;
import com.borislaporte.lasalle.network.EventManager;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class EventDetailFragment extends BaseFragment {

    private static final String ARGUMENT_EVENT_ID = "event_id";

    @BindView(R.id.detail_title_textview)
    TextView titleTextView;

    @BindView(R.id.detail_description_textview)
    TextView descriptionTextView;

    @BindView(R.id.detail_date_textview)
    TextView dateTextView;

    @BindView(R.id.detail_place_textview)
    TextView detailTextView;

    @BindView(R.id.imageView)
    ImageView imageView;

    private String eventId;

    public EventDetailFragment() {
        // Required empty public constructor

        Bundle arguments = new Bundle();
        setArguments(arguments);
    }

    public static EventDetailFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString(ARGUMENT_EVENT_ID, id);

        EventDetailFragment fragment = new EventDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_event_detail, container, false);

        rootView.setClickable(true);

        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        String eventId = getArguments().getString(ARGUMENT_EVENT_ID);

        showLoading();

        EventManager.getEvent(eventId, new EventManager.Listener<Event>() {

            @Override
            public void onReceived(Event result) {
                refresh(result);
                hideLoading();
            }

            @Override
            public void onFailed() {
                hideLoading();
            }
        });
    }

    private void refresh(Event event) {
        titleTextView.setText(event.getName());
        descriptionTextView.setText(event.getDescription());
        dateTextView.setText(event.getCreatedAt());
        Glide.with(getContext())
                .load(event.getImageUrl())
                .into(imageView);
    }

    public void setEventId(String eventId) {
        Bundle arguments = getArguments();
        arguments.putString(ARGUMENT_EVENT_ID, eventId);
    }
}
