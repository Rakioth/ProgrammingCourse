package com.raks.jpa.api

import com.raks.jpa.dto.ArtistDto
import com.raks.jpa.entity.Artist
import com.raks.jpa.service.ArtistService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/artists")
class ArtistController(
    service: ArtistService
) : JpaController<Artist, ArtistDto, Int>(service)