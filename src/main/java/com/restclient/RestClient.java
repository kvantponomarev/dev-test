package com.restclient;

import com.restclient.service.JsonToCsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class RestClient implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RestClient.class);
    private static final String FILE_NAME = "data.csv";
    private static final String URL_PREFIX = "http://api.goeuro.com/api/v2/position/suggest/en/";

    @Autowired
    private JsonToCsvService jsonToCsvServiceImpl;

    public static void main(String args[]) {
        log.info("Application started");
        log.info("Checking parameters");
        if(args == null || args.length == 0 || StringUtils.isEmpty(args[0]))
            throw new IllegalArgumentException("Please, specify city name as the first input argument");
        String url = URL_PREFIX + args[0];
        log.info("Computed URL = " + url);
        SpringApplication.run(RestClient.class, url);
    }

    @Override
    public void run(String... args) throws Exception {
        jsonToCsvServiceImpl.getDataAndSave(args[0], FILE_NAME);
    }
}