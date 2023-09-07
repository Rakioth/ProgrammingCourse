package com.danielmunoz.psp.jpa.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Column
    private LocalDate releaseDate;

    @Column
    private Float sales;

    @Column
    private Boolean hasVinyl;

    @OneToMany(
            mappedBy = "album",
            orphanRemoval = true
    )
    private List<Song> songs;

    @ManyToMany
    @JoinTable(
            name = "album_artist",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> artists;

}
