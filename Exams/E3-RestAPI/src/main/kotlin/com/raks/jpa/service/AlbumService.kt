package com.raks.jpa.service

import com.raks.jpa.dto.AlbumDto
import com.raks.jpa.entity.Album
import com.raks.jpa.mapper.AlbumMapper
import com.raks.jpa.repository.AlbumRepository
import org.springframework.stereotype.Service

@Service
class AlbumService(
    repository: AlbumRepository,
    mapper:     AlbumMapper,
) : JpaService<Album, AlbumDto, Int>(
    repository,
    mapper,
)