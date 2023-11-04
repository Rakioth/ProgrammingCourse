package com.raks.jpa.service

import com.raks.jpa.dto.ArtistDto
import com.raks.jpa.entity.Artist
import com.raks.jpa.mapper.ArtistMapper
import com.raks.jpa.repository.ArtistRepository
import org.springframework.stereotype.Service

@Service
class ArtistService(
    repository: ArtistRepository,
    mapper:     ArtistMapper,
) : JpaService<Artist, ArtistDto, Int>(
    repository,
    mapper,
)