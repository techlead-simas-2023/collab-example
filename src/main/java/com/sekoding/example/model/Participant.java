package com.sekoding.example.model;

import java.util.Objects;

public class Participant {

    private String name;

    public Participant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Participant{" +
            "name='" + name + '\'' +
            '}';
    }
}
