package com.borislaporte.lasalle.network;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.borislaporte.lasalle.LasalleApp;
import com.borislaporte.lasalle.model.Event;
import com.borislaporte.lasalle.model.EventResult;
import com.borislaporte.lasalle.model.EventResults;
import com.neopixl.library.spitfire.listener.RequestListener;
import com.neopixl.library.spitfire.request.BaseRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by moi on 25/04/2017.
 */

public class EventManager {

    public interface Listener<T> {
        void onReceived(T result);
        void onFailed();
    }

    public static void getAllEvents(final Listener<List<Event>> listener) {

        String url = UrlBuilder.getAllEventsUrl();

        BaseRequest.Builder<EventResults> resultsBuilder =
                new BaseRequest.Builder<>(Request.Method.GET, url, EventResults.class);
        resultsBuilder.listener(new RequestListener<EventResults>() {
            @Override
            public void onSuccess(EventResults eventResults) {
                if (listener != null) {
                    listener.onReceived(Arrays.asList(eventResults.getData()));
                }
            }

            @Override
            public void onFailure(VolleyError volleyError, int i) {
                if (listener != null) {
                    listener.onFailed();
                }
            }
        });

        LasalleApp
                .getSharedInstance()
                .getRequestQueue()
                .add(resultsBuilder.build());
    }


    public static void getEvent(String eventId, final Listener<Event> listener) {
        String url = UrlBuilder.getEventUrl(eventId);

        BaseRequest.Builder<EventResult> resultsBuilder =
                new BaseRequest.Builder<>(Request.Method.GET, url, EventResult.class);
        resultsBuilder.listener(new RequestListener<EventResult>() {

            @Override
            public void onSuccess(EventResult eventResult) {
                if (listener != null) {
                    listener.onReceived(eventResult.getEvent());
                }
            }

            @Override
            public void onFailure(VolleyError volleyError, int i) {

            }
        });

        LasalleApp.getSharedInstance()
                .getRequestQueue()
                .add(resultsBuilder.build());
    }


    public static void createEvent(final Event event) {
        String url = UrlBuilder.getCreateEventUrl();

        BaseRequest.Builder<EventResult> resultBuilder =
                new BaseRequest.Builder<>(Request.Method.POST, url, EventResult.class);
        resultBuilder.object(event);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        resultBuilder
            .headers(headers)
            .listener(new RequestListener<EventResult>() {
                @Override
                public void onSuccess(EventResult eventResult) {

                }

                @Override
                public void onFailure(VolleyError volleyError, int i) {

                }
            });

        LasalleApp
                .getSharedInstance()
                .getRequestQueue()
                .add(resultBuilder.build());
    }

    public static void updateEvent(String eventId, final Event event) {
        String url = UrlBuilder.getCreateEventUrl();

        BaseRequest.Builder<EventResult> resultBuilder =
                new BaseRequest.Builder<>(Request.Method.PUT, url, EventResult.class);
        resultBuilder.object(event);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        resultBuilder
                .headers(headers)
                .listener(new RequestListener<EventResult>() {
                    @Override
                    public void onSuccess(EventResult eventResult) {

                    }

                    @Override
                    public void onFailure(VolleyError volleyError, int i) {

                    }
                });

        LasalleApp
                .getSharedInstance()
                .getRequestQueue()
                .add(resultBuilder.build());
    }

    public static void deleteEvent(String eventId, final Listener<Event> listener) {
        String url = UrlBuilder.getDeleteEventUrl(eventId);

        BaseRequest.Builder<EventResult> resultsBuilder =
                new BaseRequest.Builder<>(Request.Method.DELETE, url, EventResult.class);
        resultsBuilder.listener(new RequestListener<EventResult>() {

            @Override
            public void onSuccess(EventResult eventResult) {
                if (listener != null) {
                    listener.onReceived(eventResult.getEvent());
                }
            }

            @Override
            public void onFailure(VolleyError volleyError, int i) {

            }
        });

        LasalleApp.getSharedInstance()
                .getRequestQueue()
                .add(resultsBuilder.build());
    }
}
