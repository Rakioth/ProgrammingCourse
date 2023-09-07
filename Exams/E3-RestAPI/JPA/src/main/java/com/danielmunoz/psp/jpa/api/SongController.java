package com.danielmunoz.psp.jpa.api;

import com.danielmunoz.psp.jpa.data.Album;
import com.danielmunoz.psp.jpa.data.AlbumRepository;
import com.danielmunoz.psp.jpa.data.Song;
import com.danielmunoz.psp.jpa.data.SongRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SongController {
    private final SongRepository  _songRepository;
    private final AlbumRepository _albumRepository;

    @Autowired
    SongController(SongRepository songRepository, AlbumRepository albumRepository) {
        _songRepository  = songRepository;
        _albumRepository = albumRepository;
    }

    @GetMapping("/songs")
    public Iterable<Song> getAllSongs() {
        return _songRepository.findAll();
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable("id") int id) {
        return ResponseEntity.of(_songRepository.findById(id));
    }

    @PostMapping("/songs")
    public ResponseEntity<Song> createSong(@Valid @RequestBody AddSongDTO addSongDTO) {
        return _albumRepository
                .findById(addSongDTO.getAlbumId())
                .map(album -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(_songRepository.save(addSongDTO.toEntity(album))))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<Song> updateSong(
            @PathVariable("id") int id,
            @Valid @RequestBody AddSongDTO details) {
        Optional<Song> song = _songRepository.findById(id);

        if (song.isEmpty())
            return ResponseEntity.notFound().build();

        if (details.getAlbumId() != null) {
            Optional<Album> album = _albumRepository.findById(details.getAlbumId());
            if (album.isEmpty())
                return ResponseEntity.badRequest().build();
            song.get().setAlbum(album.get());
        }
        if (details.getName() != null)
            song.get().setName(details.getName());
        if (details.getTime() != null)
            song.get().setTime(details.getTime());

        return ResponseEntity.ok(_songRepository.save(song.get()));
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Object> deleteSong(@PathVariable("id") int id) {
        return _songRepository
                .findById(id)
                .map(song -> {
                    _songRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

//        Optional<Song> song = _songRepository.findById(id);
//
//        if (song.isEmpty())
//            return ResponseEntity.notFound().build();
//
//        _songRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
    }
}
