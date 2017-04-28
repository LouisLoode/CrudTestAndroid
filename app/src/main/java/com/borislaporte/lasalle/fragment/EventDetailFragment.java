package com.borislaporte.lasalle.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.borislaporte.lasalle.R;
import com.borislaporte.lasalle.activity.EventAddActivity;
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
    private static final int REQUEST_UPDATE_EVENT = 0;

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

        final Button editButton = (Button) rootView.findViewById(R.id.detail_update_event);

        editButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String eventId = getArguments().getString(ARGUMENT_EVENT_ID);

                Log.e("eventId",eventId);

                /*showLoading();
                hideLoading();*/

                Intent intent = new Intent(getActivity().getApplicationContext(), EventAddActivity.class);
                startActivityForResult(intent, REQUEST_UPDATE_EVENT);


            }
        });

        final Button deleteButton = (Button) rootView.findViewById(R.id.detail_delete_event);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eventId = getArguments().getString(ARGUMENT_EVENT_ID);

                Log.e("eventId",eventId);

                showLoading();

                EventManager.deleteEvent(eventId, new EventManager.Listener<Event>() {

                    @Override
                    public void onReceived(Event result) {
                        refresh(result);
                        //Log.e("deleteEvent","DEBUG onReceived");

                        getActivity().finish();


                        hideLoading();
                    }

                    @Override
                    public void onFailed() {

                        hideLoading();
                        //Log.e("deleteEvent","DEBUG onReceived");
                    }
                });
            }
        });

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
