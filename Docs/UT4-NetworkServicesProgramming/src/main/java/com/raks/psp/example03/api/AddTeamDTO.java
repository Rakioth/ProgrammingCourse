package com.raks.psp.example03.api;

import com.raks.psp.example03.data.Team;

public class AddTeamDTO {

    private String name;
    private String city;

    public Team toEntity() {
        Team team = new Team();
        team.setName(name);
        team.setCity(city);
        return team;
    }

}