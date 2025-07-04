package com.demo.demo.structure.util;

import com.demo.demo.structure.model.domain.User;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParsingCsvUtil {

    public List<User> parseCsv(MultipartFile multipartFile) {

        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                if (line.length >= 3) {
                    users.add(User.builder()
                            .name(line[0])
                            .lastname(line[1])
                            .email(line[2])
                            .build());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return users;
    }

}
