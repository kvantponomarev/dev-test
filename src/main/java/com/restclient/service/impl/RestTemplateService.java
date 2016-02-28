package com.restclient.service.impl;

import com.restclient.model.City;
import com.restclient.service.RESTClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service("restTemplateService")
public class RestTemplateService implements RESTClientService<City> {
    @Override
    public List<City> getData(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<City[]> responseEntity = restTemplate.getForEntity(url, City[].class);
        if(responseEntity != null && responseEntity.getBody() != null)
            return Arrays.asList(responseEntity.getBody());
        else
            return Collections.emptyList();
    }
}
