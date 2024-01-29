package com.raks.psp.example03.api;

import com.raks.psp.example03.data.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private final PlayerRepository _playerRepository;

    @Autowired
    public PlayerController(PlayerRepository playerRepository) {
        _playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public Iterable<PlayerDTO> getAllPlayers() {
        return _playerRepository.findAll().stream().map(PlayerDTO::from).toList();
    }

}