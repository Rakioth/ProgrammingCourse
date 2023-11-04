package com.raks.jpa.api

import com.raks.jpa.dto.AlbumDto
import com.raks.jpa.entity.Album
import com.raks.jpa.service.AlbumService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/albums")
class AlbumController(
    service: AlbumService
) : JpaController<Album, AlbumDto, Int>(service)