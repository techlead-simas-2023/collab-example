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

        groups.put("Kelompok 1", createKelompok1());
        groups.put("Kelompok 2", createKelompok2());
        groups.put("Kelompok 3", createKelompok3());
        groups.put("Kelompok 4", createKelompok4());

        return groups;
    }

    private static List<Participant> createKelompok1() {
        List<Participant> participants = new ArrayList<>();

        // TODO: Add Kelompok 1 group members
        participants.add(new Participant("Richman Tumpal Micael Pakpahan"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));

        return participants;
    }

    private static List<Participant> createKelompok2() {
        List<Participant> participants = new ArrayList<>();

        // TODO: Add Kelompok 2 group members
        participants.add(new Participant("Taufik Akbar Dufi"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));

        return participants;
    }

    private static List<Participant> createKelompok3() {
        List<Participant> participants = new ArrayList<>();

        // TODO: Complete Kelompok 3 group members
        participants.add(new Participant("Febry Widyatna"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));

        return participants;
    }

    private static List<Participant> createKelompok4() {
        List<Participant> participants = new ArrayList<>();

        // TODO: Complete Kelompok 4 group members
        participants.add(new Participant("Asni Valentina"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));
        participants.add(new Participant("TODO"));

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
