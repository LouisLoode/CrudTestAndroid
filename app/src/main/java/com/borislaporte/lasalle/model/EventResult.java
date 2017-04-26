package com.borislaporte.lasalle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by moi on 26/04/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResult extends  BaseResponse {

    private Event event;

    @JsonProperty("data")
    public Event getEvent() {
        return event;
    }

    @JsonProperty("data")
    public void setEvent(Event event) {
        this.event = event;
    }
}
