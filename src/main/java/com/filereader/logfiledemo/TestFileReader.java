package com.filereader.logfiledemo;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filereader.logfiledemo.model.LogEvent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TestFileReader {

    //    @Bean
//    public CommandLineRunner commandLineRunner() {
//        return args -> {
//            this.logEventService.processLogEvent(path);
//        };
//    }

    //private long;

    public static void saveEventToDB(LogEvent event, long diff) {
        Connection conn = null;
        PreparedStatement stmt;
        String db = "jdbc:hsqldb:hsql://localhost/log-event";
        String user = "SA";
        String password = "";
        Boolean flag = diff > 4;
        System.out.println("in saveDb :" + event + ":" + String.valueOf(diff) + " :flag: " + flag);

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            conn = DriverManager.getConnection(db, user, password);


            if (flag) {
                String sql = "INSERT INTO  FlagEvent (Id,Duration,Type,Host) VALUES " +
                        " (?,?,?,?)";

                stmt = conn.prepareStatement(sql);
                stmt.setString(1, event.getId());
                stmt.setLong(2, diff);
                stmt.setString(3, event.getType());
                stmt.setString(4, event.getHost());
                stmt.executeUpdate();
            }

            String sql1 = "INSERT INTO Event (Id,Duration,Type,Host,Alert) VALUES " +
                    " (?,?,?,?,?)";

            stmt = conn.prepareStatement(sql1);
            stmt.setString(1, event.getId());
            stmt.setLong(2, diff);
            stmt.setString(3, event.getType());
            stmt.setString(4, event.getHost());
            stmt.setBoolean(5, flag);
            stmt.executeUpdate();
            stmt.close();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close connection
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void saveToFile(LogEvent event, long diff) {

        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\jayes\\OneDrive\\Documents\\creditSuisse\\maxEventFile.txt", true)) {
            JsonGenerator generator = new JsonFactory().createGenerator(fos);
            generator.setCodec(new ObjectMapper());
            //for pretty printing
            generator.setPrettyPrinter(new DefaultPrettyPrinter());

            generator.writeStartObject(); // start root object
            System.out.println("in write method:" + event);
            generator.writeStringField("id", event.getId());
            generator.writeStringField("event duration", String.valueOf(diff));
            generator.writeStringField("host", event.getHost());
            generator.writeStringField("type", event.getType());
            generator.writeEndObject(); //closing properties

            generator.flush();
            generator.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        try (FileInputStream fis = new FileInputStream("C:\\Users\\jayes\\OneDrive\\Documents\\creditSuisse\\logfile.txt")) {
            JsonParser parser = new JsonFactory().createParser(fis);

            parser.setCodec(new ObjectMapper());
            parser.nextToken();
            Map<String, Map<String, String>> map = new HashMap();
            while (parser.hasCurrentToken()) {
                LogEvent event = parser.readValueAs(LogEvent.class);
                if (map.containsKey(event.getId())) {
                    Map<String, String> checkMap = map.get(event.getId());
                    System.out.println(event.getId() + " " + event.getTimestamp());
                    System.out.println(checkMap.get("timestamp"));

                    //   write check diff method good practice
                    long diff = Math.abs(Long.parseLong(event.getTimestamp()) - Long.parseLong(checkMap.get("timestamp")));
                    System.out.println("diff " + diff);
                    // call method for saving into file
                    //    saveToFile(event, diff);
                    // call method for saving into DB
                    saveEventToDB(event, diff);
                    // save all events to database

                    System.out.println("---------");

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
            e.printStackTrace();
        }
    }
}
