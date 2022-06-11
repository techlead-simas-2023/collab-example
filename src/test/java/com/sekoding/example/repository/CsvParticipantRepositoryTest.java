package com.sekoding.example.repository;

import com.sekoding.example.exception.DataNotFoundException;
import com.sekoding.example.model.Group;
import com.sekoding.example.model.Participant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.*;

class CsvParticipantRepositoryTest {

    private Path temporaryFile;
    private ParticipantRepository participantRepository;

    @BeforeEach
    void setUp() throws Exception {
        temporaryFile = Files.createTempFile(Path.of("."), "CsvParticipantRepositoryTest_", ".csv");
        Files.writeString(temporaryFile, createSampleCsv(), StandardOpenOption.WRITE);
        participantRepository = new CsvParticipantRepository(temporaryFile);
    }

    @Test
    void getParticipant_exist() throws Exception {
        Participant participant = participantRepository.getParticipant("Bambang");

        assertEquals("Bambang", participant.getName());
    }

    @Test
    void getParticipant_doesNotExist() {
        assertThrows(DataNotFoundException.class, () -> participantRepository.getParticipant("Not Bambang"));
    }

    @Test
    void getGroup_exist() throws Exception {
        Group group = participantRepository.getGroup("Kelompok 1");

        assertEquals("Kelompok 1", group.getName());
        assertEquals(2, group.getMembers().size());
    }

    @Test
    void getGroup_doesNotExist() {
        assertThrows(DataNotFoundException.class, () -> participantRepository.getGroup("Kelompok 99"));
    }

    @AfterEach
    void tearDown() throws Exception {
        Files.deleteIfExists(temporaryFile);
    }

    private String createSampleCsv() {
        return "Bambang,Kelompok 1\nBambina,Kelompok 1\nCharlie,Kelompok 2\n";
    }
}
