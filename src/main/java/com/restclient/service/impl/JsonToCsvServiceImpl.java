package com.restclient.service.impl;

import com.restclient.model.City;
import com.restclient.service.CSVService;
import com.restclient.service.JsonToCsvService;
import com.restclient.service.RESTClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("jsonToCsvServiceImpl")
public class JsonToCsvServiceImpl implements JsonToCsvService {

    private static final Logger log = LoggerFactory.getLogger(JsonToCsvServiceImpl.class);

    @Autowired
    private CSVService<City> commonsCSVService;

    @Autowired
    private RESTClientService<City> restTemplateService;

    @Override
    public void getDataAndSave(String url, String fileName) {
        if(StringUtils.isEmpty(url))
            throw new IllegalArgumentException("URL parameter is Empty");
        if(StringUtils.isEmpty(fileName))
            throw new IllegalArgumentException("fileName parameter is Empty");
        log.info("Getting Data by URL " + url);
        List<City> results = restTemplateService.getData(url);
        log.info("Found " + (results != null ? results.size() : 0) + " objects");
        log.info("Saving to file " + fileName);
        commonsCSVService.save(fileName, results);
        log.info("Process successfully completed, please, check file " + fileName + " for results");
    }
}
