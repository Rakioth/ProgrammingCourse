package com.raks.jpa.mapper

import com.raks.jpa.dto.ArtistDto
import com.raks.jpa.entity.Artist
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ArtistMapper : JpaMapper<Artist, ArtistDto>