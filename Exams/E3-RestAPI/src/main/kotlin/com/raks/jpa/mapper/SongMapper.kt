package com.raks.jpa.mapper

import com.raks.jpa.dto.SongDto
import com.raks.jpa.entity.Song
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface SongMapper : JpaMapper<Song, SongDto>