package com.raks.jpa.mapper

import com.raks.jpa.dto.AlbumDto
import com.raks.jpa.entity.Album
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", uses = [GenreMapper::class, ArtistMapper::class, SongMapper::class])
interface AlbumMapper : JpaMapper<Album, AlbumDto>