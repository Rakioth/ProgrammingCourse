package com.raks.jpa.repository;

import com.raks.jpa.entity.Song
import org.springframework.data.jpa.repository.JpaRepository

interface SongRepository : JpaRepository<Song, Int>