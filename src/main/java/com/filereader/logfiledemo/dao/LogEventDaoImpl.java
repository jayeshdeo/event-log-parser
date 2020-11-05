package com.filereader.logfiledemo.dao;

import com.filereader.logfiledemo.model.LogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class LogEventDaoImpl implements  LogEventDao {

    Logger logger = LoggerFactory.getLogger(LogEventDaoImpl.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String  INSERT_QUERY = "INSERT INTO Event (Id,Duration,Type,Host,Alert) VALUES  (?,?,?,?,?)";

    public void saveLogEvent(LogEvent logEvent, long diff) {
        logger.info("Saving LogEvent with id"+logEvent.getId());
        Boolean flag = diff > 4;
        jdbcTemplate.update(INSERT_QUERY, logEvent.getId(), diff, logEvent.getType()
                , logEvent.getHost(), flag);
    }

}
