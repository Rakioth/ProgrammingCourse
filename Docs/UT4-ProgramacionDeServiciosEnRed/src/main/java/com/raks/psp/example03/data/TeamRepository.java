package com.raks.psp.example03.data;

import org.springframework.data.repository.ListCrudRepository;

public interface TeamRepository extends ListCrudRepository<Team, Integer> {
}
