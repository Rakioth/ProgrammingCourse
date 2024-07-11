package com.raks.jpa.repository;

import com.raks.jpa.entity.Album
import org.springframework.data.jpa.repository.JpaRepository

interface AlbumRepository : JpaRepository<Album, Int>