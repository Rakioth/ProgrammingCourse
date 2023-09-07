package com.raks.psp.example03.api;

import com.raks.psp.example03.data.Player;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlayerDTO {
    private Integer   id;
    private String    name;
    private LocalDate birthDate;
    private Integer   currentTeamId;

    public static PlayerDTO from(Player p) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(p.getId());
        playerDTO.setName(p.getName());
        playerDTO.setBirthDate(p.getBirthDate());
        playerDTO.setCurrentTeamId(p.getCurrentTeam().getId());
        return playerDTO;
    }
}
