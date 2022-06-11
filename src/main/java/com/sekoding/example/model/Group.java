package com.sekoding.example.model;

import java.util.List;
import java.util.Objects;

public class Group {

    private String name;
    private List<Participant> members;

    public Group(String name, List<Participant> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public List<Participant> getMembers() {
        return members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return name.equals(group.name) && members.equals(group.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, members);
    }

    @Override
    public String toString() {
        return "Group{" +
            "name='" + name + '\'' +
            ", members=" + members +
            '}';
    }
}
