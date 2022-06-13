package com.sekoding.example;

import com.sekoding.example.repository.CsvParticipantRepository;
import com.sekoding.example.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

@SpringBootApplication
public class ExampleApplication {

    @Value("${example.csvPath:classpath:data/kelompok.csv}")
    private String csvPath;

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Bean
    public ParticipantRepository csvParticipantRepository() throws IOException, URISyntaxException {
        Path toCsv;

        if (csvPath.contains("classpath")) {
            toCsv = Path.of(ResourceUtils.getURL(csvPath)
                .toURI());
        } else {
            toCsv = Path.of(csvPath);
        }

        if (csvPath.equals("demo")) {
            return new CsvParticipantRepository();
        }

        return new CsvParticipantRepository(toCsv);
    }
}
