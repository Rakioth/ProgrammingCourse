package com.raks.jpa.service

import com.raks.jpa.dto.SongDto
import com.raks.jpa.entity.Song
import com.raks.jpa.mapper.SongMapper
import com.raks.jpa.repository.SongRepository
import org.springframework.stereotype.Service

@Service
class SongService(
    repository: SongRepository,
    mapper:     SongMapper,
) : JpaService<Song, SongDto, Int>(
    repository,
    mapper,
)