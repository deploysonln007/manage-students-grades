package com.example.quanlysv.servlet.util;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class HttpUtil {
    private static final Logger logger = LogManager.getLogger(HttpUtil.class);
    private String value;

    public HttpUtil (String value) {
        this.value = value;
    }

    public <T> T toModel(Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(value, tClass);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            logger.error(e.getMessage() +"lỗi ở HttpUtil");
        }
        return null;
    }

    public static HttpUtil of (BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
            logger.error(e.getMessage() +"lỗi ở HttpUtil");
        }
        return new HttpUtil(sb.toString());
    }
}
