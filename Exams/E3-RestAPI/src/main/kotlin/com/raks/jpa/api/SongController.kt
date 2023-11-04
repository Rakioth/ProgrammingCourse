package com.raks.jpa.api

import com.raks.jpa.dto.SongDto
import com.raks.jpa.entity.Song
import com.raks.jpa.service.SongService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/songs")
class SongController(
    service: SongService
) : JpaController<Song, SongDto, Int>(service)