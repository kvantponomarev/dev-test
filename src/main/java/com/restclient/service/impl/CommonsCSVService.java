package com.restclient.service.impl;

import com.restclient.model.City;
import com.restclient.model.GeoPosition;
import com.restclient.service.CSVService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("commonsCSVService")
public class CommonsCSVService implements CSVService<City> {

    private static final Logger log = LoggerFactory.getLogger(CommonsCSVService.class);

    @Override
    public void save(String fileName, List<City> objects) {
        CSVPrinter csvFilePrinter = null;
        FileWriter fileWriter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
        try {
            fileWriter = new FileWriter(fileName);
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            if(objects != null)
                for (City city : objects) {
                    List<String> record = new ArrayList<>();
                    record.add(String.valueOf(city.get_id()));
                    record.add(city.getName());
                    record.add(city.getType());
                    GeoPosition position = city.getGeo_position();
                    if(position != null) {
                        record.add(String.valueOf(position.getLatitude()));
                        record.add(String.valueOf(position.getLongitude()));
                    }
                    csvFilePrinter.printRecord(record);
                }
        } catch (Exception e) {
            log.error("Can't save to CSV file", e);
            throw new RuntimeException("Can't save to CSV file", e);
        } finally {
            try {
                if(fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
                if(csvFilePrinter != null) {
                    csvFilePrinter.close();
                }
            } catch (IOException e) {
                log.error("Can't flush CVS file", e);
                throw new RuntimeException("Can't flush CVS file", e);
            }
        }
    }
}
