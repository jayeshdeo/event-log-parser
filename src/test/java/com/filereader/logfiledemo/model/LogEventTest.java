package com.filereader.logfiledemo.model;


import org.junit.Assert;
import org.junit.Test;

public class LogEventTest {

    @Test
    public void testLogEvent(){
        LogEvent logEvent = new LogEvent();
        logEvent.setId("Test");
        logEvent.setHost("Test");
        logEvent.setState("Test");
        logEvent.setTimestamp("Test");
        logEvent.setType("Test");
        Assert.assertEquals("Test",logEvent.getId());
        Assert.assertEquals("Test",logEvent.getHost());
        Assert.assertEquals("Test",logEvent.getState());
        Assert.assertEquals("Test",logEvent.getTimestamp());
        Assert.assertEquals("Test",logEvent.getType());

    }

}
