package com.restclient.service;

import java.util.List;

public interface CSVService<T> {
    void save(String fileName, List<T> objects);
}
