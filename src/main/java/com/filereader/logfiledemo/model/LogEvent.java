package com.filereader.logfiledemo.model;

public class LogEvent {
    private String id;
    private String type;
    private String state;
    private String timestamp;
    private String host;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public LogEvent() {

    }

    public LogEvent(String id, String type, String state, String timestamp, String host) {
        this.id = id;
        this.type = type;
        this.state = state;
        this.timestamp = timestamp;
        this.host = host;
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", timestamp=" + timestamp +
                ", host='" + host + '\'' +
                '}';
    }
}
