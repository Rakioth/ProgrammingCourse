package com.raks.psp.example02.api;

import com.raks.psp.example02.data.Team;
import com.raks.psp.example02.data.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private final TeamRepository _teamRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository) {
        _teamRepository = teamRepository;
    }

    @GetMapping("/teams")
    public Iterable<Team> getAllTeams() {
        return _teamRepository.findAll();
    }

}