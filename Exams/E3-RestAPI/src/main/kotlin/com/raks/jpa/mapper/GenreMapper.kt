package com.raks.jpa.mapper

import com.raks.jpa.dto.GenreDto
import com.raks.jpa.entity.Genre
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface GenreMapper : JpaMapper<Genre, GenreDto>