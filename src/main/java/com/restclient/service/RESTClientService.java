package com.restclient.service;

import java.util.List;

public interface RESTClientService<T> {
    List<T> getData(String url);
}
