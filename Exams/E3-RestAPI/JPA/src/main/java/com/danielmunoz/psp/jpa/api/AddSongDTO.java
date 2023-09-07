package com.danielmunoz.psp.jpa.api;

import com.danielmunoz.psp.jpa.data.Album;
import com.danielmunoz.psp.jpa.data.Song;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
public class AddSongDTO {
    private Integer   id;
    private String    name;
    private LocalTime time;
    @NotNull
    private Integer   albumId;

    public Song toEntity(Album album) {
        Song song = new Song();
        song.setId(id);
        song.setName(name);
        song.setTime(time);
        song.setAlbum(album);
        return song;
    }
}
