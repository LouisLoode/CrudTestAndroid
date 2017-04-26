package com.borislaporte.lasalle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by moi on 26/04/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResults extends BaseResponse {

    private Event[] data;

    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
        this.data = data;
    }
}
