package com.filereader.logfiledemo.service;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filereader.logfiledemo.dao.LogEventDao;
import com.filereader.logfiledemo.model.LogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class LogEventServiceImpl implements LogEventService {

    Logger logger = LoggerFactory.getLogger(LogEventServiceImpl.class);

    @Autowired
    private LogEventDao logEventDao;

    @Override
    public void processLogEvent(String path) {
        try (FileInputStream fis = new FileInputStream(path)) {
            logger.info("Reading file from :" + path);
            JsonParser parser = new JsonFactory().createParser(fis);
            parser.setCodec(new ObjectMapper());
            parser.nextToken();
            Map<String, Map<String, String>> map = new HashMap();
            while (parser.hasCurrentToken()) {
                LogEvent event = parser.readValueAs(LogEvent.class);
                if (map.containsKey(event.getId())) {
                    Map<String, String> checkMap = map.get(event.getId());
                    //   write check diff method good practice utility class
                    long diff = Math.abs(Long.parseLong(event.getTimestamp()) - Long.parseLong(checkMap.get("timestamp")));
                    // call method for saving into DB
                    this.logEventDao.saveLogEvent(event, diff);
                } else {
                    Map<String, String> putMap = new HashMap();
                    putMap.put("type", event.getType());
                    putMap.put("state", event.getState());
                    putMap.put("timestamp", event.getTimestamp());
                    putMap.put("host", event.getHost());
                    map.put(event.getId(), putMap);
                }
                parser.nextToken();

            }
        } catch (IOException e) {
            logger.info("File not found");
            e.printStackTrace();
        }
    }
}
