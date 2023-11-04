package com.raks.psp.example03.data;

import org.springframework.data.repository.ListCrudRepository;

public interface PlayerRepository extends ListCrudRepository<Player, Integer> {
}