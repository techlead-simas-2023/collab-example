package com.sekoding.example.repository;

import com.sekoding.example.exception.DataNotFoundException;
import com.sekoding.example.model.Group;
import com.sekoding.example.model.Participant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Notes: 1st class to implement in TDD process
public class CsvParticipantRepository implements ParticipantRepository {

    private Map<String, List<Participant>> groups;

    public CsvParticipantRepository() {
        this.groups = createSampleGroups();
    }

    public CsvParticipantRepository(Path csvPath) throws IOException {
        this.groups = createGroups(csvPath);
    }

    @Override
    public Participant getParticipant(String name) throws DataNotFoundException {
        for (List<Participant> groupMembers : groups.values()) {
            for (Participant participant : groupMembers) {
                if (participant.getName().equalsIgnoreCase(name)) {
                    return participant;
                }
            }
        }

        throw new DataNotFoundException(String.format("Participant '%s' does not exist", name));
    }

    @Override
    public Group getGroup(String name) throws DataNotFoundException {
        if (!groups.containsKey(name)) {
            throw new DataNotFoundException(String.format("Group '%s' does not exist", name));
        }

        return new Group(name, groups.get(name));
    }

    private static Map<String, List<Participant>> createSampleGroups() {
        Map<String, List<Participant>> groups = new HashMap<>();

        groups.put("Contoh", createKelompokContoh());
        groups.put("K1", createKelompok1());
        groups.put("K2", createKelompok2());
        groups.put("K3", createKelompok3());

        return groups;
    }

    private static List<Participant> createKelompokContoh() {
        List<Participant> participants = new ArrayList<>();

        participants.add(new Participant("Upin"));
        participants.add(new Participant("Ipin"));

        return participants;
    }

    private static List<Participant> createKelompok1() {
        List<Participant> participants = new ArrayList<>();

        participants.add(new Participant("Daya Adianto"));

        return participants;
    }

    private static List<Participant> createKelompok2() {
        List<Participant> participants = new ArrayList<>();

        participants.add(new Participant("Kucing Garong"));
        participants.add(new Participant("Guk Guk"));

        return participants;
    }

    private static List<Participant> createKelompok3() {
        List<Participant> participants = new ArrayList<>();

        participants.add(new Participant("David"));
        participants.add(new Participant("Messi"));
        participants.add(new Participant("Salah"));

        return participants;
    }

    private static Map<String, List<Participant>> createGroups(Path csvPath) throws IOException {
        List<String> nonEmptyLines = Files.readAllLines(csvPath).stream()
            .filter(line -> line.length() != 0)
            .collect(Collectors.toList());
        Map<String, List<Participant>> groups = new HashMap<>();

        for (String line : nonEmptyLines) {
            String[] values = line.split(",");

            String name = values[0];
            String group = values[1];

            List<Participant> members = groups.getOrDefault(group, new ArrayList<>());
            members.add(new Participant(name));

            groups.putIfAbsent(group, members);
        }

        return groups;
    }
}
