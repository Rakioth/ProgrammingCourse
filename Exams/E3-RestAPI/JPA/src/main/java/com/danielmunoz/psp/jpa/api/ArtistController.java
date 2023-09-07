package com.danielmunoz.psp.jpa.api;

import com.danielmunoz.psp.jpa.data.Album;
import com.danielmunoz.psp.jpa.data.AlbumRepository;
import com.danielmunoz.psp.jpa.data.Artist;
import com.danielmunoz.psp.jpa.data.ArtistRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ArtistController {
    private final ArtistRepository _artistRepository;

    @Autowired
    ArtistController(ArtistRepository artistRepository) {
        _artistRepository = artistRepository;
    }

    @GetMapping("/artists")
    public Iterable<Artist> getAllArtists() {
        return _artistRepository.findAll();
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable("id") int id) {
        return ResponseEntity.of(_artistRepository.findById(id));
    }

    @PostMapping("/artists")
    public ResponseEntity<Artist> createArtist(@Valid @RequestBody Artist artist) {
        return ResponseEntity.status(HttpStatus.CREATED).body(_artistRepository.save(artist));
    }

    @PutMapping("/artists/{id}")
    public ResponseEntity<Artist> updateArtist(
            @PathVariable("id") int id,
            @Valid @RequestBody Artist details) {
        Optional<Artist> artist = _artistRepository.findById(id);

        if (artist.isEmpty())
            return ResponseEntity.notFound().build();

        if (details.getName() != null)
            artist.get().setName(details.getName());
        if (details.getBirthDate() != null)
            artist.get().setBirthDate(details.getBirthDate());
        if (details.getCity() != null)
            artist.get().setCity(details.getCity());
        if (details.getMonthlyListeners() != null)
            artist.get().setMonthlyListeners(details.getMonthlyListeners());
        if (details.getLabel() != null)
            artist.get().setLabel(details.getLabel());

        final Artist updatedArtist = _artistRepository.save(artist.get());
        return ResponseEntity.ok(updatedArtist);
    }

    @DeleteMapping("/artists/{id}")
    public ResponseEntity<Artist> deleteArtist(@PathVariable("id") int id) {
        Optional<Artist> artist = _artistRepository.findById(id);

        if (artist.isEmpty())
            return ResponseEntity.notFound().build();

        _artistRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
