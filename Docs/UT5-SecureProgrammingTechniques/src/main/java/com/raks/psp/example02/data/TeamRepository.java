package com.raks.psp.example02.data;

import org.springframework.data.repository.ListCrudRepository;

public interface TeamRepository extends ListCrudRepository<Team, Integer> {
}