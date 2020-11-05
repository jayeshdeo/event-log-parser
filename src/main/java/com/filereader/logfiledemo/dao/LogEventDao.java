package com.filereader.logfiledemo.dao;

import com.filereader.logfiledemo.model.LogEvent;

public interface LogEventDao {

    public void saveLogEvent(LogEvent logEvent, long diff);
}
