package com.sekoding.example.controller;

import com.sekoding.example.exception.DataNotFoundException;
import com.sekoding.example.model.Group;
import com.sekoding.example.repository.ParticipantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ParticipantController {

    private static final Logger LOG = LoggerFactory.getLogger(ParticipantController.class);

    private ParticipantRepository participantRepository;

    @Autowired
    public ParticipantController(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @GetMapping("/group/{name}")
    public String getGroupPage(@PathVariable String name, Model model) throws DataNotFoundException {
        LOG.debug("Input group name: " + name);

        Group group = participantRepository.getGroup(name);

        model.addAttribute("group", group);

        return "group";
    }

    @ExceptionHandler
    public String handleNotFound(DataNotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());

        return "404";
    }
}
