package com.sekoding.example.repository;

import com.sekoding.example.exception.DataNotFoundException;
import com.sekoding.example.model.Group;
import com.sekoding.example.model.Participant;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository {

    /**
     * Returns the information of a participant with matching {@code name}.
     *
     * @param name name of the participant
     * @return an instance of {@link Participant}
     * @throws DataNotFoundException if there is no participant with matching {@code name}
     */
    Participant getParticipant(String name) throws DataNotFoundException;

    /**
     * Retrieves the group with matching {@code name}.
     *
     * @param name name of the group.
     * @return an instance of {@link Group}
     * @throws DataNotFoundException if there is no group with matching {@code name}
     */
    Group getGroup(String name) throws DataNotFoundException;
}
