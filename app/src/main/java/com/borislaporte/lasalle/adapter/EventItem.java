package com.borislaporte.lasalle.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.borislaporte.lasalle.R;
import com.borislaporte.lasalle.model.Event;
import com.bumptech.glide.Glide;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by moi on 25/04/2017.
 */

public class EventItem extends AbstractItem<EventItem, EventItem.EventItemViewHolder> {

    private final Event event;

    public EventItem(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public EventItemViewHolder getViewHolder(View v) {
        return new EventItemViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.row_event_image_view;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.row_event;
    }

    @Override
    public void bindView(EventItemViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);

        holder.titleTextView.setText(event.getName());
        holder.dateTextView.setText(event.getCreatedAt());
        holder.imageView.setImageDrawable(null);

        String url = event.getImageUrl();

        Glide.with(holder.titleTextView.getContext())
                .load(event.getImageUrl())
                .into(holder.imageView);
    }

    protected static class EventItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.row_event_image_view)
        ImageView imageView;

        @BindView(R.id.row_event_title_textview)
        TextView titleTextView;

        @BindView(R.id.row_event_date_textview)
        TextView dateTextView;

        public EventItemViewHolder(View itemView) {
            super(itemView);

            // Bind with ButterKnife !
            ButterKnife.bind(this, itemView);
        }
    }
}
