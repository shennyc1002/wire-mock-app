package com.motel666.usereventservice.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="EVENT")
public class Event {

    @Id
    @Column(name="EVENT_ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;

    @Column(name="USER_ID")
    private String userId;

    @Column(name="EVENT_TYPE")
    private String eventType;

    @Column(name="CONTEXT")
    private String context;

    @Column(name="EVENT_TIME")
    private Date eventTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }
}
