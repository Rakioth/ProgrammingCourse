package com.danielmunoz.psp.jpa.api;

import com.danielmunoz.psp.jpa.data.Album;
import com.danielmunoz.psp.jpa.data.Artist;
import com.danielmunoz.psp.jpa.data.Genre;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AddAlbumDTO {
    private Integer       id;
    private String        title;
    private Genre         genre;
    private LocalDate     releaseDate;
    private Float         sales;
    private Boolean       hasVinyl;
    private List<Integer> artistsIds;

    public Album toEntity(List<Artist> artists) {
        Album album = new Album();
        album.setId(id);
        album.setTitle(title);
        album.setGenre(genre);
        album.setReleaseDate(releaseDate);
        album.setSales(sales);
        album.setHasVinyl(hasVinyl);
        album.setArtists(artists);
        return album;
    }
}
