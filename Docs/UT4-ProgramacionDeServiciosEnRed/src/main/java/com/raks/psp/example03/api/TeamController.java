package com.raks.psp.example03.api;

import com.raks.psp.example03.data.Team;
import com.raks.psp.example03.data.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TeamController {
    private final TeamRepository _teamRepository;

    @Autowired
    TeamController(TeamRepository teamRepository) {
        _teamRepository = teamRepository;
    }

    @GetMapping("/teams")
    public Iterable<Team> getAllTeams() {
        return _teamRepository.findAll();
    }

    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(@RequestBody AddTeamDTO addTeamDTO) {
        Team team;
        team = _teamRepository.save(addTeamDTO.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(team);
    }

    @DeleteMapping("/teams/{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable("id") int id) {
        Optional<Team> team = _teamRepository.findById(id);
        if (team.isEmpty())
            return ResponseEntity.notFound().build();

        _teamRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
