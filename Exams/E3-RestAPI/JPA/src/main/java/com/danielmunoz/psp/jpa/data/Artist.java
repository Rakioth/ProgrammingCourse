package com.danielmunoz.psp.jpa.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private LocalDate birthDate;

    @Column
    private String city;

    @Column
    private Integer monthlyListeners;

    @Column
    private String label;

    @ManyToMany(mappedBy = "artists")
    @JsonIgnore
    private List<Album> albums;

}
