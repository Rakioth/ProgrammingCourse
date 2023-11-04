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

    public static PlayerDTO from(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setBirthDate(player.getBirthDate());
        playerDTO.setCurrentTeamId(player.getCurrentTeam().getId());
        return playerDTO;
    }

}