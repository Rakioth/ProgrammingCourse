package com.danielmunoz.psp.jpa.api;

import com.danielmunoz.psp.jpa.data.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AlbumController {
    private final AlbumRepository  _albumRepository;
    private final ArtistRepository _artistRepository;

    @Autowired
    AlbumController(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        _albumRepository  = albumRepository;
        _artistRepository = artistRepository;
    }

    @GetMapping("/albums")
    public Iterable<Album> getAllAlbums() {
        return _albumRepository.findAll();
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable("id") int id) {
        return ResponseEntity.of(_albumRepository.findById(id));
    }

    @PostMapping("/albums")
    public ResponseEntity<Album> createAlbum(@Valid @RequestBody AddAlbumDTO addAlbumDTO) {
        List<Artist>     artists = new ArrayList<>();
        Optional<Artist> artist;

        for (Integer artistsId : addAlbumDTO.getArtistsIds())
            if ((artist = _artistRepository.findById(artistsId)).isPresent())
                artists.add(artist.get());

        Album album = _albumRepository.save(addAlbumDTO.toEntity(artists));
        return ResponseEntity.status(HttpStatus.CREATED).body(album);
    }

    @PutMapping("/albums/{id}")
    public ResponseEntity<Album> updateAlbum(
            @PathVariable("id") int id,
            @Valid @RequestBody AddAlbumDTO details) {
        Optional<Album> album = _albumRepository.findById(id);

        if (album.isEmpty())
            return ResponseEntity.notFound().build();

        List<Artist>     artists = new ArrayList<>();
        Optional<Artist> artist;

        if (details.getTitle() != null)
            album.get().setTitle(details.getTitle());
        if (details.getGenre() != null)
            album.get().setGenre(details.getGenre());
        if (details.getReleaseDate() != null)
            album.get().setReleaseDate(details.getReleaseDate());
        if (details.getSales() != null)
            album.get().setSales(details.getSales());
        if (details.getHasVinyl() != null)
            album.get().setHasVinyl(details.getHasVinyl());
        if (details.getArtistsIds() != null) {
            for (Integer artistsId : details.getArtistsIds())
                if ((artist = _artistRepository.findById(artistsId)).isPresent())
                    artists.add(artist.get());
            album.get().getArtists().clear();
            album.get().getArtists().addAll(artists);
        }

        final Album updatedAlbum = _albumRepository.save(album.get());
        return ResponseEntity.ok(updatedAlbum);
    }

    @DeleteMapping("/albums/{id}")
    public ResponseEntity<Album> deleteAlbum(@PathVariable("id") int id) {
        Optional<Album> album = _albumRepository.findById(id);

        if (album.isEmpty())
            return ResponseEntity.notFound().build();

        _albumRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
